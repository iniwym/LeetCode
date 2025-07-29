package T2507;

/**
 * @Description: LogTrick
 * @Author: iniwym
 * @Date: 2025-07-29
 * @Link: https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or/
 */
public class Code2411_SmallestSubarraysWithMaximumBitwiseOr {
    /**
     * 计算每个位置作为右端点时，能够获得最大按位或值的最短子数组长度
     *
     * @param nums 输入的整数数组
     * @return 长度为n的数组，ans[i]表示以索引i为右端点的子数组中，
     * 能够获得最大按位或值的最短子数组长度
     */
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) { // 计算右端点为 i 的子数组的或值
            int x = nums[i];
            ans[i] = 1; // 子数组的长度至少是 1
            // 循环直到 nums[j] 无法增大，其左侧元素也无法增大
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; j--) {
                nums[j] |= x; // nums[j] 增大，现在 nums[j] = 原数组 nums[j] 到 nums[i] 的或值
                ans[j] = i - j + 1; // nums[j] 最后一次增大时的子数组长度就是答案
            }
        }
        return ans;
    }

}
