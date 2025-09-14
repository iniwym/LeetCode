package T2509;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-09-14
 * @Link: https://leetcode.cn/problems/vowel-spellchecker/
 */
public class Code0966_VowelSpellchecker {

    class Solution {
        /**
         * 拼写检查器函数，根据给定的单词列表对查询单词进行纠错处理
         *
         * @param wordlist 单词字典列表，用于匹配和纠错的基准
         * @param queries  需要进行拼写检查的查询单词数组
         * @return 返回与查询单词对应的结果数组，每个元素为匹配到的正确单词或空字符串
         */
        public String[] spellchecker(String[] wordlist, String[] queries) {
            int n = wordlist.length;
            Set<String> origin = new HashSet<>(Arrays.asList(wordlist));
            Map<String, String> lowerToOrigin = new HashMap<>(n); // 预分配空间
            Map<String, String> vowelToOrigin = new HashMap<>(n);

            // 从后向前遍历单词列表，建立不同规则的映射关系
            // 这样可以确保当有多个匹配项时，返回的是最先出现的单词
            for (int i = n - 1; i >= 0; i--) {
                String s = wordlist[i];
                String lower = s.toLowerCase();
                lowerToOrigin.put(lower, s); // 建立小写形式到原始单词的映射
                vowelToOrigin.put(replaceVowels(lower), s); // 建立元音替换形式到原始单词的映射
            }

            // 处理每个查询单词，按照优先级进行匹配
            for (int i = 0; i < queries.length; i++) {
                String q = queries[i];
                if (origin.contains(q)) { // 完全匹配
                    continue;
                }
                String lower = q.toLowerCase();
                if (lowerToOrigin.containsKey(lower)) { // 不区分大小写的匹配
                    queries[i] = lowerToOrigin.get(lower);
                } else { // 不区分大小写+元音模糊匹配
                    queries[i] = vowelToOrigin.getOrDefault(replaceVowels(lower), "");
                }
            }
            return queries;
        }

        /**
         * 将字符串中的元音字母替换为问号
         *
         * @param str 待处理的字符串
         * @return 替换元音字母后的字符串
         */
        private String replaceVowels(String str) {
            char[] s = str.toCharArray();
            // 遍历字符数组，将元音字母替换为问号
            for (int i = 0; i < s.length; ++i) {
                char c = s[i];
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    s[i] = '?';
                }
            }
            return new String(s);
        }
    }

}
