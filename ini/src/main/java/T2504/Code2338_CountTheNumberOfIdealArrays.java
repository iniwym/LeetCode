package T2504;

import java.util.Arrays;

/**
 * @Description: 组合数学
 * @Author: iniwym
 * @Date: 2025-04-22
 * @Link: https://leetcode.cn/problems/count-the-number-of-ideal-arrays/
 */
public class Code2338_CountTheNumberOfIdealArrays {

    private static final int MOD = 1000000007; // 定义模数

    /**
     * 计算长度为n且所有元素均不超过maxValue的理想数组的数量。理想数组定义为每个元素是前一个元素的倍数的数组。
     * 返回值为结果模某个大质数后的结果（MOD常量未在代码中显示）。
     *
     * @param n        数组的长度
     * @param maxValue 数组元素的最大允许值
     * @return 符合条件的数组数量模MOD的结果
     */
    public static int idealArrays(int n, int maxValue) {
        // 预处理最小质因数数组，用于快速质因数分解
        int[] minPrime = new int[maxValue + 1];
        Arrays.fill(minPrime, -1);
        for (int i = 2; i <= maxValue; i++) {
            if (minPrime[i] == -1) { // i是质数
                minPrime[i] = i; // 质数的最小质因数是自己
                // 标记i的倍数的最小质因数
                for (long j = (long) i * i; j <= maxValue; j += i) {
                    if (minPrime[(int) j] == -1) {
                        minPrime[(int) j] = i;
                    }
                }
            }
        }

        // 计算所有数质因数分解后的最大指数总和，确定阶乘数组的大小
        int maxE = 0;
        for (int i = maxValue; i >= 1; i--) {
            int temp = i;
            int count = 0;
            while (temp > 1) {
                int p = minPrime[temp];
                while (temp % p == 0) {
                    temp /= p;
                    count++;
                }
            }
            maxE = Math.max(maxE, count);
        }

        // 预处理阶乘和逆阶乘数组，用于快速计算组合数
        int maxFact = (n - 1) + maxE; // 最大组合数参数
        long[] fact = new long[maxFact + 1]; // 阶乘数组
        long[] invFact = new long[maxFact + 1]; // 逆阶乘数组
        fact[0] = 1;
        for (int i = 1; i <= maxFact; i++) {
            fact[i] = fact[i - 1] * i % MOD; // 计算阶乘
        }
        invFact[maxFact] = pow(fact[maxFact], MOD - 2); // 费马小定理求逆元
        for (int i = maxFact - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD; // 递推逆阶乘
        }

        long ans = 0;
        // 遍历每个可能的最后一个元素k，计算其贡献
        for (int k = 1; k <= maxValue; k++) {
            if (k == 1) { // 特殊情况，只有全1的数组
                ans = (ans + 1) % MOD;
                continue;
            }
            int temp = k;
            long product = 1; // 贡献累积
            // 分解k的质因数
            while (temp > 1) {
                int p = minPrime[temp];
                int e = 0; // 当前质因子的指数
                while (temp % p == 0) {
                    temp /= p;
                    e++;
                }
                // 计算组合数C(n-1 + e, e)
                int a = n - 1 + e;
                long c = fact[a] * invFact[n - 1] % MOD * invFact[e] % MOD;
                product = product * c % MOD; // 各质因子组合数相乘
            }
            ans = (ans + product) % MOD; // 累加k的贡献
        }

        return (int) ans;
    }


    // 快速幂计算a^n % MOD
    private static long pow(long a, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(idealArrays(2, 5)); // 输出10
        System.out.println(idealArrays(5, 3)); // 输出11
    }
}
