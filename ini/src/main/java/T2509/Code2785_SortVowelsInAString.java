package T2509;

import java.util.Arrays;

/**
 * @Description: 排序
 * @Author: iniwym
 * @Date: 2025-09-11
 * @Link: https://leetcode.cn/problems/sort-vowels-in-a-string/
 */
public class Code2785_SortVowelsInAString {

    /**
     * 对字符串中的元音字母进行排序
     * 该函数将字符串中的所有元音字母提取出来，按字母顺序排序后，
     * 再放回原来的位置，保持非元音字母的位置不变
     *
     * @param S 输入的字符串
     * @return 元音字母已排序的字符串
     */
    public String sortVowels(String S) {
        // 提取所有元音字母
        StringBuilder vowels = new StringBuilder();
        char[] s = S.toCharArray();
        for (char ch : s) {
            char c = Character.toLowerCase(ch);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels.append(ch);
            }
        }

        // 对提取的元音字母进行排序
        char[] sortedVowels = vowels.toString().toCharArray();
        Arrays.sort(sortedVowels);

        // 将排序后的元音字母重新放回原字符串中元音字母的位置
        int j = 0;
        for (int i = 0; i < s.length; i++) {
            char c = Character.toLowerCase(s[i]);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                s[i] = sortedVowels[j++];
            }
        }
        return new String(s);
    }

}
