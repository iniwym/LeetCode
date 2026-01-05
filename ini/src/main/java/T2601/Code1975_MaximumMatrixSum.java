package T2601;

/**
 * @Description: 贪心策略
 * @Author: iniwym
 * @Date: 2026-01-05
 * @Link: https://leetcode.cn/problems/maximum-matrix-sum/
 */
public class Code1975_MaximumMatrixSum {
    /**
     * 计算矩阵经过任意次数操作后的最大可能元素和
     * 操作规则：每次可以选择任意两个相邻元素，将它们都变成它们的相反数
     *
     * @param matrix 输入的二维整数矩阵
     * @return 返回经过操作后矩阵元素的最大可能和
     */
    public long maxMatrixSum(int[][] matrix) {
        int length = matrix.length;
        // 统计负数的个数
        int num = 0;
        // 计算所有元素绝对值的总和
        long total = 0;
        // 记录矩阵中绝对值最小的元素
        int mn = Integer.MAX_VALUE;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                mn = Math.min(mn, Math.abs(matrix[i][j]));
                if (matrix[i][j] < 0) {
                    ++num;
                }
                total += Math.abs(matrix[i][j]);
            }
        }

        // 如果负数个数为偶数，所有负数都可以通过操作变为正数
        // 如果负数个数为奇数，必须保留一个负数，选择绝对值最小的作为负数以获得最大和
        if (num % 2 == 0) {
            return total;
        } else {
            return total - 2 * mn;
        }

    }

}
