package T2601;

import java.util.Arrays;

/**
 * @Description: 数组遍历
 * @Author: iniwym
 * @Date: 2026-01-24
 * @Link: https://leetcode.cn/problems/minimize-maximum-pair-sum-in-array/
 */
public class Code1877_MinimizeMaximumPairSumInArray {

    /**
     * 找到数组中数对和的最大值的最小可能值
     * 通过将数组排序后配对首尾元素来实现最优配对策略
     *
     * @param nums 输入的整数数组，长度为偶数
     * @return 返回所有配对中的最大数对和
     */
    public int minPairSum(int[] nums) {
        int ans = 0;

        // 对数组进行升序排序，为后续配对做准备
        Arrays.sort(nums);

        // 配对策略：将最小的与最大的配对，第二小的与第二大配对，以此类推
        // 这样可以使得最大数对和尽可能小
        for (int i = 0; i < nums.length / 2; i++) {

            ans = Math.max(ans, nums[i] + nums[nums.length - 1 - i]);
        }

        return ans;

    }


}
