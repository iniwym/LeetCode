package T2504;

import java.util.Arrays;

/**
 * @Description: 预处理
 * @Author: iniwym
 * @Date: 2025-04-11
 * @Link: https://leetcode.cn/problems/count-symmetric-integers/
 */
public class Code2843_CountSymmetricIntegers {
    static int[] arr = new int[10001];

    /**
     * 计算指定范围内的对称整数数量。
     *
     * @param low  区间下限（包含）
     * @param high 区间上限（包含）
     * @return 在[low, high]区间内的对称整数的数量
     */
    public static int countSymmetricIntegers(int low, int high) {
        // 预处理以确保arr数组计算到high的值
        process(high);
        // 通过前缀和数组计算low到high之间的对称整数数量
        return arr[high] - arr[low - 1];
    }


    /**
     * 根据指定的上限值处理不同范围的数值并更新数组arr的值
     *
     * @param high 需要处理的数值上限
     */
    private static void process(int high) {

        if (high >= 11) {
            // 处理两位数（11到99）的数值，更新arr数组的值
            // 当数字是11的倍数时，当前值在前一个基础上加1，否则保持前一个值
            for (int i = 11; i <= 99; i++) {
                if (i % 11 == 0) {
                    arr[i] = arr[i - 1] + 1;
                } else {
                    arr[i] = arr[i - 1];
                }
            }
        }
        if (high >= 100) {
            // 处理三位数（100到999）的数值，将对应位置统一设为9
            // 并输出四位数起始位置（1000）的值
            Arrays.fill(arr, 100, 1000, 9);
        }
        if (high >= 1000) {
            // 处理四位数（1000到high或9999）的数值
            // 当数字的前两位之和等于后两位之和时，当前值在前一个基础上加1
            for (int i = 1000; i <= Math.min(9999, high); i++) {
                int a = i % 10;
                int b = (i / 10) % 10;
                int c = (i / 100) % 10;
                int d = (i / 1000) % 10;
                if (a + b == c + d) {
                    arr[i] = arr[i - 1] + 1;
                } else {
                    arr[i] = arr[i - 1];
                }
            }
        }
        if (high == 10000) {
            // 特殊处理五位数的边界情况（10000）
            arr[10000] = arr[9999];
        }
    }

    public static void main(String[] args) {
        System.out.println(countSymmetricIntegers(100, 10000));
    }
}


