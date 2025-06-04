package T2506;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-06-04
 * @Link: https://leetcode.cn/problems/find-the-lexicographically-largest-string-from-the-box-i/
 */
public class Code3403_FindTheLexicographicallyLargestStringFromTheBoxI {

    /**
     * 根据给定的单词和朋友数量，返回一个特定的字符串
     * 这个函数的目的是在给定的单词中找到一个子串，这个子串的长度等于朋友的数量，
     * 并且这个子串是在字典序上最大的
     *
     * @param word       输入的单词
     * @param numFriends 朋友的数量
     * @return 在字典序上最大的子串
     */
    public String answerString(String word, int numFriends) {
        // 当只有一个朋友时，直接返回原始单词
        if (numFriends == 1) {
            return word;
        }
        // 获取单词的长度
        int n = word.length();

        // 初始化答案字符串为空
        String ans = "";

        // 遍历单词中的每个字符
        for (int i = 0; i < n; i++) {
            // 获取从当前位置开始，长度不超过剩余字符数量的子串
            // 子串的长度确保不会超过朋友的数量
            String sub = word.substring(i, Math.min(i + n - numFriends + 1, n));
            // 比较当前子串和已有的答案字符串，保留字典序较大的
            if (sub.compareTo(ans) > 0) {
                ans = sub;
            }
        }

        // 返回最终的答案字符串
        return ans;

    }
}
