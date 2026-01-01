package T2601;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2026-01-01
 * @Link: https://leetcode.cn/problems/plus-one/
 */
public class Code0066_PlusOne {

    /**
     * 对数组表示的数字进行加一操作
     *
     * @param digits 表示数字的数组，每个元素代表一位数字，从高位到低位排列
     * @return 加一后的新数组
     */
    public int[] plusOne(int[] digits) {
        // 从最后一位开始处理加一操作
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        // 如果所有位都是9，需要扩展数组长度
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

}
