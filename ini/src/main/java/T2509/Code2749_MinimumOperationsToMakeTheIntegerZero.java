package T2509;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-09-05
 * @Link: https://leetcode.cn/problems/minimum-operations-to-make-the-integer-zero/
 */
public class Code2749_MinimumOperationsToMakeTheIntegerZero {

    /**
     * 将给定的整数通过特定操作变为零
     * <p>
     * 该函数通过枚举操作次数k，找到最小的操作次数使得num1经过k次操作后变为0。
     * 每次操作将num1减去(num2*k + 某个值)的形式。
     *
     * @param num1 初始整数，需要通过操作变为0
     * @param num2 操作中的参数，用于计算每次操作减少的值
     * @return 返回使num1变为0的最小操作次数，如果无法变为0则返回-1
     */
    public int makeTheIntegerZero(int num1, int num2) {
        // 枚举操作次数k，寻找满足条件的最小值
        for (long k = 1; k <= num1 - num2 * k; k++) {
            // 检查当前操作次数是否满足条件：k大于等于(num1 - num2*k)的二进制中1的个数
            if (k >= Long.bitCount(num1 - num2 * k)) {
                return (int) k;
            }
        }
        return -1;
    }

}
