package T2503;

/**
 * @Description: 子数组最大累加和
 * @Author: iniwym
 * @Date: 2025-03-05
 * @Link: https://leetcode.cn/problems/house-robber/
 */
public class Code0198_HouseRobber {
    /**
     * 解决房屋偷盗问题，返回在不触动警报的情况下能偷到的最大金额
     *
     * @param nums 一个整数数组，每个元素代表每个房屋能偷到的金额
     * @return 在不触动警报的情况下能偷到的最大金额
     */
    public int rob(int[] nums) {
        // 房屋的数量
        int n = nums.length;

        // 如果只有一个房屋，直接返回该房屋的金额
        if (n == 1) {
            return nums[0];
        }
        // 如果有两个房屋，返回金额较大的那个房屋的金额
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // dp[i]表示以i结尾的最大收益
        int dp[] = new int[n + 1];
        // 初始化动态规划数组
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 遍历每个房屋，计算最大收益
        for (int i = 2; i < n; i++) {
            // 不要当前位置，此位置的收益等于dp[i - 1]
            // 要当前位置，此位置的收益等于nums[i] + dp[i - 2]
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        // 返回最后一个房屋的最大收益
        return dp[n - 1];
    }
}
