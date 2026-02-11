package T2602;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Lazy 线段树
 * @Author: iniwym
 * @Date: 2026-02-11
 * @Link: https://leetcode.cn/problems/longest-balanced-subarray-ii/
 */
public class Code3721_LongestBalancedSubarrayIi {

    /**
     * LazySegmentTree 是一个支持区间更新和区间查询的懒惰线段树实现。
     * 它可以高效地处理区间加法操作，并支持查找区间内第一个等于目标值的元素下标。
     */
    class LazySegmentTree {
        /**
         * Node 是线段树中的节点，用于存储区间的最小值、最大值以及懒标记。
         */
        private final class Node {
            int min;   // 区间最小值
            int max;   // 区间最大值
            int todo;  // 懒标记，表示需要传递给子节点的操作
        }

        /**
         * 将懒标记应用到指定节点的子树中。
         *
         * @param node 当前节点的索引
         * @param todo 需要应用的懒标记值
         */
        private void apply(int node, int todo) {
            Node cur = tree[node];
            cur.min += todo;
            cur.max += todo;
            cur.todo += todo;
        }

        private final int n;         // 数组长度
        private final Node[] tree;   // 线段树数组

        /**
         * 构造函数，初始化一个大小为 n 的线段树。
         *
         * @param n 线段树维护的数组长度
         */
        public LazySegmentTree(int n) {
            this.n = n;
            tree = new Node[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
            Arrays.setAll(tree, i -> new Node());
        }

        /**
         * 对区间 [ql, qr] 中的所有元素执行加法操作 f。
         *
         * @param ql 区间左端点（包含）
         * @param qr 区间右端点（包含）
         * @param f  要加上的数值
         */
        public void update(int ql, int qr, int f) {
            update(1, 0, n - 1, ql, qr, f);
        }

        /**
         * 在区间 [ql, qr] 内查找第一个等于 target 的元素下标。
         * 如果找不到则返回 -1。
         *
         * @param ql     区间左端点（包含）
         * @param qr     区间右端点（包含）
         * @param target 目标值
         * @return 第一个等于 target 的元素下标，若不存在则返回 -1
         */
        public int findFirst(int ql, int qr, int target) {
            return findFirst(1, 0, n - 1, ql, qr, target);
        }

        /**
         * 将当前节点的懒标记下传给其左右子节点。
         *
         * @param node 当前节点的索引
         */
        private void spread(int node) {
            int todo = tree[node].todo;
            if (todo == 0) { // 没有需要下传的信息
                return;
            }
            apply(node * 2, todo);
            apply(node * 2 + 1, todo);
            tree[node].todo = 0; // 下传完毕
        }

        /**
         * 合并左右子节点的信息到当前节点。
         *
         * @param node 当前节点的索引
         */
        private void maintain(int node) {
            tree[node].min = Math.min(tree[node * 2].min, tree[node * 2 + 1].min);
            tree[node].max = Math.max(tree[node * 2].max, tree[node * 2 + 1].max);
        }

        /**
         * 递归更新线段树中指定区间的值。
         *
         * @param node 当前节点的索引
         * @param l    当前节点所代表区间的左端点
         * @param r    当前节点所代表区间的右端点
         * @param ql   查询区间的左端点
         * @param qr   查询区间的右端点
         * @param f    要加上的数值
         */
        private void update(int node, int l, int r, int ql, int qr, int f) {
            if (ql <= l && r <= qr) { // 当前子树完全在 [ql, qr] 内
                apply(node, f);
                return;
            }
            spread(node);
            int m = (l + r) / 2;
            if (ql <= m) { // 更新左子树
                update(node * 2, l, m, ql, qr, f);
            }
            if (qr > m) { // 更新右子树
                update(node * 2 + 1, m + 1, r, ql, qr, f);
            }
            maintain(node);
        }

        /**
         * 递归查找区间 [ql, qr] 内第一个等于 target 的元素下标。
         *
         * @param node   当前节点的索引
         * @param l      当前节点所代表区间的左端点
         * @param r      当前节点所代表区间的右端点
         * @param ql     查询区间的左端点
         * @param qr     查询区间的右端点
         * @param target 目标值
         * @return 第一个等于 target 的元素下标，若不存在则返回 -1
         */
        private int findFirst(int node, int l, int r, int ql, int qr, int target) {
            if (l > qr || r < ql || target < tree[node].min || target > tree[node].max) {
                return -1;
            }
            if (l == r) {
                return l;
            }
            spread(node);
            int m = (l + r) / 2;
            int idx = findFirst(node * 2, l, m, ql, qr, target);
            if (idx < 0) {
                idx = findFirst(node * 2 + 1, m + 1, r, ql, qr, target);
            }
            return idx;
        }
    }


    /**
     * 解决寻找最长平衡子数组的问题。
     * 平衡子数组定义为：子数组中所有元素的奇偶性交替出现，且奇数和偶数的数量差不超过1。
     *
     * @param nums 输入的整数数组
     * @return 返回最长平衡子数组的长度
     */
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        LazySegmentTree t = new LazySegmentTree(n + 1);

        Map<Integer, Integer> last = new HashMap<>(); // 记录nums中每个元素上一次出现的位置
        int ans = 0;
        int curSum = 0;

        // 遍历数组，动态维护当前前缀和以及线段树的状态
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            int v = x % 2 > 0 ? 1 : -1; // 将奇数映射为1，偶数映射为-1
            Integer j = last.get(x);
            if (j == null) { // 如果是首次遇到该元素
                curSum += v;
                t.update(i, n, v); // 更新线段树，将区间[i, n]的值增加v
            } else { // 如果是再次遇到该元素
                t.update(j, i - 1, -v); // 撤销之前对该元素在区间[j, i-1]的影响
            }
            last.put(x, i); // 更新该元素最新出现的位置

            // 在线段树中查找满足条件的最左端点，优化搜索范围以减少无效计算
            int l = t.findFirst(0, i - 1 - ans, curSum);
            if (l >= 0) {
                ans = i - l; // 如果找到有效区间，则更新最大长度
            }
        }
        return ans;
    }

}
