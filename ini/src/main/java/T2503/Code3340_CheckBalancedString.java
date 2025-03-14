package T2503;

/**
 * @Description: 运算
 * @Author: iniwym
 * @Date: 2025-03-14
 * @Link: https://leetcode.cn/problems/check-balanced-string/
 */
public class Code3340_CheckBalancedString {

    /**
     * 检查输入的数字字符串是否平衡，即奇数位置和偶数位置的数字之和相等。
     * 偶数索引位置（0, 2, 4...）的数字之和与奇数索引位置（1, 3, 5...）的数字之和相等时返回true。
     *
     * @param num 需要检查的数字字符串，不能为空
     * @return 是否平衡的布尔值
     */
    public boolean isBalanced(String num) {
        if (num.length() == 0) {
            return false;
        }
        int sum1 = 0;
        int sum2 = 0;
        char[] chars = num.toCharArray();

        // 遍历每个字符，根据索引奇偶性分别累加到sum1和sum2
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                sum2 += (int) chars[i] - '0';
            } else {
                sum1 += (int) chars[i] - '0';
            }
        }

        return sum1 == sum2;
    }

}
