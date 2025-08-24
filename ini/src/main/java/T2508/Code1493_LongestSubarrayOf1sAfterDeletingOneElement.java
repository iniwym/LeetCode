package T2508;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2025-08-24
 * @Link: https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/
 */
public class Code1493_LongestSubarrayOf1sAfterDeletingOneElement {
    /**
     * 找到最长的连续子数组，该子数组最多包含一个0，返回删除一个元素后的最大长度
     *
     * @param nums 输入的二进制数组，只包含0和1
     * @return 删除一个元素后，最长全为1的子数组长度
     */
    public int longestSubarray(int[] nums) {
        int ans = 0;
        int cnt0 = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 1. 入，nums[right] 进入窗口
            cnt0 += 1 - nums[right]; // 维护窗口中的 0 的个数
            // 当窗口中0的个数超过1时，需要收缩窗口
            while (cnt0 > 1) { // 不符合题目要求
                // 2. 出，nums[left] 离开窗口
                cnt0 -= 1 - nums[left]; // 维护窗口中的 0 的个数
                left++;
            }
            // 3. 更新答案，注意不是 right-left+1，因为我们要删掉一个数
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

}
