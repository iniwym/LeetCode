package T2503;

/**
 * @Description: 回文字符串
 * @Author: iniwym
 * @Date: 2025-03-05
 * @Link: https://leetcode.cn/problems/break-a-palindrome/
 */
public class Code1328_BreakAPalindrome {
    /**
     * 将给定的回文字符串破坏，使其不再成为回文，同时找到字典序最小的破坏方法
     * 如果输入字符串长度为1，无法破坏回文特性，返回空字符串
     * 通过替换字符串中的字符来破坏回文特性，优先替换第一个不是'a'的字符为'a'，
     * 如果字符串中所有字符都是'a'，则将最后一个字符替换为'b'
     *
     * @param palindrome 给定的回文字符串
     * @return 破坏回文特性后的字符串，如果无法破坏则返回空字符串
     */
    public String breakPalindrome(String palindrome) {
        // 获取字符串长度
        int n = palindrome.length();

        // 如果字符串长度为1，无法破坏回文特性，返回空字符串
        if (n == 1) {
            return "";
        }

        // 将字符串转换为字符数组，便于后续操作
        char[] chars = palindrome.toCharArray();

        // 只需检查字符串的前半部分，因为回文字符串的后半部分是前半部分的逆序
        int len = n / 2;
        for (int i = 0; i < len; i++) {
            // 如果当前字符不是'a'，将其替换为'a'，并返回修改后的字符串
            if (chars[i] != 'a') {
                chars[i] = 'a';
                return new String(chars);
            }
        }

        // 如果所有字符都是'a'，将最后一个字符替换为'b'，以破坏回文特性
        chars[n - 1] = 'b';
        return new String(chars);
    }
}
