package T2504;

/**
 * @Description: 数学计算
 * @Author: iniwym
 * @Date: 2025-04-27
 * @Link: https://leetcode.cn/problems/count-subarrays-of-length-three-with-a-condition/
 */
public class Code3392_CountSubarraysOfLengthThreeWithACondition {

    /**
     * 统计满足特定条件的连续三元素子数组的数量。
     *
     * @param nums 输入的整数数组
     * @return 满足条件的子数组数目，即当 (nums[i] + nums[i+2]) * 2 等于 nums[i+1] 时计数加1
     */
    public int countSubarrays(int[] nums) {
        int n = nums.length;
        int ans = 0;

        // 遍历所有可能的三元素子数组（i, i+1, i+2）
        for (int i = 0; i < n - 2; i++) {
            if ((nums[i] + nums[i + 2]) * 2 == nums[i + 1]) {
                ans++;
            }
        }

        return ans;
    }

}
