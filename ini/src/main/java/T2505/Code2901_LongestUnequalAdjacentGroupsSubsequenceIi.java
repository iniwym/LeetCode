package T2505;

import java.util.*;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-05-16
 * @Link: https://leetcode.cn/problems/longest-unequal-adjacent-groups-subsequence-ii/
 */
public class Code2901_LongestUnequalAdjacentGroupsSubsequenceIi {

    /**
     * 获取在最长子序列中的单词列表
     * 最长子序列由不重复的汉明距离为1的单词组成
     *
     * @param words  单词数组
     * @param groups 组号数组，表示每个单词所属的组
     * @return 最长子序列中的单词列表
     */
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        // 单词数量
        int n = words.length;
        // dp数组用于记录以每个单词结尾的最长子序列长度
        int[] dp = new int[n];
        // pre数组用于记录每个单词在最长子序列中的前一个单词的索引
        int[] pre = new int[n];
        // 初始化pre数组为-1，表示没有前一个单词
        Arrays.fill(pre, -1);
        // 初始化dp数组为1，因为每个单词自身可以构成一个子序列
        Arrays.fill(dp, 1);

        // 遍历所有单词，计算最长子序列长度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 如果两个单词不属于同一组且汉明距离为1，则尝试更新最长子序列长度
                if (groups[i] != groups[j] && checkHamming(words[i], words[j])) {
                    // 如果通过当前单词可以得到更长的子序列，则更新dp数组和pre数组
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
        }

        // 找到最大的dp值和对应的endIndex
        int maxLen = 0;
        int endIndex = -1;
        for (int i = 0; i < n; i++) {
            // 如果当前单词的子序列长度大于已知的最大长度，则更新最大长度和结束索引
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                endIndex = i;
            } else if (dp[i] == maxLen) {
                // 如果当前单词的子序列长度等于最大长度，选择最后一个出现的单词作为结束索引
                endIndex = i;
            }
        }

        // 回溯路径，找到最长子序列中单词的索引
        List<Integer> indices = new ArrayList<>();
        while (endIndex != -1) {
            indices.add(endIndex);
            endIndex = pre[endIndex];
        }
        // 反转索引列表，使其按照正确的顺序
        Collections.reverse(indices);

        // 根据索引列表构建结果单词列表
        List<String> result = new ArrayList<>();
        for (int idx : indices) {
            result.add(words[idx]);
        }
        return result;
    }

    /**
     * 检查两个字符串是否满足Hamming距离为1的条件
     * Hamming距离是指两个字符串对应位置上不同字符的个数
     * 此方法用于确定两个字符串是否正好在一个字符上有差异
     *
     * @param a 第一个字符串
     * @param b 第二个字符串
     * @return 如果两个字符串的Hamming距离为1，则返回true；否则返回false
     */
    private boolean checkHamming(String a, String b) {
        // 确保两个字符串长度相同，长度不同则无法比较
        if (a.length() != b.length()) {
            return false;
        }
        // 计数器，用于记录两个字符串在对应位置上不同字符的数量
        int cnt = 0;
        // 遍历字符串的每个字符
        for (int i = 0; i < a.length(); i++) {
            // 如果当前字符不同，计数器加一
            if (a.charAt(i) != b.charAt(i)) {
                cnt++;
                // 如果不同字符的数量超过1，则不满足Hamming距离为1的条件，直接返回false
                if (cnt > 1) {
                    return false;
                }
            }
        }
        // 最后检查是否正好有一个字符不同
        return cnt == 1;
    }

}
