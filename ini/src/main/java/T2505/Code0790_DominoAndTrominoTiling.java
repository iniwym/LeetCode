package T2505;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-05-05
 * @Link: https://leetcode.cn/problems/domino-and-tromino-tiling/
 */
public class Code0790_DominoAndTrominoTiling {
    public static int[] dp = new int[1001];
    public static int MOD = 1000000007;

    public static int numTilings(int n) {
        Arrays.fill(dp, -1);
        return f(n);
    }

    /**
     * 计算第n项的值
     * 该函数使用递归和记忆化技术来计算特定序列的第n项值
     * 它通过存储已经计算过的值来避免重复计算，从而提高效率
     *
     * @param n 要计算的项的索引，索引从0开始
     * @return 第n项的值如果n小于0，则返回0
     */
    public static int f(int n) {
        // 如果输入的n小于0，则返回0，因为序列不定义负数索引的值
        if (n < 0) return 0;

        // 检查是否已经计算过第n项的值，如果是，则直接返回该值
        if (dp[n] != -1) {
            return dp[n];
        }

        // 特殊情况处理：当n为0、1或2时，直接返回预定义的值
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        // 计算第n项的值，使用递归公式：f(n) = 2 * f(n-1) + f(n-3)
        // 对结果取模以防止整数溢出
        int ans = (2 * f(n - 1) % MOD + f(n - 3) % MOD) % MOD;

        // 将计算结果存储在dp数组中，以便后续查询
        dp[n] = ans;

        // 返回计算结果
        return ans;
    }
}
