package T2510;

/**
 * @Description: 字符串遍历
 * @Author: iniwym
 * @Date: 2025-10-23
 * @Link: https://leetcode.cn/problems/check-if-digits-are-equal-in-string-after-operations-i/
 */
public class Code3461_CheckIfDigitsAreEqualInStringAfterOperationsI {

    /**
     * 判断字符串经过特定运算后首尾字符是否相同
     * 该函数对输入字符串进行多轮运算：每轮将相邻两个字符的数值相加后取个位数，
     * 直到只剩两个字符为止，最后判断这两个字符是否相同。
     *
     * @param s 输入的字符串，应只包含数字字符
     * @return 如果经过运算后剩余的两个字符相同则返回true，否则返回false
     */
    public boolean hasSameDigits(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();

        // 进行len-2轮运算，每轮减少一个字符
        for (int i = 1; i <= len - 2; i++) {
            char[] temp = new char[len];

            // 计算相邻字符数值相加后的个位数
            for (int j = len - i - 1; j >= 0; j--) {
                temp[j] = (char) ((chars[j + 1] + chars[j]) % 10);
            }
            chars = temp;
        }

        // 判断最终剩余的两个字符是否相同
        return chars[0] == chars[1];
    }

}
