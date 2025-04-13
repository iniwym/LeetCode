package T2504;

/**
 * @Description: 快速幂算法
 * @Author: iniwym
 * @Date: 2025-04-13
 * @Link: https://leetcode.cn/problems/count-good-numbers/
 */
public class Code1922_CountGoodNumbers {

    private static final long MOD = 1000000007L;

    public static int countGoodNumbers(long n) {
        long evenPositions = (n + 1) / 2;  // 偶数下标的数量
        long oddPositions = n / 2;         // 奇数下标的数量
        long part1 = powMod(5, evenPositions, MOD);
        long part2 = powMod(4, oddPositions, MOD);
        return (int) ((part1 * part2) % MOD);
    }

    /**
     * 计算 (base^exponent) mod mod 使用快速幂算法。
     *
     * @param base     底数
     * @param exponent 指数，必须为非负数
     * @param mod      模数，必须为正数
     * @return 计算结果 (base^exponent) mod mod
     */
    private static long powMod(long base, long exponent, long mod) {
        long result = 1;
        base = base % mod;
        // 快速幂算法主循环，逐步分解指数为二进制位并计算结果
        while (exponent > 0) {
            // 如果当前指数的最低位为1，则将当前base乘到结果中
            if ((exponent & 1) == 1) {
                result = (result * base) % mod;
            }
            // 平方当前base，对应指数的下一位
            base = (base * base) % mod;
            // 右移指数，处理下一位
            exponent >>= 1;
        }
        return result;
    }


    public static void main(String[] args) {
        /**
         * 25(11001) = 16 + 8 + 1
         * 4^25 = 4^(16+8+1) = 4^16 * 4^8 * 4^1
         * 5^25 = 5^(16+8+1) = 5^16 * 5^8 * 5^1
         * 564908303
         */
        System.out.println(countGoodNumbers(50));
    }

}
