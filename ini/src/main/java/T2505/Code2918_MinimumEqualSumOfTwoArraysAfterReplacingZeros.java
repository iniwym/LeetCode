package T2505;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2025-05-10
 * @Link: https://leetcode.cn/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/
 */
public class Code2918_MinimumEqualSumOfTwoArraysAfterReplacingZeros {

    /**
     * 计算两个数组中替换0后可以达到的最小公共和，若无法达成则返回 -1。
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 最小公共和，或 -1 表示无法达成
     */
    public long minSum(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        long sum1 = 0;
        long sum2 = 0;
        long zero1 = 0;
        long zero2 = 0;

        for (int i = 0; i < n1; i++) {
            if (nums1[i] == 0) {
                zero1++;
            } else {
                sum1 += nums1[i];
            }
        }

        for (int i = 0; i < n2; i++) {
            if (nums2[i] == 0) {
                zero2++;
            } else {
                sum2 += nums2[i];
            }
        }

        // 缓存常用表达式，避免重复计算
        long total1IfZeroUsed = sum1 + zero1;
        long total2IfZeroUsed = sum2 + zero2;

        if (zero1 != 0 && zero2 != 0) {
            if (sum1 == sum2) {
                return sum1 + Math.max(zero1, zero2);
            } else {
                return Math.max(total1IfZeroUsed, total2IfZeroUsed);
            }
        } else if (zero1 == 0 && zero2 != 0) {
            return total1IfZeroUsed >= total2IfZeroUsed ? sum1 : -1;
        } else if (zero1 != 0 && zero2 == 0) {
            return total2IfZeroUsed >= total1IfZeroUsed ? sum2 : -1;
        } else {
            return sum1 == sum2 ? sum1 : -1;
        }
    }

}
