package T2506;

/**
 * @Description: 组合数学
 * @Author: iniwym
 * @Date: 2025-06-17
 * @Link: https://leetcode.cn/problems/count-the-number-of-arrays-with-k-matching-adjacent-elements/
 */
public class Code3405_CountTheNumberOfArraysWithKMatchingAdjacentElements {
    // 定义模数常量，用于取模操作，以防止计算过程中的整数溢出
    private static final int MOD = 1000000007;

    // 定义最大的n值，用于预计算阶乘和逆阶乘数组的大小
    private static final int MAX_N = 100000;

    // fact数组用于存储预计算的阶乘结果
    private static long[] fact;

    // invFact数组用于存储预计算的阶乘逆元结果
    private static long[] invFact;

    // 标记是否已经完成阶乘和逆阶乘的预计算
    private static boolean precomputed = false;


    /**
     * 计算满足特定条件的数组数量
     * 该方法用于计算给定参数n, m, k下，满足特定条件的数组的数量
     * 特定条件包括数组的长度、元素的范围以及数组中特定模式的出现次数
     *
     * @param n 数组的长度
     * @param m 数组中元素的范围
     * @param k 数组中特定模式的出现次数
     * @return 满足条件的数组数量
     */
    public int countGoodArrays(int n, int m, int k) {
        // 初始化参数数组，便于后续处理
        int[] flerdovika = {n, m, k};

        // 检查是否需要预处理，如果未预处理，则进行预处理并标记为已处理
        if (!precomputed) {
            precompute();
            precomputed = true;
        }

        // 如果k的值大于n-1，则无法满足条件，直接返回0
        if (k > n - 1) {
            return 0;
        }

        // 计算组合数C(n-1, k)，作为最终结果的一部分
        long part1 = comb(n - 1, k);
        // 计算m对MOD取模的结果，作为最终结果的一部分
        long part2 = m % MOD;
        // 计算(m-1)^(n-k-1)的结果，作为最终结果的一部分
        long part3 = pow(m - 1, (long) n - k - 1);
        // 结合上述三部分结果，计算最终满足条件的数组数量
        return (int) ((part1 * part2 % MOD) * part3 % MOD);
    }


    /**
     * 预计算阶乘和逆阶乘以提高后续计算效率
     * <p>
     * 本方法初始化两个数组，fact和invFact，分别用于存储阶乘和逆阶乘的结果
     * 阶乘用于快速计算组合数，而逆阶乘用于在模运算下快速计算组合数的逆元
     */
    private void precompute() {
        // 初始化阶乘数组
        fact = new long[MAX_N + 1];
        // 初始化逆阶乘数组
        invFact = new long[MAX_N + 1];
        // 定义0的阶乘为1
        fact[0] = 1;
        // 计算所有阶乘值
        for (int i = 1; i <= MAX_N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        // 计算最大N的逆阶乘值
        invFact[MAX_N] = pow(fact[MAX_N], MOD - 2);
        // 逆序计算逆阶乘值
        for (int i = MAX_N; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }
    }

    /**
     * 计算base的exp次幂，并对结果进行模运算以防止溢出
     * 该方法主要用于需要进行大量幂运算的场景，通过模运算优化结果，避免数据溢出
     *
     * @param base 底数，表示幂运算的基础数值
     * @param exp  指数，表示底数自乘的次数
     * @return 返回base的exp次幂对MOD取模后的结果如果exp为负数，则返回0
     */
    private long pow(long base, long exp) {
        // 当指数为负数时，幂运算结果为分数，此处返回0作为错误处理
        if (exp < 0) {
            return 0;
        }
        // 当底数为0时，根据指数情况返回1或0，避免未定义情况
        if (base == 0) {
            return exp == 0 ? 1 : 0;
        }
        // 对底数进行模运算，优化后续计算
        base %= MOD;
        // 初始化结果为1，即base^0的结果
        long result = 1;
        // 循环计算，直到指数为0
        while (exp > 0) {
            // 当指数的最低位为1时，将当前底数纳入结果计算
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }
            // 底数自乘并对MOD取模，为下一轮迭代做准备
            base = base * base % MOD;
            // 指数右移一位，相当于除以2
            exp >>= 1;
        }
        // 返回最终的计算结果
        return result;
    }


    /**
     * 计算组合数 C(n, k)
     * 组合数是指从n个不同元素中，任取k(k≤n)个元素并成一组，叫做从n个元素中取出k个元素的一个组合
     * 计算公式为 C(n, k) = n! / (k! * (n-k)!)
     * 此方法使用了预计算的阶乘及其逆元来提高计算效率，适用于需要多次计算组合数的场景
     *
     * @param n 总元素个数，必须非负
     * @param k 组合中的元素个数，必须满足 0 <= k <= n
     * @return 返回组合数 C(n, k)，如果 k < 0 或 k > n 则返回 0
     */
    private long comb(int n, int k) {
        // 检查参数是否满足组合数的定义，如果不满足则返回0
        if (k < 0 || k > n) {
            return 0;
        }
        // 使用预计算的阶乘和逆元来计算组合数，使用模运算防止结果溢出
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

}
