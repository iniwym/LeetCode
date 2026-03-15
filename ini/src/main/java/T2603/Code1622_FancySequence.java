package T2603;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 懒更新
 * @Author: iniwym
 * @Date: 2026-03-15
 * @Link: https://leetcode.cn/problems/fancy-sequence/
 */
public class Code1622_FancySequence {

    /**
     * 奇妙序列类
     * <p>
     * 实现一个支持以下操作的序列：
     * 1. append(val): 在序列末尾添加元素
     * 2. addAll(inc): 将所有元素增加指定值
     * 3. multAll(m): 将所有元素乘以指定值
     * 4. getIndex(idx): 获取指定索引处的元素
     * <p>
     * 使用数学优化，通过维护乘法因子和加法偏移量来避免逐个更新元素
     */
    class Fancy {
        private static final int MOD = 1_000_000_007;

        // 存储序列元素的列表
        private final List<Integer> vals = new ArrayList<>();
        // 全局加法偏移量
        private long add = 0;
        // 全局乘法因子
        private long mul = 1;

        /**
         * 在序列末尾添加一个新元素
         *
         * @param val 要添加的整数值
         */
        public void append(int val) {
            // 注意这里有减法，计算结果可能是负数，+MOD 可以保证计算结果非负
            vals.add((int) ((val - add + MOD) * pow(mul, MOD - 2) % MOD));
        }

        /**
         * 将序列中的所有元素增加指定值
         *
         * @param inc 要增加的整数值
         */
        public void addAll(int inc) {
            add = (add + inc) % MOD;
        }

        /**
         * 将序列中的所有元素乘以指定值
         *
         * @param m 要乘以的整数值
         */
        public void multAll(int m) {
            mul = mul * m % MOD;
            add = add * m % MOD;
        }

        /**
         * 获取指定索引处的元素值
         *
         * @param idx 要获取的索引位置
         * @return 索引处的元素值，如果索引超出范围则返回 -1
         */
        public int getIndex(int idx) {
            if (idx >= vals.size()) {
                return -1;
            }
            return (int) ((vals.get(idx) * mul + add) % MOD);
        }

        /**
         * 使用快速幂算法计算 x 的 n 次方
         *
         * @param x 底数
         * @param n 指数（非负整数）
         * @return x 的 n 次方结果对 MOD 取模的值
         */
        private long pow(long x, int n) {
            long res = 1;
            for (; n > 0; n /= 2) {
                if (n % 2 > 0) {
                    res = res * x % MOD;
                }
                x = x * x % MOD;
            }
            return res;
        }
    }
}
