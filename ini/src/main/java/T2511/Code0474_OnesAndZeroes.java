package T2511;

import java.util.Arrays;

/**
 * @Description: 记忆化搜索
 * @Author: iniwym
 * @Date: 2025-11-11
 * @Link: https://leetcode.cn/problems/ones-and-zeroes/
 */
public class Code0474_OnesAndZeroes {

    /**
     * 找到最多能组成的字符串数量，使得这些字符串中0的总数不超过m且1的总数不超过n
     *
     * @param strs 输入的二进制字符串数组
     * @param m    最多允许的0的个数
     * @param n    最多允许的1的个数
     * @return 满足条件的最大字符串数量
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int k = strs.length;
        // 预处理每个字符串中0的个数
        int[] cnt0 = new int[k];
        for (int i = 0; i < k; i++) {
            cnt0[i] = (int) strs[i].chars().filter(ch -> ch == '0').count();
        }

        // 创建记忆化数组，memo[i][j][k]表示考虑前i个字符串，使用j个0和k个1能得到的最大字符串数
        int[][][] memo = new int[strs.length][m + 1][n + 1];
        for (int[][] mat : memo) {
            for (int[] arr : mat) {
                Arrays.fill(arr, -1); // -1 表示没有计算过
            }
        }
        return dfs(k - 1, m, n, strs, cnt0, memo);
    }

    /**
     * 使用深度优先搜索和记忆化技术解决0-1背包问题的变种
     * 在给定的字符串数组中选择子集，使得选出的字符串中0的总数不超过j且1的总数不超过k的情况下，求最多能选多少个字符串
     *
     * @param i    当前考虑的字符串索引（从后往前）
     * @param j    剩余可用的0的个数
     * @param k    剩余可用的1的个数
     * @param strs 字符串数组，每个字符串只包含'0'和'1'
     * @param cnt0 每个字符串中'0'的个数数组
     * @param memo 记忆化数组，memo[i][j][k]表示在前i+1个字符串中，使用j个0和k个1最多能选的字符串个数
     * @return 在当前约束条件下最多能选择的字符串个数
     */
    private int dfs(int i, int j, int k, String[] strs, int[] cnt0, int[][][] memo) {
        // 边界条件：没有字符串可选择了
        if (i < 0) {
            return 0;
        }

        // 记忆化：如果之前已经计算过该状态，直接返回结果
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }

        // 不选择当前字符串strs[i]的情况
        int res = dfs(i - 1, j, k, strs, cnt0, memo);

        // 计算当前字符串中1的个数
        int cnt1 = strs[i].length() - cnt0[i];

        // 如果剩余容量足够容纳当前字符串，则考虑选择它的情况
        if (j >= cnt0[i] && k >= cnt1) {
            // 选择当前字符串，更新剩余容量并递归求解
            res = Math.max(res, dfs(i - 1, j - cnt0[i], k - cnt1, strs, cnt0, memo) + 1);
        }

        // 将结果存储到记忆化数组中并返回
        return memo[i][j][k] = res;
    }


}
