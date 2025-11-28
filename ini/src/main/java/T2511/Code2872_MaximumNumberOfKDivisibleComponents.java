package T2511;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 判断子树点权和是否为 k 的倍数
 * @Author: iniwym
 * @Date: 2025-11-28
 * @Link: https://leetcode.cn/problems/maximum-number-of-k-divisible-components/
 */
public class Code2872_MaximumNumberOfKDivisibleComponents {

    class Solution {
        private int ans;

        public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, temp -> new ArrayList<>());
            for (int[] e : edges) {
                int x = e[0];
                int y = e[1];
                g[x].add(y);
                g[y].add(x);
            }

            dfs(0, -1, g, values, k);
            return ans;
        }

        // 返回子树 x 的点权和
        private long dfs(int x, int fa, List<Integer>[] g, int[] values, int k) {
            long sum = values[x];
            for (int y : g[x]) {
                if (y != fa) { // 避免访问父节点
                    // 加上子树 y 的点权和，得到子树 x 的点权和
                    sum += dfs(y, x, g, values, k);
                }
            }
            ans += sum % k == 0 ? 1 : 0;
            return sum;
        }
    }

}
