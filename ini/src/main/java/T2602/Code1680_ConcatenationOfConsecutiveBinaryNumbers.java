package T2602;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-28
 * @Link: https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers/
 */
public class Code1680_ConcatenationOfConsecutiveBinaryNumbers {

    /**
     * 计算从1到n的所有整数的二进制表示连接后的结果，并对结果取模。
     *
     * @param n 正整数，表示计算范围的上限
     * @return 连接后二进制数对应的十进制值对1, 000, 000, 007取模的结果
     */
    public int concatenatedBinary(int n) {
        // 定义模数常量，用于防止结果溢出
        final int MOD = 1_000_000_007;
        // 初始化结果变量，使用long类型以避免中间计算溢出
        long ans = 0;

        // 遍历从1到n的每个整数
        for (int i = 1; i <= n; i++) {
            // 计算当前数字i的二进制位数
            int w = 32 - Integer.numberOfLeadingZeros(i);
            // 将当前结果左移w位并与i进行按位或操作，然后对MOD取模
            ans = (ans << w | i) % MOD;
        }

        // 返回最终结果，转换为int类型
        return (int) ans;
    }


}
