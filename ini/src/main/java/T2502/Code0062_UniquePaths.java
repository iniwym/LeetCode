package T2502;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-02-09
 * @Link: https://leetcode.cn/problems/unique-paths/
 */
public class Code0062_UniquePaths {

    /**
     * 从左上角到右下角的路径数量
     * 第一行的时候，每个点都只有1种路径
     * 同理，第一列的时候，每个点都只有1种路径
     * 状态转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
     */
    public int uniquePaths(int m, int n) {

        if (m == 1 || n == 1) {
            return 1;
        }

        int[][] dp = new int[m][n];

        // 处理第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 处理第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 状态转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 使用一维数组进行空间复杂度优化
     */
    public int uniquePaths2(int m, int n) {
        // 增强边界条件检查
        if (m <= 0 || n <= 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }

        // 使用一维数组优化空间复杂度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                /**
                 * dp[j]保存上一行的路径信息
                 * dp[j - 1]保存左边的路径信息
                 */
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }

}
