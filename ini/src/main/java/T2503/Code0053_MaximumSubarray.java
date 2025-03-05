package T2503;

/**
 * @Description: 子数组最大累加和
 * @Author: iniwym
 * @Date: 2025-03-04
 * @Link: https://leetcode.cn/problems/maximum-subarray/
 */
public class Code0053_MaximumSubarray {
    /**
     * 寻找连续子数组中的最大和
     * 本方法使用动态规划的思想，通过迭代计算以每个元素结尾的子数组的最大和，从而得到整个数组中的最大和子数组
     *
     * @param nums 输入的整数数组，不能为空
     * @return 返回连续子数组中的最大和
     */
    public int maxSubArray(int[] nums) {
        // 以i结尾的子数组的最大和
        int ans = nums[0];
        // 记录当前子数组的最大和
        int tempMax = nums[0];
        // 从第二个元素开始遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 决定是否将当前元素与之前的子数组最大和相加，或重新开始一个新的子数组
            tempMax = nums[i] + Math.max(tempMax, 0);
            // 更新找到的最大和
            ans = Math.max(ans, tempMax);
        }
        // 返回最大和
        return ans;
    }

}
