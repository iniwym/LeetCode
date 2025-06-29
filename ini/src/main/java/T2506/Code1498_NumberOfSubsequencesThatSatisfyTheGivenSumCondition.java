package T2506;

import java.util.Arrays;

/**
 * @Description: 排序 + 双指针
 * @Author: iniwym
 * @Date: 2025-06-29
 * @Link: https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
 */
public class Code1498_NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
    /**
     * 计算满足条件的子序列数量（子序列的最小值+最大值 <= target）
     *
     * @param nums   输入数组，将被排序处理
     * @param target 目标阈值
     * @return 满足条件的子序列数量（对 1000000007 取模）
     */
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        int MOD = 1000000007;  // 取模基数
        Arrays.sort(nums);     // 排序数组以使用双指针
        int ans = 0;           // 结果计数器
        int l = 0;             // 左指针
        int r = n - 1;         // 右指针

        // 双指针遍历：当 nums[l] + nums[r] <= target 时，
        // 所有以 nums[l] 为最小值、nums[r] 为最大值的子序列都满足条件
        while (l <= r) {
            if (nums[l] + nums[r] <= target) {
                ans += pow(2, r - l, MOD);  // 计算子集数量：2^(r-l)
                ans %= MOD;                 // 防止溢出
                l++;                        // 尝试更大的最小值
            } else {
                r--;                        // 尝试更小的最大值
            }
        }
        return ans;
    }

    /**
     * 快速幂算法（计算 base^exponent % mod）
     *
     * @param base     基数
     * @param exponent 指数
     * @param mod      取模值
     * @return 计算结果
     */
    private int pow(int base, int exponent, int mod) {
        int result = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {           // 当前二进制位为1
                result = (int) ((long) result * base % mod);  // 累乘当前base
            }
            base = (int) ((long) base * base % mod);  // base平方
            exponent >>= 1;                           // 右移一位
        }
        return result;
    }

}
