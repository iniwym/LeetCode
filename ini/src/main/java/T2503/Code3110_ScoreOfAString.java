package T2503;

/**
 * @Description: 字符串运算
 * @Author: iniwym
 * @Date: 2025-03-15
 * @Link: https://leetcode.cn/problems/score-of-a-string/
 */
public class Code3110_ScoreOfAString {

    /**
     * 计算字符串的得分，得分等于相邻字符在字母表中的位置差的绝对值之和。
     *
     * @param s 输入的字符串
     * @return 相邻字符位置差绝对值的总和
     */
    public int scoreOfString(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        // 遍历字符串中的每个相邻字符对，计算它们的字母位置差的绝对值并将结果累加到sum中
        for (int i = 1; i < chars.length; i++) {
            sum += Math.abs((chars[i] - 'a') - (chars[i - 1] - 'a'));
        }
        return sum;
    }


}
