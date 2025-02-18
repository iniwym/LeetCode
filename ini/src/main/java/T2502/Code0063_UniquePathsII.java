package T2502;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-02-10
 * @Link: https://leetcode.cn/problems/unique-paths-ii/
 */
public class Code0063_UniquePathsII {

    /**
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。、
     * 对于第一行的任意位置dp[0][j]的值，
     * 需要判断的dp[0][j-1]是否是0和obstacleGrid[0][j]是否有障碍物。
     * 对于第一列的任意位置dp[i][0]的值，
     * 需要判断的dp[i-1][0]是否是0和obstacleGrid[i][0]是否有障碍物。
     * 状态转移方程：dp[i][j] 的值，
     * 需要判断(dp[i-1][j] && dp[i][j-1])是否同时为0和obstacleGrid[i][j]是否有障碍物。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        // 初始化第一行和第一列的值
        dp[0][0] = 1;

        // 处理第一行
        for (int i = 1; i < n; i++) {
            // 如果当前位置有障碍物，则该位置的值为0，或者前一个位置的值是0，代表该位置的值为0，否则为前一个位置的值
            dp[0][i] = obstacleGrid[0][i] == 1 || dp[0][i - 1] == 0 ? 0 : dp[0][i - 1];
        }

        // 处理第一列
        for (int i = 1; i < m; i++) {
            // 如果当前位置有障碍物，则该位置的值为0，或者前一个位置的值是0，代表该位置的值为0，否则为前一个位置的值
            dp[i][0] = obstacleGrid[i][0] == 1 || dp[i - 1][0] == 0 ? 0 : dp[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                /**
                 * 如果当前位置有障碍物，则该位置的值为0，
                 * 如果左边的位置的值为0，同时上方的位置的值为0，代表该位置的值为0，
                 * 否则为左边和上方的值之和
                 */
                dp[i][j] = obstacleGrid[i][j] == 1 || (dp[i - 1][j] == 0 && dp[i][j - 1] == 0) ? 0
                        : dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 使用一维数组优化空间复杂度
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        // 检查输入是否为空或不规则
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 检查起点或终点是否有障碍物
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // 使用一维数组优化空间复杂度
        int[] dp = new int[n];
        dp[0] = 1;

        /**
         * 使用一维数组保存的是上一行的信息
         * 对于任意一个位置dp[j]的值，
         * 需要判断dp[j-1]是否为0和obstacleGrid[i][j]是否有障碍物。
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    // 如果j-1位置的值不为0
                    if (dp[j - 1] != 0) {
                        // dp[j]保存上一行的路径信息
                        // dp[j - 1]保存左边的路径信息
                        dp[j] = dp[j] + dp[j - 1];
                    }
                }
            }
        }

        return dp[n - 1];
    }
}
