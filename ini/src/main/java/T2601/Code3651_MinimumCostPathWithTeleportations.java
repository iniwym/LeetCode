package T2601;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2026-01-28
 * @Link: https://leetcode.cn/problems/minimum-cost-path-with-teleportations/
 */
public class Code3651_MinimumCostPathWithTeleportations {

    /**
     * 计算网格中从左上角到右下角的最小成本路径
     *
     * @param grid 二维整数数组，表示网格中的成本值
     * @param k    允许进行特殊操作的最大次数
     * @return 返回从左上角到右下角的最小总成本
     */
    public int minCost(int[][] grid, int k) {
        int n = grid[0].length;
        int mx = 0;
        // 找出网格中的最大值，用于确定数组大小
        for (int[] row : grid) {
            for (int x : row) {
                mx = Math.max(mx, x);
            }
        }

        int[] sufMinF = new int[mx + 2];
        Arrays.fill(sufMinF, Integer.MAX_VALUE);
        int[] minF = new int[mx + 1];
        int[] f = new int[n + 1];

        // 动态规划主循环，遍历k次操作的可能性
        for (int t = 0; t <= k; t++) {
            Arrays.fill(minF, Integer.MAX_VALUE);

            // 64. 最小路径和（空间优化写法）
            Arrays.fill(f, Integer.MAX_VALUE / 2);
            f[1] = -grid[0][0]; // 起点的成本不算
            for (int[] row : grid) {
                for (int j = 0; j < n; j++) {
                    int x = row[j];
                    f[j + 1] = Math.min(Math.min(f[j], f[j + 1]) + x, sufMinF[x]);
                    minF[x] = Math.min(minF[x], f[j + 1]);
                }
            }

            // 计算 minF 的后缀最小值
            for (int i = mx; i >= 0; i--) {
                sufMinF[i] = Math.min(sufMinF[i + 1], minF[i]);
            }
        }

        return f[n];
    }

}
