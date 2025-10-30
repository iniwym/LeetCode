package T2510;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2025-10-30
 * @Link: https://leetcode.cn/problems/smallest-number-with-all-set-bits/
 */
public class Code3370_SmallestNumberWithAllSetBits {

    /**
     * 计算大于等于给定数字n的最小的形如2^k-1的数
     *
     * @param n 输入的正整数
     * @return 大于等于n的最小的2^k-1形式的数
     */
    public int smallestNumber(int n) {
        // 计算n的二进制位数
        int bitLength = 32 - Integer.numberOfLeadingZeros(n);
        // 构造一个bitLength位全为1的二进制数，即2^bitLength-1
        return (1 << bitLength) - 1;
    }

}
