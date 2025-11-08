package T2511;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2025-11-08
 * @Link: https://leetcode.cn/problems/minimum-one-bit-operations-to-make-integers-zero/
 */
public class Code1611_MinimumOneBitOperationsToMakeIntegersZero {

    /**
     * 计算将整数n变为0所需的最少一位运算次数
     * <p>
     * 该函数使用递归方法，基于格雷码的性质来计算最少操作次数。
     * 对于一个k位的二进制数，将其变为0最多需要2^k - 1次操作。
     *
     * @param n 待处理的非负整数
     * @return 将n变为0所需的最少一位运算次数
     */
    public int minimumOneBitOperations(int n) {
        // 基础情况：如果n为0，则不需要任何操作
        if (n == 0) {
            return 0;
        }

        // 计算n的二进制表示的位数k
        int k = 32 - Integer.numberOfLeadingZeros(n);

        // 递归计算：2^k - 1减去将(n - 2^(k-1))变为0所需的操作次数
        return (1 << k) - 1 - minimumOneBitOperations(n - (1 << (k - 1)));
    }


}
