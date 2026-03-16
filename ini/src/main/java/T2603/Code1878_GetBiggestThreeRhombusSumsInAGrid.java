package T2603;

import java.util.Arrays;

/**
 * @Description: 斜向前缀和
 * @Author: iniwym
 * @Date: 2026-03-16
 * @Link: https://leetcode.cn/problems/get-biggest-three-rhombus-sums-in-a-grid/
 */
public class Code1878_GetBiggestThreeRhombusSumsInAGrid {

    // 最大，次大，第三大的菱形和
    private int x, y, z;

    /**
     * 获取矩阵中三个最大的不同菱形和
     *
     * @param grid 输入的二维整数矩阵
     * @return 包含三个最大菱形和的数组，如果不同的和少于三个则返回实际数量
     */
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // ↘ 方向的对角线前缀和
        int[][] diagSum = new int[m + 1][n + 1];
        // ↙ 方向的反对角线前缀和
        int[][] antiSum = new int[m + 1][n + 1];

        // 预处理：构建两个方向的前缀和数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                diagSum[i + 1][j + 1] = diagSum[i][j] + v;
                antiSum[i + 1][j] = antiSum[i][j + 1] + v;
            }
        }

        // 枚举每个可能的菱形中心点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 更新单个元素（退化的菱形）
                update(grid[i][j]);

                // 计算以 (i,j) 为中心的最大可能菱形半径
                int mx = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));

                // 枚举所有可能的菱形大小
                for (int k = 1; k <= mx; k++) {
                    // 计算菱形四条边的和
                    int a = queryDiagonal(diagSum, i - k, j, k);
                    int b = queryDiagonal(diagSum, i, j - k, k);
                    int c = queryAntiDiagonal(antiSum, i - k + 1, j - 1, k - 1);
                    int d = queryAntiDiagonal(antiSum, i, j + k, k + 1);
                    update(a + b + c + d);
                }
            }
        }

        // 构造结果数组，去除未更新的零值
        int[] ans = new int[]{x, y, z};
        int len = 3;
        while (ans[len - 1] == 0) {
            len--;
        }
        return Arrays.copyOf(ans, len);
    }

    /**
     * 查询从指定位置开始沿右下方向的连续 k 个元素的和
     *
     * @param diagSum 对角线前缀和数组
     * @param x       起始行坐标
     * @param y       起始列坐标
     * @param k       连续元素个数
     * @return 对角线上 k 个元素的和
     */
    private int queryDiagonal(int[][] diagSum, int x, int y, int k) {
        return diagSum[x + k][y + k] - diagSum[x][y];
    }

    /**
     * 查询从指定位置开始沿左下方向的连续 k 个元素的和
     *
     * @param antiSum 反对角线前缀和数组
     * @param x       起始行坐标
     * @param y       起始列坐标
     * @param k       连续元素个数
     * @return 反对角线上 k 个元素的和
     */
    private int queryAntiDiagonal(int[][] antiSum, int x, int y, int k) {
        return antiSum[x + k][y + 1 - k] - antiSum[x][y + 1];
    }

    /**
     * 更新三个最大值
     *
     * @param v 新的候选值
     */
    private void update(int v) {
        if (v > x) {
            z = y;
            y = x;
            x = v;
        } else if (v < x && v > y) {
            z = y;
            y = v;
        } else if (v < y && v > z) {
            z = v;
        }
    }
}
