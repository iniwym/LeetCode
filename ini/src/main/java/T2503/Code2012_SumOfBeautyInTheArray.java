package T2503;

/**
 * @Description: 前缀最大值+后缀最小值
 * @Author: iniwym
 * @Date: 2025-03-11
 * @Link: https://leetcode.cn/problems/sum-of-beauty-in-the-array/
 */
public class Code2012_SumOfBeautyInTheArray {

    /**
     * 计算数组元素的美丽值总和。美丽值定义为：
     * 1. 若元素同时满足大于前i-1个元素的最大值且小于后n-i个元素的最小值，则贡献2分
     * 2. 若元素仅满足大于前一个元素且小于后一个元素，则贡献1分
     *
     * @param nums 输入的整数数组
     * @return 美丽值总和
     */
    public static int sumOfBeauties(int[] nums) {

        int ans = 0;
        int n = nums.length;
        int[] preMax = new int[n]; // 前缀最大值数组
        int[] behindMin = new int[n]; // 后缀最小值数组

        // 初始化前缀最大值数组
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }

        // 初始化后缀最小值数组
        behindMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            behindMin[i] = Math.min(behindMin[i + 1], nums[i]);
        }

        // 遍历计算中间元素的美丽值（首尾元素无法满足条件）
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > preMax[i - 1] && nums[i] < behindMin[i + 1]) {
                ans += 2;
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
        }
        return ans;

    }

}
