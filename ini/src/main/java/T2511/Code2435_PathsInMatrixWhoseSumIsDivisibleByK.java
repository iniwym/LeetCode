package T2511;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-11-26
 * @Link: https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k/
 */
public class Code2435_PathsInMatrixWhoseSumIsDivisibleByK {

    /**
     * 解决方案类，用于计算网格中满足条件的路径数量
     */
    class Solution {
        private static final int MOD = 1_000_000_007;

        /**
         * 计算从网格左上角到右下角的路径数量，其中路径上数字总和能被k整除
         *
         * @param grid 输入的二维网格数组
         * @param k    用于判断路径总和是否能被整除的数值
         * @return 满足条件的路径数量，结果对MOD取模
         */
        public int numberOfPaths(int[][] grid, int k) {
            int m = grid.length;
            int n = grid[0].length;
            // 创建三维记忆化数组，memo[i][j][s]表示到达位置(i,j)且路径和模k等于s的路径数
            int[][][] memo = new int[m][n][k];
            // 初始化记忆化数组，-1表示该状态尚未计算
            for (int[][] mat : memo) {
                for (int[] row : mat) {
                    Arrays.fill(row, -1);
                }
            }
            // 从右下角开始DFS搜索，初始状态和为0
            return dfs(m - 1, n - 1, 0, memo, grid, k);
        }

        /**
         * 深度优先搜索函数，计算到达指定位置且满足路径和条件的路径数量
         *
         * @param i    当前行坐标
         * @param j    当前列坐标
         * @param s    当前路径和模k的值
         * @param memo 三维记忆化数组
         * @param grid 输入的二维网格数组
         * @param k    用于判断路径总和是否能被整除的数值
         * @return 从当前位置到起点满足条件的路径数量
         */
        private int dfs(int i, int j, int s, int[][][] memo, int[][] grid, int k) {
            // 边界检查：超出网格范围
            if (i < 0 || j < 0) {
                return 0;
            }

            // 计算前驱状态的路径和模k值
            int preS = ((s - grid[i][j]) % k + k) % k;

            // 到达起点的情况
            if (i == 0 && j == 0) {
                // 如果前驱状态和为0，则找到一条有效路径
                return preS == 0 ? 1 : 0;
            }

            // 检查是否已经计算过该状态
            if (memo[i][j][s] != -1) {
                return memo[i][j][s];
            }

            // 分别从上方和左方转移过来的路径数
            int res1 = dfs(i - 1, j, preS, memo, grid, k);
            int res2 = dfs(i, j - 1, preS, memo, grid, k);

            // 记忆化存储并返回结果
            return memo[i][j][s] = (res1 + res2) % MOD;
        }
    }

}
