package T2507;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-07-17
 * @Link: https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-ii/
 */
public class Code3202_FindTheMaximumLengthOfValidSubsequenceIi {
    /**
     * 计算在给定约束条件下数组中最长子序列的长度
     * <p>
     * 该函数使用动态规划的方法，找出数组中满足特定条件的最长子序列长度。
     * 约束条件为：子序列中相邻元素的模k结果不能相同。
     *
     * @param nums 输入整数数组，包含需要处理的数字
     * @param k    模数，用于计算每个元素的模值
     * @return 满足条件的最长子序列长度
     */
    public int maximumLength(int[] nums, int k) {
        // 动态规划表，dp[i][j]表示前一个元素模k为i，当前元素模k为j时的最大长度
        int[][] dp = new int[k][k];
        int res = 0;  // 存储最终结果

        // 遍历输入数组中的每个数字
        for (int num : nums) {
            num %= k;  // 计算当前数字的模k值

            // 更新动态规划表
            for (int prev = 0; prev < k; prev++) {
                // 状态转移：当前长度等于前一个状态长度加1
                dp[prev][num] = dp[num][prev] + 1;
                // 更新最大长度结果
                res = Math.max(res, dp[prev][num]);
            }
        }
        return res;
    }
}
