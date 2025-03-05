package T2503;

/**
 * @Description: 子数组最大累加和
 * @Author: iniwym
 * @Date: 2025-03-05
 * @Link: https://leetcode.cn/problems/maximum-sum-circular-subarray/
 */
public class Code0918_MaximumSumCircularSubarray {
    /**
     * 计算循环数组中的最大子数组和
     * 该方法同时考虑了两种情况：
     * 1. 子数组在原数组中不跨越末尾和开头，即为标准的最大子数组和问题
     * 2. 子数组跨越末尾和开头，通过计算整个数组的和减去最小子数组和来获得最大子数组和
     *
     * @param nums 输入的循环数组
     * @return 返回两种情况下的最大子数组和
     */
    public int maxSubarraySumCircular(int[] nums) {

        // 初始化最大子数组和、当前累加的最大子数组和、最小子数组和、当前累加的最小子数组和以及数组总和
        int sumMax = nums[0];
        int tempMax = nums[0];
        int sumMin = nums[0];
        int tempMin = nums[0];
        int all = nums[0];
        // 遍历数组，计算两种情况下的子数组和
        for (int i = 1; i < nums.length; i++) {
            // 累加数组总和
            all += nums[i];
            // 计算当前累加的最大子数组和
            tempMax = Math.max(tempMax + nums[i], nums[i]);
            // 更新最大子数组和
            sumMax = Math.max(sumMax, tempMax);
            // 计算当前累加的最小子数组和
            tempMin = Math.min(tempMin + nums[i], nums[i]);
            // 更新最小子数组和
            sumMin = Math.min(sumMin, tempMin);
        }
        // 判断是否所有元素都为负数，如果是，则返回最大子数组和，否则返回两种情况下的最大值
        return all == sumMin ? sumMax : Math.max(sumMax, all - sumMin);
    }
}
