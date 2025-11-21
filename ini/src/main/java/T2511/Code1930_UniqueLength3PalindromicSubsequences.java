package T2511;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-11-21
 * @Link: https://leetcode.cn/problems/unique-length-3-palindromic-subsequences/
 */
public class Code1930_UniqueLength3PalindromicSubsequences {

    /**
     * 计算字符串中回文子序列的数量
     * 回文子序列的长度为3，形式为 aba，其中首尾字符相同，中间字符可以任意
     * 算法思路：枚举所有可能的首尾字符，然后统计夹在首尾字符之间的不同字符数量
     *
     * @param s 输入字符串
     * @return 长度为3的回文子序列的数量
     */
    public int countPalindromicSubsequence(String s) {
        int ans = 0;
        // 枚举所有小写字母作为回文子序列的首尾字符
        for (char alpha = 'a'; alpha <= 'z'; alpha++) {
            int i = s.indexOf(alpha); // 找到该字符在字符串中最左边的位置
            if (i < 0) { // 如果字符串中不存在该字符，跳过
                continue;
            }
            int j = s.lastIndexOf(alpha); // 找到该字符在字符串中最右边的位置

            // 统计在位置i和j之间不同字符的种类数
            boolean[] has = new boolean[26];
            for (int k = i + 1; k < j; k++) {
                int mid = s.charAt(k) - 'a';
                if (!has[mid]) {
                    has[mid] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

}
