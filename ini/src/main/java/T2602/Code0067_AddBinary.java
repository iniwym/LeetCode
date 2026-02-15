package T2602;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-15
 * @Link: https://leetcode.cn/problems/add-binary/
 */
public class Code0067_AddBinary {

    /**
     * 将两个二进制字符串相加，返回它们的和（也是二进制字符串）。
     *
     * @param a 第一个二进制字符串
     * @param b 第二个二进制字符串
     * @return 两个二进制字符串的和，以二进制字符串形式返回
     */
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1; // 指向字符串 a 的末尾
        int j = b.length() - 1; // 指向字符串 b 的末尾
        int carry = 0;          // 进位标志

        // 从右到左逐位相加
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i--) - '0'; // 将字符转换为数字
            }

            if (j >= 0) {
                sum += b.charAt(j--) - '0'; // 将字符转换为数字
            }

            result.append(sum % 2); // 当前位的结果
            carry = sum / 2;        // 更新进位
        }

        // 结果是逆序的，需要反转
        return result.reverse().toString();
    }

}
