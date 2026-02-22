package T2602;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-22
 * @Link: https://leetcode.cn/problems/prime-number-of-set-bits-in-binary-representation/
 */
public class Code0762_PrimeNumberOfSetBitsInBinaryRepresentation {

    /**
     * 计算在给定范围 [left, right] 内，二进制表示中 1 的个数为质数的整数数量。
     *
     * @param left  范围的起始值（包含）
     * @param right 范围的结束值（包含）
     * @return 在指定范围内，二进制表示中 1 的个数为质数的整数数量
     */
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        // 遍历范围内的每一个整数
        for (int x = left; x <= right; x++) {
            // 检查当前整数的二进制表示中 1 的个数是否为质数
            if (primes.contains(Integer.bitCount(x))) {
                ans++;
            }
        }
        return ans;
    }

    // 静态不可变集合，存储已知的小质数，用于快速判断一个数是否为质数
    private static final Set<Integer> primes = Collections.unmodifiableSet(
            new HashSet<Integer>() {{
                add(2);
                add(3);
                add(5);
                add(7);
                add(11);
                add(13);
                add(17);
                add(19);
            }}
    );
}
