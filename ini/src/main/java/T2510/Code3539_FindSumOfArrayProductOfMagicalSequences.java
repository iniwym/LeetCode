package T2510;

import java.util.Arrays;

/**
 * @Description: 记忆化搜索
 * @Author: iniwym
 * @Date: 2025-10-12
 * @Link: https://leetcode.cn/problems/find-sum-of-array-product-of-magical-sequences/
 */
public class Code3539_FindSumOfArrayProductOfMagicalSequences {

    private static final int MOD = 1_000_000_007;
    private static final int MX = 31;

    private static final long[] F = new long[MX]; // F[i] = i!
    private static final long[] INV_F = new long[MX]; // INV_F[i] = i!^-1

    static {
        F[0] = 1;
        for (int i = 1; i < MX; i++) {
            F[i] = F[i - 1] * i % MOD;
        }

        INV_F[MX - 1] = pow(F[MX - 1], MOD - 2);
        for (int i = MX - 1; i > 0; i--) {
            INV_F[i - 1] = INV_F[i] * i % MOD;
        }
    }

    /**
     * 快速幂运算函数，计算 x 的 n 次方模 MOD。
     *
     * @param x 底数
     * @param n 指数
     * @return x^n % MOD
     */
    private static long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

    /**
     * 主函数，计算满足条件的魔法和的数量。
     *
     * @param m    表示指数上限
     * @param k    目标二进制中 1 的个数
     * @param nums 输入数组，包含若干整数
     * @return 满足条件的魔法和的数量模 MOD 的结果
     */
    public int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;
        int[][] powV = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            powV[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                powV[i][j] = (int) ((long) powV[i][j - 1] * nums[i] % MOD);
            }
        }

        int[][][][] memo = new int[n][m + 1][m / 2 + 1][k + 1];
        for (int[][][] a : memo) {
            for (int[][] b : a) {
                for (int[] c : b) {
                    Arrays.fill(c, -1);
                }
            }
        }
        return (int) (dfs(0, m, 0, k, powV, memo) * F[m] % MOD);
    }

    /**
     * 使用深度优先搜索（DFS）和记忆化搜索来计算满足条件的组合数。
     *
     * @param i     当前处理到 nums 数组中的第 i 个元素
     * @param leftM 剩余可选的指数总和
     * @param x     当前累积的 S 值右移后的部分
     * @param leftK 剩余需要达到的 1 的个数
     * @param powV  预处理的幂值数组
     * @param memo  记忆化搜索的缓存表
     * @return 满足当前状态下的组合数
     */
    private long dfs(int i, int leftM, int x, int leftK, int[][] powV, int[][][][] memo) {
        int c1 = Integer.bitCount(x);
        if (c1 + leftM < leftK) { // 可行性剪枝：当前状态不可能满足要求
            return 0;
        }
        if (i == powV.length || leftM == 0 || leftK == 0) { // 边界条件：无法继续选数字
            return leftM == 0 && c1 == leftK ? 1 : 0;
        }
        if (memo[i][leftM][x][leftK] != -1) {
            return memo[i][leftM][x][leftK];
        }
        long res = 0;
        for (int j = 0; j <= leftM; j++) { // 枚举当前元素 nums[i] 被使用的次数 j
            // 这 j 个下标 i 对 S 的贡献是 j * pow(2, i)
            // 由于 x = S >> i，转化成对 x 的贡献是 j
            int bit = (x + j) & 1; // 取最低位，提前从 leftK 中减去，其余进位到 x 中
            long r = dfs(i + 1, leftM - j, (x + j) >> 1, leftK - bit, powV, memo);
            res = (res + r * powV[i][j] % MOD * INV_F[j]) % MOD;
        }
        return memo[i][leftM][x][leftK] = (int) res;
    }
}

