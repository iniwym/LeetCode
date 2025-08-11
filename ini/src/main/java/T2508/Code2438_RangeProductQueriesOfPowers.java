package T2508;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 取模
 * @Author: iniwym
 * @Date: 2025-08-11
 * @Link: https://leetcode.cn/problems/range-product-queries-of-powers/
 */
public class Code2438_RangeProductQueriesOfPowers {
    /**
     * 根据给定的整数n和查询数组，计算每个查询范围内2的幂次乘积
     * <p>
     * 算法思路：
     * 1. 将整数n分解为2的幂次之和（通过二进制位运算实现）
     * 2. 对于每个查询，计算指定范围内幂次的乘积
     *
     * @param n       输入的正整数，用于分解为2的幂次之和
     * @param queries 查询数组，每个元素为[起始索引, 结束索引]的二维数组
     * @return 结果数组，每个元素对应一个查询的乘积结果
     */
    public int[] productQueries(int n, int[][] queries) {
        final int MOD = 1_000_000_007;

        // 使用lowbit技术将n分解为2的幂次
        // 例如二进制 1100 分解为 100 + 1000
        // 第一轮循环 lowbit(1100) = 100，然后 1100 ^ 100 = 1000
        // 第二轮循环 lowbit(1000) = 1000，然后 1000 ^ 1000 = 0，循环结束
        List<Integer> powers = new ArrayList<>();
        while (n > 0) {
            int lowbit = n & -n;
            powers.add(lowbit);
            n ^= lowbit;
        }

        // 处理每个查询，计算指定范围内幂次的乘积
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            long mul = 1;
            for (int j = q[0]; j <= q[1]; j++) {
                mul = mul * powers.get(j) % MOD;
            }
            ans[i] = (int) mul;
        }
        return ans;
    }


}
