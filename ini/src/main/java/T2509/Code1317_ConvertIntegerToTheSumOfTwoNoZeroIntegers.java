package T2509;

/**
 * @Description: 暴力枚举
 * @Author: iniwym
 * @Date: 2025-09-08
 * @Link: https://leetcode.cn/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
 */
public class Code1317_ConvertIntegerToTheSumOfTwoNoZeroIntegers {

    /**
     * 获取两个不含数字0的整数，使得它们的和等于给定的n
     *
     * @param n 目标和值，必须为大于1的正整数
     * @return 包含两个不含数字0的整数的数组，它们的和等于n
     */
    public int[] getNoZeroIntegers(int n) {
        // 从1开始遍历可能的第一个加数，直到找到满足条件的两个数
        for (int a = 1; ; a++) {
            // 检查当前数字a和对应的补数(n-a)是否都不包含数字0
            if (!Integer.toString(a).contains("0") &&
                    !Integer.toString(n - a).contains("0")) {
                return new int[]{a, n - a};
            }
        }
    }

}
