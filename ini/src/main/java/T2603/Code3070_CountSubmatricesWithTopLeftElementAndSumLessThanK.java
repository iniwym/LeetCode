package T2603;

/**
 * @Description: 前缀和
 * @Author: iniwym
 * @Date: 2026-03-18
 * @Link: https://leetcode.cn/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/
 */
public class Code3070_CountSubmatricesWithTopLeftElementAndSumLessThanK {

    /**
     * 计算左上角元素固定且元素和小于等于 k 的子矩阵数量
     * 使用二维前缀和优化计算，对于每个位置 (i,j)，计算从 (0,0) 到 (i,j) 的矩形区域内所有元素的和
     *
     * @param grid 输入的二维矩阵，所有元素为非负整数
     * @param k    目标和的上限值
     * @return 返回满足条件的子矩阵数量，即左上角为 (0,0) 且元素和小于等于 k 的子矩阵总数
     */
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m + 1][n + 1];

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
                if (sum[i + 1][j + 1] <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
