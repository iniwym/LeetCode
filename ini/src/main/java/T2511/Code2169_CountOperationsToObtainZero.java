package T2511;

/**
 * @Description: 辗转相除
 * @Author: iniwym
 * @Date: 2025-11-09
 * @Link: https://leetcode.cn/problems/count-operations-to-obtain-zero/
 */
public class Code2169_CountOperationsToObtainZero {
    /**
     * 计算两个非负整数进行减法操作的次数
     * 每次操作将较大的数减去较小的数，直到其中一个数变为0
     *
     * @param num1 第一个非负整数
     * @param num2 第二个非负整数
     * @return 操作次数，如果任一输入为0则返回0
     */
    public int countOperations(int num1, int num2) {
        int ans = 0;
        // 如果任一数为0，直接返回0
        if (num1 == 0 || num2 == 0) {
            return 0;
        }

        // 循环执行减法操作直到其中一个数变为0
        while (!(num1 == 0 || num2 == 0)) {
            // 将较大的数减去较小的数
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
            ans++;

        }

        return ans;
    }

    /**
     * 计算两个整数之间的操作次数
     * 该函数通过模拟欧几里得算法的过程来计算操作次数
     *
     * @param x 第一个整数，作为被除数
     * @param y 第二个整数，作为除数
     * @return 操作次数的总和
     */
    public int countOperations1(int x, int y) {
        int ans = 0;
        // 使用欧几里得算法的思想，不断进行除法运算直到y为0
        while (y > 0) {
            ans += x / y;
            int r = x % y; // 计算x除以y的余数
            x = y; // 将y的值赋给x
            y = r; // 将余数赋给y，继续下一轮计算
        }
        return ans;
    }


}
