package T2601;

/**
 * @Description: 枚举 + 前缀和
 * @Author: iniwym
 * @Date: 2026-01-19
 * @Link: https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
 */
public class Code1292_MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {
    /**
     * 找到矩阵中满足条件的最大正方形边长
     * 给定一个矩阵和阈值，找到和不超过阈值的最大正方形的边长
     *
     * @param mat       矩阵数组，二维整数数组
     * @param threshold 阈值，正方形内所有元素的和不能超过此值
     * @return 满足条件的最大正方形的边长
     */
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        // 构建前缀和数组，用于快速计算任意子矩阵的和
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + mat[i][j];
            }
        }

        int ans = 0;
        // 遍历每个可能的左上角位置，尝试扩展正方形的边长
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 边长为 ans+1 的正方形，左上角在 (i, j)，右下角在 (i+ans, j+ans)
                while (i + ans < m && j + ans < n && query(sum, i, j, i + ans, j + ans) <= threshold) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 查询二维前缀和数组中指定子矩阵的元素和
     * 使用容斥原理计算矩形区域的和：总区域减去多余部分再加上重复减去的部分
     *
     * @param sum 二维前缀和数组，其中sum[i][j]表示原矩阵中从(0,0)到(i-1,j-1)的子矩阵元素和
     * @param r1  子矩阵左上角的行索引（从0开始）
     * @param c1  子矩阵左上角的列索引（从0开始）
     * @param r2  子矩阵右下角的行索引（从0开始）
     * @param c2  子矩阵右下角的列索引（从0开始）
     * @return 左上角在(r1, c1)，右下角在(r2,c2)的子矩阵所有元素的和
     */
    private int query(int[][] sum, int r1, int c1, int r2, int c2) {
        return sum[r2 + 1][c2 + 1] - sum[r2 + 1][c1] - sum[r1][c2 + 1] + sum[r1][c1];
    }

}
