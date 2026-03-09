package T2603;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2026-03-09
 * @Link: https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-i/
 */
public class Code3129_FindAllPossibleStableBinaryArraysI {

    private static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] memo = new int[zero + 1][one + 1][2];
        for (int[][] m : memo) {
            for (int[] m2 : m) {
                Arrays.fill(m2, -1); // -1 表示没有计算过
            }
        }
        return (dfs(zero, one, 0, limit, memo) + dfs(zero, one, 1, limit, memo)) % MOD;
    }

    /**
     * 使用记忆化搜索计算稳定二进制数组的数量
     *
     * @param i     剩余需要放置的 0 的个数
     * @param j     剩余需要放置的 1 的个数
     * @param k     上一个放置的数字（0 或 1），用于判断当前可以放置什么数字
     * @param limit 连续相同数字的最大长度限制
     * @param memo  记忆化数组，存储已经计算过的状态，避免重复计算
     * @return 满足条件的稳定二进制数组的数量
     */
    private int dfs(int i, int j, int k, int limit, int[][][] memo) {
        // 递归边界：所有 0 都已放置完毕，检查最后放置的是否为 1 且剩余的 1 不超过限制
        if (i == 0) {
            return k == 1 && j <= limit ? 1 : 0;
        }
        // 递归边界：所有 1 都已放置完毕，检查最后放置的是否为 0 且剩余的 0 不超过限制
        if (j == 0) {
            return k == 0 && i <= limit ? 1 : 0;
        }
        // 如果该状态已经计算过，直接返回缓存的结果
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        // 当前需要放置数字，根据上一个放置的数字分类讨论
        if (k == 0) {
            // 上一个放置的是 0，现在可以继续放 0、改放 1，或者减去超过限制的非法情况
            // + MOD 保证答案非负
            memo[i][j][k] = (int) (((long) dfs(i - 1, j, 0, limit, memo) + dfs(i - 1, j, 1, limit, memo) +
                    (i > limit ? MOD - dfs(i - limit - 1, j, 1, limit, memo) : 0)) % MOD);
        } else {
            // 上一个放置的是 1，现在可以继续放 1、改放 0，或者减去超过限制的非法情况
            memo[i][j][k] = (int) (((long) dfs(i, j - 1, 0, limit, memo) + dfs(i, j - 1, 1, limit, memo) +
                    (j > limit ? MOD - dfs(i, j - limit - 1, 0, limit, memo) : 0)) % MOD);
        }
        return memo[i][j][k];
    }

}
