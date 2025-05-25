package T2505;

/**
 * @Description: 回文
 * @Author: iniwym
 * @Date: 2025-05-25
 * @Link: https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words/
 */
public class Code2131_LongestPalindromeByConcatenatingTwoLetterWords {

    /**
     * 计算给定字符串数组中所有回文对的最大长度之和
     * 回文对是指一对字符串，其中一个字符串是另一个字符串的反转，且两者合并后形成回文
     *
     * @param words 字符串数组，包含可能形成回文对的单词
     * @return 所有回文对的最大长度之和
     */
    public int longestPalindrome(String[] words) {
        // 创建一个二维数组，用于统计每个单词的出现次数
        int[][] nums = new int[26][26];

        // 遍历字符串数组，统计每个单词的出现次数
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            nums[chars[0] - 'a'][chars[1] - 'a']++;
        }

        // 初始化回文对的最大长度之和为0
        int ans = 0;
        // 定义一个布尔变量，用于标记是否存在中心为单个字符的回文对
        boolean temp = false;

        // 遍历二维数组，计算所有回文对的最大长度之和
        for (int i = 0; i < 26; i++) {
            // 如果当前字符组成的单词出现次数大于0
            if (nums[i][i] > 0) {
                // 计算回文对的最大长度之和，每个回文对长度为4的倍数
                ans += (nums[i][i] / 2) * 4;
                // 如果当前字符组成的单词出现次数为奇数，则标记存在中心为单个字符的回文对
                if (nums[i][i] % 2 != 0) {
                    temp = true;
                }
            }
        }
        // 根据是否存在中心为单个字符的回文对，更新回文对的最大长度之和
        ans += temp ? 2 : 0;

        // 遍历二维数组，计算不同字符组成的回文对的最大长度之和
        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                // 计算回文对的最大长度之和，每个回文对长度为4的倍数
                ans += (Math.min(nums[i][j], nums[j][i])) * 4;
            }
        }
        // 返回回文对的最大长度之和
        return ans;
    }

}
