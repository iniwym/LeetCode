package T2506;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-06-21
 * @Link: https://leetcode.cn/problems/minimum-deletions-to-make-string-k-special/
 */
public class Code3085_MinimumDeletionsToMakeStringKSpecial {
    /**
     * 计算将字符串转换为满足特定条件的字符串所需的最小删除次数
     *
     * @param word 输入的字符串，每个字符都将被转换为一个计数
     * @param k    允许的字符频率差异的最大值
     * @return 返回所需的最小删除次数
     */
    public int minimumDeletions(String word, int k) {
        // 创建一个数组来统计每个字母的出现次数
        int[] nums = new int[26];
        // 遍历字符串，统计每个字符的出现次数
        for (int i = 0; i < word.length(); i++) {
            nums[word.charAt(i) - 'a']++;
        }

        // 初始化答案为最大整数值，以便后续进行最小值比较
        int ans = Integer.MAX_VALUE;
        // 遍历每个字符的出现次数
        for (int i = 0; i < 26; i++) {
            // 临时变量用于计算当前字符作为基准时的删除次数
            int temp = 0;
            // 再次遍历所有字符的出现次数，计算需要删除的字符数
            for (int j = 0; j < 26; j++) {
                // 如果当前字符的出现次数比基准字符的出现次数加k还要多，则需要删除多余的字符
                if (nums[j] > nums[i] + k) {
                    temp += nums[j] - k - nums[i];
                    // 如果当前字符的出现次数比基准字符的出现次数少，则全部删除
                } else if (nums[j] < nums[i]) {
                    temp += nums[j];
                }
            }
            // 更新答案为所有情况中的最小值
            ans = Math.min(ans, temp);
        }
        // 返回最小的删除次数
        return ans;
    }

}
