package T2506;

import java.util.Arrays;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2025-06-30
 * @Link: https://leetcode.cn/problems/longest-harmonious-subsequence/
 */
public class Code0594_LongestHarmoniousSubsequence {
    /**
     * 查找最长和谐子序列的长度。
     * 和谐子序列定义为序列中的最大值和最小值的差正好为1。
     *
     * @param nums 输入数组，可能包含任意整数
     * @return 最长和谐子序列的长度。如果输入数组为空或null，则返回0
     * <p>
     * 实现思路：
     * 1. 先对数组排序，以便使用双指针技术
     * 2. 使用滑动窗口（双指针）技术，维护窗口内的最大值和最小值的差不超过1
     * 3. 当窗口满足条件时，记录窗口长度
     */
    public int findLHS(int[] nums) {
        // 处理空数组或null的情况
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 排序数组以便使用双指针技术
        Arrays.sort(nums);
        int maxLength = 0;
        int left = 0;

        // 使用滑动窗口技术查找最长和谐子序列
        for (int right = 1; right < nums.length; right++) {
            // 调整左指针，直到窗口内的差值不超过1
            while (nums[right] - nums[left] > 1) {
                left++;
            }

            // 当窗口满足和谐子序列条件时，更新最大长度
            if (nums[right] - nums[left] == 1) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }


}
