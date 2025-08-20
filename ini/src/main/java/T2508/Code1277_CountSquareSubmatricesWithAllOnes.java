package T2508;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-08-20
 * @Link: https://leetcode.cn/problems/count-square-submatrices-with-all-ones/
 */
public class Code1277_CountSquareSubmatricesWithAllOnes {

    /**
     * 统计矩阵中全为1的正方形子矩阵的个数
     * 使用动态规划方法，dp[i][j]表示以(i,j)为右下角的正方形的最大边长
     *
     * @param matrix 输入的二维矩阵，元素只为0或1
     * @return 返回矩阵中全为1的正方形子矩阵的总个数
     */
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int total = 0;

        // 遍历矩阵中的每个位置，计算以该位置为右下角的正方形个数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 边界情况：第一行或第一列
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                    // 当前位置为1时，计算能形成的最大正方形边长
                } else if (matrix[i][j] == 1) {
                    // 取左边、上边、左上角三个位置的最小值加1
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                } else {
                    dp[i][j] = 0;
                }
                // 累加当前位罝为右下角的正方形个数
                total += dp[i][j];
            }
        }
        return total;
    }


}
