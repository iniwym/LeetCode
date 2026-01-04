package T2601;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2026-01-04
 * @Link: https://leetcode.cn/problems/four-divisors/
 */
public class Code1390_FourDivisors {

    /**
     * 计算数组中所有数字的四因子和的总和
     * 对于数组中的每个数字，如果该数字恰好有4个因子，则将其所有因子的和加入总和中
     *
     * @param nums 输入的整数数组
     * @return 所有具有四个因子的数字的因子和的总和
     */
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        // 遍历数组中的每个数字，累加其四因子和
        for (int num : nums) {
            totalSum += getFourDivisorSum(num);
        }

        return totalSum;
    }

    /**
     * 辅助函数：如果num恰好有4个因数，返回它们的和；否则返回0
     *
     * @param num 待检查的正整数
     * @return 如果num恰好有4个因数则返回它们的和，否则返回0
     */
    private int getFourDivisorSum(int num) {
        // 初始化因数和为1 + num（因为1和num总是因数）
        int sum = 1 + num;
        // 因数计数器
        int count = 2;

        // 只需检查到sqrt(num)即可
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                if (i * i == num) {
                    // 完全平方数的情况
                    sum += i;
                    count++;
                } else {
                    // 两个不同的因数
                    sum += i + num / i;
                    count += 2;
                }

                // 如果已经超过4个因数，提前返回0
                if (count > 4) {
                    return 0;
                }
            }
        }

        // 恰好有4个因数，返回它们的和
        return count == 4 ? sum : 0;
    }
}
