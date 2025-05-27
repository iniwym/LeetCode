package T2505;

/**
 * @Description: 求余
 * @Author: iniwym
 * @Date: 2025-05-27
 * @Link: https://leetcode.cn/problems/divisible-and-non-divisible-sums-difference/
 */
public class Code2894_DivisibleAndNonDivisibleSumsDifference {

    /**
     * 计算两个和的差值
     * 该方法接受两个整数参数n和m，计算从1到n的整数中，能被m整除的整数和（num2）与不能被m整除的整数和（num1）的差值
     *
     * @param n 整数范围的上限，包括n本身
     * @param m 用于判断整数是否能被m整除的除数
     * @return 返回不能被m整除的整数和与能被m整除的整数和的差值
     */
    public int differenceOfSums(int n, int m) {
        // 初始化能被m整除的整数和
        int num1 = 0;
        // 初始化不能被m整除的整数和
        int num2 = 0;

        // 遍历从1到n的整数
        for (int i = 1; i <= n; i++) {
            // 判断当前整数是否能被m整除
            if (i % m == 0) {
                // 如果能被m整除，累加到num2
                num2 += i;
            } else {
                // 如果不能被m整除，累加到num1
                num1 += i;
            }
        }

        // 返回两个和的差值
        return num1 - num2;
    }

}
