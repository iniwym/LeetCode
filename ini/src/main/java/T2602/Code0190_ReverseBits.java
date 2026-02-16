package T2602;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-16
 * @Link: https://leetcode.cn/problems/reverse-bits/
 */
public class Code0190_ReverseBits {

    private static final int m0 = 0x55555555; // 01010101 ...
    private static final int m1 = 0x33333333; // 00110011 ...
    private static final int m2 = 0x0f0f0f0f; // 00001111 ...
    private static final int m3 = 0x00ff00ff; // 00000000111111110000000011111111

    /**
     * 反转一个32位整数的二进制位。
     * <p>
     * 该方法通过分步交换位的方式实现二进制位的反转：
     * 1. 先交换相邻的位；
     * 2. 再交换每两个位组成的组；
     * 3. 接着交换每四个位组成的组；
     * 4. 然后交换每八个位组成的组；
     * 5. 最后交换高16位和低16位。
     *
     * @param n 输入的32位整数
     * @return 反转二进制位后的整数
     */
    public int reverseBits(int n) {
        // 交换相邻位
        n = n >>> 1 & m0 | (n & m0) << 1;

        // 两个两个交换
        n = n >>> 2 & m1 | (n & m1) << 2;

        // 四个四个交换
        n = n >>> 4 & m2 | (n & m2) << 4;

        // 八个八个交换
        n = n >>> 8 & m3 | (n & m3) << 8;

        // 交换高低16位
        return n >>> 16 | n << 16;
    }


}
