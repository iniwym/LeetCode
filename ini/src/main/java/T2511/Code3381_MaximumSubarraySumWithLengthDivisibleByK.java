package T2511;

import java.util.Arrays;

/**
 * @Description: 前缀和
 * @Author: iniwym
 * @Date: 2025-11-27
 * @Link: https://leetcode.cn/problems/maximum-subarray-sum-with-length-divisible-by-k/
 */
public class Code3381_MaximumSubarraySumWithLengthDivisibleByK {

    /**
     * 计算数组中满足特定条件的最大子数组和
     * 该函数寻找所有长度为k的倍数的子数组中，具有最大和的子数组
     *
     * @param nums 输入的整数数组
     * @param k    子数组长度必须是k的倍数
     * @return 满足条件的最大子数组和
     */
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构建前缀和数组，sum[i]表示前i个元素的和
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        // minS[i]存储模k余数为i的所有前缀和中的最小值
        long[] minS = new long[k];
        Arrays.fill(minS, Long.MAX_VALUE / 2); // 防止下面减法溢出

        long ans = Long.MIN_VALUE;
        // 遍历所有前缀和，计算满足条件的最大子数组和
        for (int j = 0; j < sum.length; j++) {
            int i = j % k;
            ans = Math.max(ans, sum[j] - minS[i]);
            minS[i] = Math.min(minS[i], sum[j]);
        }
        return ans;
    }

}
