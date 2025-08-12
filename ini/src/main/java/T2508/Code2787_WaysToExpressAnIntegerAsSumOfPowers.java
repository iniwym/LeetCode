package T2508;

/**
 * @Description: 0/1背包
 * @Author: iniwym
 * @Date: 2025-08-12
 * @Link: https://leetcode.cn/problems/ways-to-express-an-integer-as-sum-of-powers/
 */
public class Code2787_WaysToExpressAnIntegerAsSumOfPowers {

    /**
     * 计算将整数n表示为不同正整数的x次幂之和的方案数
     *
     * @param n 目标整数
     * @param x 幂次
     * @return 将n表示为不同正整数的x次幂之和的方案数，结果对1,000,000,007取模
     */
    int numberOfWays(int n, int x) {
        long[] f = new long[n + 1];
        f[0] = 1;
        // 本题数据范围小，Math.pow 的计算结果一定准确
        for (int i = 1; Math.pow(i, x) <= n; i++) {
            int v = (int) Math.pow(i, x);
            // 动态规划：计算使用前i个x次幂组成各个数值的方案数
            for (int s = n; s >= v; s--) {
                f[s] += f[s - v];
            }
        }
        return (int) (f[n] % 1_000_000_007);
    }


}
