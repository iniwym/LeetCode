package T2509;

/**
 * @Description: 位运算
 * @Author: iniwym
 * @Date: 2025-09-06
 * @Link: https://leetcode.cn/problems/minimum-operations-to-make-array-elements-zero/
 */
public class Code3495_MinimumOperationsToMakeArrayElementsZero {

    /**
     * 计算处理查询所需的最小操作数
     *
     * @param queries 查询数组，每个查询包含两个整数[start, end]
     * @return 所有查询所需操作数的总和
     */
    public long minOperations(int[][] queries) {
        long ans = 0;
        // 遍历所有查询，累加每个查询所需的操作数
        for (int[] q : queries) {
            ans += (f(q[1]) - f(q[0] - 1) + 1) / 2;
        }
        return ans;
    }


    /**
     * 计算[1,n]范围内所有元素的操作次数之和
     * 该函数通过数学公式优化计算，避免了对每个元素逐一计算操作次数
     *
     * @param n 范围的上界，计算[1,n]区间内所有数字的操作次数总和
     * @return 返回[1, n]范围内所有元素的操作次数总和
     */
    private long f(int n) {
        // 计算n的二进制位数
        int m = 32 - Integer.numberOfLeadingZeros(n);
        long res = 0;

        // 对于完整的二进制位数区间，使用公式计算操作次数之和
        for (int i = 1; i < m; i++) {
            res += (long) (i + 1) / 2 << (i - 1);
        }

        // 处理不完整的最高位区间部分
        return res + (long) (m + 1) / 2 * (n + 1 - (1 << m >> 1));
    }

}
