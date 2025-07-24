package T2507;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: DFS 时间戳
 * @Author: iniwym
 * @Date: 2025-07-24
 * @Link: https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/
 */
public class Code2322_MinimumScoreAfterRemovalsOnATree {

    /**
     * Solution 类用于解决树上删除两条边后最小分数的问题。
     */
    class Solution {
        /**
         * 计算在树中删除两条边后形成的三个连通分量的异或值的最大差值的最小可能值。
         *
         * @param nums  一个长度为 n 的数组，表示每个节点的值
         * @param edges 一个 n-1 行的二维数组，表示树中的边
         * @return 删除两条边后三个连通分量异或值最大差值的最小值
         */
        public int minimumScore(int[] nums, int[][] edges) {
            int n = nums.length;
            // 建立邻接表表示的无向图
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : edges) {
                int x = e[0];
                int y = e[1];
                g[x].add(y);
                g[y].add(x);
            }

            // 初始化每个节点子树异或值、进入时间和离开时间
            int[] xor = new int[n];
            int[] in = new int[n];
            int[] out = new int[n];
            dfs(0, -1, g, nums, xor, in, out);

            int ans = Integer.MAX_VALUE;
            // 枚举：删除 x 与 x 父节点之间的边，删除 y 与 y 父节点之间的边
            for (int x = 2; x < n; x++) {
                for (int y = 1; y < x; y++) {
                    int a, b, c;
                    if (isAncestor(x, y, in, out)) { // x 是 y 的祖先
                        a = xor[y];
                        b = xor[x] ^ a;
                        c = xor[0] ^ xor[x];
                    } else if (isAncestor(y, x, in, out)) { // y 是 x 的祖先
                        a = xor[x];
                        b = xor[y] ^ a;
                        c = xor[0] ^ xor[y];
                    } else { // x 和 y 分别属于两棵不相交的子树
                        a = xor[x];
                        b = xor[y];
                        c = xor[0] ^ a ^ b;
                    }
                    ans = Math.min(ans, Math.max(Math.max(a, b), c) - Math.min(Math.min(a, b), c));
                    if (ans == 0) { // 不可能变小
                        return 0; // 提前返回
                    }
                }
            }
            return ans;
        }

        private int clock = 0;

        /**
         * 使用深度优先搜索计算每个节点的子树异或值以及进入和离开时间。
         *
         * @param x    当前访问的节点编号
         * @param fa   当前节点的父节点编号
         * @param g    图的邻接表表示
         * @param nums 每个节点的值
         * @param xor  存储每个节点子树异或值的数组
         * @param in   存储每个节点进入时间的数组
         * @param out  存储每个节点离开时间的数组
         */
        private void dfs(int x, int fa, List<Integer>[] g, int[] nums, int[] xor, int[] in, int[] out) {
            in[x] = ++clock; // 进入当前节点的时间戳
            xor[x] = nums[x];
            for (int y : g[x]) {
                if (y != fa) {
                    dfs(y, x, g, nums, xor, in, out);
                    xor[x] ^= xor[y]; // 合并子树的异或值
                }
            }
            out[x] = clock; // 离开当前节点的时间戳
        }

        /**
         * 判断节点 x 是否是节点 y 的祖先。
         *
         * @param x   要检查是否为祖先的节点
         * @param y   要检查是否为后代的节点
         * @param in  每个节点的进入时间数组
         * @param out 每个节点的离开时间数组
         * @return 如果 x 是 y 的祖先则返回 true，否则返回 false
         */
        private boolean isAncestor(int x, int y, int[] in, int[] out) {
            return in[x] < in[y] && in[y] <= out[x];
        }
    }

}
