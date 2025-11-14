package T2511;

/**
 * @Description: 二维差分+二维前缀和
 * @Author: iniwym
 * @Date: 2025-11-14
 * @Link: https://leetcode.cn/problems/increment-submatrices-by-one/
 */
public class Code2536_IncrementSubmatricesByOne {

    /**
     * 对一个 n x n 的矩阵执行多个范围加法查询操作
     * 使用二维差分数组优化区间更新操作，最后通过前缀和还原结果
     *
     * @param n       矩阵的边长，创建一个 n x n 的矩阵
     * @param queries 查询操作数组，每个查询 [r1, c1, r2, c2] 表示对矩形区域 [r1,c1] 到 [r2,c2] 进行 +1 操作
     * @return 执行完所有查询后的最终矩阵
     */
    public int[][] rangeAddQueries(int n, int[][] queries) {
        // 二维差分
        int[][] diff = new int[n + 2][n + 2];
        for (int[] q : queries) {
            int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
            diff[r1 + 1][c1 + 1]++;
            diff[r1 + 1][c2 + 2]--;
            diff[r2 + 2][c1 + 1]--;
            diff[r2 + 2][c2 + 2]++;
        }

        // 原地计算 diff 的二维前缀和，然后填入答案
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diff[i + 1][j + 1] += diff[i + 1][j] + diff[i][j + 1] - diff[i][j];
                ans[i][j] = diff[i + 1][j + 1];
            }
        }
        return ans;
    }

}
