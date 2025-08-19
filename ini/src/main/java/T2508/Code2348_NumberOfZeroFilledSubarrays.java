package T2508;

/**
 * @Description: 等差数列求和
 * @Author: iniwym
 * @Date: 2025-08-19
 * @Link: https://leetcode.cn/problems/number-of-zero-filled-subarrays/
 */
public class Code2348_NumberOfZeroFilledSubarrays {

    /**
     * 计算数组中所有由0组成的连续子数组的个数
     * 对于长度为n的连续0序列，其能组成的子数组个数为n*(n+1)/2
     *
     * @param nums 输入的整数数组
     * @return 所有由0组成的连续子数组的总个数
     */
    public long zeroFilledSubarray(int[] nums) {

        long ans = 0l;
        long len = 0l;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                len++;
            } else {
                // 遇到非0元素，计算当前连续0序列能组成的子数组个数
                ans += (len * (len + 1)) / 2;
                len = 0;
            }

        }
        // 处理数组末尾的连续0序列
        ans += (len * (len + 1)) / 2;

        return ans;
    }


}
