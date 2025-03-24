package T2503;

/**
 * @Description: String的startsWith方法
 * @Author: iniwym
 * @Date: 2025-03-24
 * @Link: https://leetcode.cn/problems/count-prefixes-of-a-given-string/
 */
public class Code2255_CountPrefixesOfAGivenString {

    /**
     * 统计数组中作为字符串s前缀的单词数量。
     *
     * @param words 需要检查的单词数组
     * @param s     目标字符串，用于检查前缀
     * @return 符合条件的单词数量（即s以该单词作为前缀的单词数量）
     */
    public static int countPrefixes(String[] words, String s) {
        int ans = 0;
        /* 遍历每个单词并检查是否为s的前缀 */
        for (String word : words) {
            if (s.startsWith(word)) {
                ans += 1;
            }
        }
        return ans;
    }


}
