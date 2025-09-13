package T2509;

/**
 * @Description: 计数
 * @Author: iniwym
 * @Date: 2025-09-13
 * @Link: https://leetcode.cn/problems/find-most-frequent-vowel-and-consonant/
 */
public class Code3541_FindMostFrequentVowelAndConsonant {

    /**
     * 计算字符串中元音字母最大频次与辅音字母最大频次之和
     *
     * @param s 输入的字符串，只包含小写字母
     * @return 返回元音字母最大出现次数与辅音字母最大出现次数的和
     */
    public int maxFreqSum(String s) {
        // 统计每个字母的出现频次
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        // 分别找出元音字母和辅音字母的最大出现频次
        int temp1 = 0; // 元音字母的最大频次
        int temp2 = 0; // 辅音字母的最大频次

        for (int i = 0; i < 26; i++) {
            if (i == ('a' - 'a') || i == ('e' - 'a') || i == ('i' - 'a')
                    || i == ('o' - 'a') || i == ('u' - 'a')) {
                temp1 = Math.max(temp1, arr[i]);
            } else {
                temp2 = Math.max(temp2, arr[i]);
            }
        }

        return temp1 + temp2;
    }

}
