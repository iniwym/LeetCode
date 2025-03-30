package T2503;

/**
 * @Description: 百分比的计算
 * @Author: iniwym
 * @Date: 2025-03-31
 * @Link: https://leetcode.cn/problems/percentage-of-letter-in-string/
 */
public class Code2278_PercentageOfLetterInString {
    /**
     * 计算指定字符在字符串中出现的百分比（向下取整）。
     *
     * @param s      输入的字符串
     * @param letter 要统计的字符
     * @return 字符在字符串中出现次数占总长度的百分比，结果向下取整
     */
    public static int percentageLetter(String s, char letter) {

        int count = 0;
        int length = s.length();
        // 统计指定字符在字符串中的出现次数
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == letter) {
                count++;
            }
        }
        return (int) (count * 100.0 / length);
    }

}
