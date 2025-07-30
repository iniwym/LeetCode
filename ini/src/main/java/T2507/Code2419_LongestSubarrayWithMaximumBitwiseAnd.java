package T2507;

import java.util.Arrays;

/**
 * @Description: 数组遍历
 * @Author: iniwym
 * @Date: 2025-07-30
 * @Link: https://leetcode.cn/problems/longest-subarray-with-maximum-bitwise-and/
 */
public class Code2419_LongestSubarrayWithMaximumBitwiseAnd {

    /**
     * 找到数组中最大元素的最长连续子数组长度
     *
     * @param nums 输入的整数数组
     * @return 返回包含最大元素的最长连续子数组的长度
     */
    public int longestSubarray(int[] nums) {
        // 找到数组中的最大值
        int mx = Arrays.stream(nums).max().getAsInt();
        int ans = 0;
        int cnt = 0;

        // 遍历数组，统计连续最大值的长度
        for (int x : nums) {
            if (x == mx) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0; // 连续 mx 断开了，重新统计
            }
        }
        return ans;
    }

}
