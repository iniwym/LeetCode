package T2603;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-03-11
 * @Link: https://leetcode.cn/problems/complement-of-base-10-integer/
 */
public class Code1009_ComplementOfBase10Integer {

    /**
     * 计算给定整数的二进制补码（按位取反）
     *
     * @param n 输入的非负整数，需要对其二进制表示进行按位取反
     * @return 返回按位取反后的结果整数
     * <p>
     * 算法思路:
     * 1. 特殊情况：0 的补码为 1
     * 2. 一般情况：构造一个与 n 的有效位数相同的全 1 掩码，然后与 n 进行异或运算
     */
    public int bitwiseComplement(int n) {
        // 处理特殊情况：0 的二进制补码为 1
        if (n == 0) {
            return 1;
        }

        // 计算 n 的二进制表示的有效位数（去除前导零后的位数）
        int w = 32 - Integer.numberOfLeadingZeros(n);

        // 构造全 1 掩码并与 n 进行异或运算得到补码
        // (1 << w) - 1 生成 w 个连续的 1，与 n 异或实现按位取反
        return ((1 << w) - 1) ^ n;
    }


}
