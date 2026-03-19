package T2603;

/**
 * @Description: 前缀和
 * @Author: iniwym
 * @Date: 2026-03-19
 * @Link: https://leetcode.cn/problems/count-submatrices-with-equal-frequency-of-x-and-y/
 */
public class Code3212_CountSubmatricesWithEqualFrequencyOfXAndY {

    /**
     * 统计满足条件的子矩阵数量：子矩阵中字符 'X' 和 'Y' 的出现频率相等
     * 使用二维前缀和数组来高效计算任意子矩阵内字符的出现次数
     *
     * @param grid 输入的二维字符矩阵，包含字符 'X'、'Y' 或 '.'（空位）
     * @return 满足条件的子矩阵数量
     */
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        // 创建三维前缀和数组，sum[i][j][0] 表示 'X' 的计数，sum[i][j][1] 表示 'Y' 的计数
        int[][][] sum = new int[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 使用容斥原理计算当前位置的前缀和
                sum[i + 1][j + 1][0] = sum[i + 1][j][0] + sum[i][j + 1][0] - sum[i][j][0];
                sum[i + 1][j + 1][1] = sum[i + 1][j][1] + sum[i][j + 1][1] - sum[i][j][1];
                if (grid[i][j] != '.') {
                    // 根据字符类型更新对应的前缀和计数（'X'&1=0, 'Y'&1=1）
                    sum[i + 1][j + 1][grid[i][j] & 1]++;
                }
                // 检查以当前位置为右下角的子矩阵是否满足条件
                if (sum[i + 1][j + 1][0] > 0 && sum[i + 1][j + 1][0] == sum[i + 1][j + 1][1]) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
