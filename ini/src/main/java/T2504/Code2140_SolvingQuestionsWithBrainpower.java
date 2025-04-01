package T2504;

/**
 * @Description: 无后效性的动态规划
 * @Author: iniwym
 * @Date: 2025-04-01
 * @Link: https://leetcode.cn/problems/solving-questions-with-brainpower/
 */
public class Code2140_SolvingQuestionsWithBrainpower {
    /**
     * 计算解决题目数组可以获得的最大分数。
     *
     * @param questions 二维数组，每个元素包含两个整数：questions[i][0]是解决该题获得的分数，
     *                  questions[i][1]是解决该题后必须跳过的题目数量。
     * @return 可获得的最大分数。
     */
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        // 动态规划数组，dp[i]表示从第i题开始能获得的最大分数
        long[] dp = new long[n + 1];
        dp[n] = 0; // 边界条件：最后一题之后没有题目可选，得分为0

        // 从后向前遍历，计算每个位置的最大得分
        // 对比两种选择：解决当前题（得分+后续跳过后的最优解）或直接跳过当前题
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], questions[i][0] + dp[Math.min(i + 1 + questions[i][1], n)]);
        }

        return dp[0];
    }

}
