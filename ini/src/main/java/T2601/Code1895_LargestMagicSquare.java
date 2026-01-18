package T2601;

/**
 * @Description: 枚举 + 前缀和
 * @Author: iniwym
 * @Date: 2026-01-18
 * @Link: https://leetcode.cn/problems/largest-magic-square/
 */
public class Code1895_LargestMagicSquare {

    /**
     * 找到网格中最大的幻方的边长
     * 幻方是指每行、每列以及两条对角线上的数字之和都相等的正方形子矩阵
     *
     * @param grid 输入的二维整数数组，表示网格
     * @return 返回最大幻方的边长，如果不存在大于1的幻方则返回1
     */
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 每一行的前缀和
        int[][] rowsum = new int[m][n];
        for (int i = 0; i < m; ++i) {
            rowsum[i][0] = grid[i][0];
            for (int j = 1; j < n; ++j) {
                rowsum[i][j] = rowsum[i][j - 1] + grid[i][j];
            }
        }
        // 每一列的前缀和
        int[][] colsum = new int[m][n];
        for (int j = 0; j < n; ++j) {
            colsum[0][j] = grid[0][j];
            for (int i = 1; i < m; ++i) {
                colsum[i][j] = colsum[i - 1][j] + grid[i][j];
            }
        }

        // 从大到小枚举边长 edge
        for (int edge = Math.min(m, n); edge >= 2; --edge) {
            // 枚举正方形的左上角位置 (i,j)
            for (int i = 0; i + edge <= m; ++i) {
                for (int j = 0; j + edge <= n; ++j) {
                    // 计算每一行、列、对角线的值应该是多少（以第一行为样本）
                    int stdsum = rowsum[i][j + edge - 1] - (j > 0 ? rowsum[i][j - 1] : 0);
                    boolean check = true;
                    // 枚举每一行并用前缀和直接求和
                    for (int ii = i + 1; ii < i + edge; ++ii) {
                        if (rowsum[ii][j + edge - 1] - (j > 0 ? rowsum[ii][j - 1] : 0) != stdsum) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;
                    // 枚举每一列并用前缀和直接求和
                    for (int jj = j; jj < j + edge; ++jj) {
                        if (colsum[i + edge - 1][jj] - (i > 0 ? colsum[i - 1][jj] : 0) != stdsum) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;
                    // 两条对角线的和
                    int d1 = 0, d2 = 0;
                    for (int k = 0; k < edge; ++k) {
                        d1 += grid[i + k][j + k];
                        d2 += grid[i + k][j + edge - 1 - k];
                    }
                    if (d1 == stdsum && d2 == stdsum) {
                        return edge;
                    }
                }
            }
        }
        return 1;
    }

}
