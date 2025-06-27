package T2506;

/**
 * @Description: 枚举排列 + 判断子序列
 * @Author: iniwym
 * @Date: 2025-06-27
 * @Link: https://leetcode.cn/problems/longest-subsequence-repeated-k-times/
 */
public class Code2014_LongestSubsequenceRepeatedKTimes {
    private char[] ans; // 存储当前找到的最长有效子序列
    private int ansLen = 0; // 当前有效子序列的长度

    /**
     * 查找字符串中能生成k次重复子序列的最大字典序字符串
     *
     * @param S 原始字符串
     * @param k 需要重复的次数
     * @return 最长且字典序最大的可重复k次的子序列
     */
    public String longestSubsequenceRepeatedK(String S, int k) {
        char[] s = S.toCharArray();

        // 统计每个字符出现的次数
        int[] cnt = new int[26];
        for (char c : s) {
            cnt[c - 'a']++;
        }

        // 构建候选字符数组，按逆序排列以优先考虑字典序大的字符
        StringBuilder tmp = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            String c = String.valueOf((char) ('a' + i));
            for (int repeatIndex = 0; repeatIndex < cnt[i] / k; repeatIndex++) {
                tmp.append((char) ('a' + i));
            }
        }
        char[] a = tmp.toString().toCharArray();

        // 初始化答案数组并开始全排列枚举
        ans = new char[a.length];
        permute(a, k, s);

        return new String(ans, 0, ansLen);
    }

    /**
     * 全排列枚举入口函数
     *
     * @param nums 候选字符数组
     * @param k    需要匹配的次数
     * @param s    原始字符串
     */
    private void permute(char[] nums, int k, char[] s) {
        int n = nums.length;
        char[] path = new char[n]; // 当前排列路径
        boolean[] onPath = new boolean[n]; // 标记哪些字符已经在排列中
        dfs(0, nums, path, onPath, k, s);
    }

    /**
     * 深度优先搜索进行全排列枚举
     *
     * @param i      当前处理的位置（排列中的第i位）
     * @param nums   候选字符数组
     * @param path   当前排列路径
     * @param onPath 标记哪些字符已经在排列中
     * @param k      需要匹配的次数
     * @param s      原始字符串
     */
    private void dfs(int i, char[] nums, char[] path, boolean[] onPath, int k, char[] s) {
        // 处理当前排列
        process(path, i, k, s);

        if (i == nums.length) {
            return;
        }

        // 枚举所有可能的字符填入当前位置
        for (int j = 0; j < nums.length; j++) {
            // 跳过已使用的字符以及去重逻辑
            if (onPath[j] || j > 0 && nums[j] == nums[j - 1] && !onPath[j - 1]) {
                continue;
            }
            path[i] = nums[j]; // 填入排列
            onPath[j] = true; // 标记为已使用
            dfs(i + 1, nums, path, onPath, k, s); // 递归处理下一个位置
            onPath[j] = false; // 回溯，恢复现场
        }
    }

    /**
     * 处理当前排列，判断是否更新最优解
     *
     * @param seq    当前排列的字符序列
     * @param seqLen 当前排列的长度
     * @param k      需要匹配的次数
     * @param s      原始字符串
     */
    private void process(char[] seq, int seqLen, int k, char[] s) {
        // 如果当前排列更优，则尝试更新最优解
        if (seqLen > ansLen || seqLen == ansLen && compare(seq, ans, ansLen) > 0) {
            if (isSubsequence(seq, seqLen, k, s)) {
                System.arraycopy(seq, 0, ans, 0, seqLen);
                ansLen = seqLen;
            }
        }
    }

    /**
     * 比较两个字符数组的字典序大小
     *
     * @param a 第一个字符数组
     * @param b 第二个字符数组
     * @param n 比较的字符数量
     * @return 正数表示a大，负数表示b大，0表示相等
     */
    private int compare(char[] a, char[] b, int n) {
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return a[i] - b[i];
            }
        }
        return 0;
    }

    /**
     * 判断某个序列重复k次后是否是原始字符串的子序列
     *
     * @param seq 待检查的序列
     * @param n   序列的长度
     * @param k   需要重复的次数
     * @param s   原始字符串
     * @return 是否是子序列
     */
    private boolean isSubsequence(char[] seq, int n, int k, char[] s) {
        int i = 0;
        for (char c : s) {
            if (seq[i % n] == c) {
                i++;
                if (i == n * k) { // 成功匹配了k次重复序列
                    return true;
                }
            }
        }
        return false;
    }
}

