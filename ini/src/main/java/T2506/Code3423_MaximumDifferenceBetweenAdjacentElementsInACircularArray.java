package T2506;

/**
 * @Description: 数组遍历
 * @Author: iniwym
 * @Date: 2025-06-12
 * @Link: https://leetcode.cn/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/
 */
public class Code3423_MaximumDifferenceBetweenAdjacentElementsInACircularArray {

    /**
     * 计算数组中任意两个相邻元素的最大差值
     * 此方法通过遍历数组，比较每对相邻元素的差值的绝对值，从而找到最大的差值
     * 特别地，它还考虑了数组中第一个元素和最后一个元素之间的差值，以处理循环或首尾相连的情形
     *
     * @param nums 一个整数数组，不为空且至少包含两个元素
     * @return 返回任意两个相邻元素的最大差值
     */
    public int maxAdjacentDistance(int[] nums) {

        // 初始化最大差值为0
        int ans = 0;

        // 遍历数组，计算每对相邻元素的差值的绝对值，并更新最大差值
        for (int i = 1; i < nums.length; i++) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[i - 1]));
        }

        // 考虑数组首尾元素之间的差值，更新最大差值
        ans = Math.max(ans, Math.abs(nums[nums.length - 1] - nums[0]));

        // 返回计算得到的最大差值
        return ans;
    }

}
