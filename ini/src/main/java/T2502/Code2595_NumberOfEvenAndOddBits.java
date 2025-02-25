package T2502;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2025-02-20
 * @Link: https://leetcode.cn/problems/number-of-even-and-odd-bits/
 */
public class Code2595_NumberOfEvenAndOddBits {
    /**
     * 统计一个整数的二进制表示中奇数位和偶数位上1的个数
     *
     * @param n 输入的整数
     * @return 包含两个整数的数组，第一个整数表示偶数位上1的个数，第二个整数表示奇数位上1的个数
     */
    public int[] evenOddBit(int n) {

        // 初始化结果数组
        int[] res = new int[2];

        // 如果输入为0，直接返回结果数组，因为0的二进制表示中没有1
        if (n == 0) {
            return res;
        }

        // 初始化偶数位和奇数位上1的个数
        int even = 0;
        int odd = 0;

        // 遍历整数的二进制表示中的每一位，从最高位开始
        for (int i = 31; i >= 0; i--) {
            // 使用位运算检查当前位是否为1
            int temp = ((1 << i) & n) == 0 ? 0 : 1;
            // 如果当前位是偶数位，则累加到even变量
            if (i % 2 == 0) {
                even += temp;
            } else {
                // 如果当前位是奇数位，则累加到odd变量
                odd += temp;
            }
        }
        // 将统计结果存储到结果数组中
        res[0] = even;
        res[1] = odd;
        // 返回结果数组
        return res;

    }

}
