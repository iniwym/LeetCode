package T2509;

/**
 * @Description: 字符串
 * @Author: iniwym
 * @Date: 2025-09-15
 * @Link: https://leetcode.cn/problems/maximum-number-of-words-you-can-type/
 */
public class Code1935_MaximumNumberOfWordsYouCanType {
    /**
     * 计算可以完整输入的单词数量
     * 给定一段文本和损坏的字母，返回文本中可以完整输入的单词数量
     *
     * @param text          输入的文本，包含多个由空格分隔的单词
     * @param brokenLetters 损坏的字母字符串，包含所有无法输入的字母
     * @return 可以完整输入的单词数量
     */
    public int canBeTypedWords(String text, String brokenLetters) {

        int ans = 0;
        char[] chars = brokenLetters.toCharArray();
        String[] str = text.split(" ");

        // 遍历每个单词，检查是否包含损坏的字母
        for (String s : str) {
            for (char c : chars) {
                if (s.contains(c + "")) {
                    ans++;
                    break;
                }
            }
        }
        return str.length - ans;
    }

}
