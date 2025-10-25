package T2510;

/**
 * @Description: 等差数列求和公式
 * @Author: iniwym
 * @Date: 2025-10-25
 * @Link: https://leetcode.cn/problems/calculate-money-in-leetcode-bank/
 */
public class Code1716_CalculateMoneyInLeetcodeBank {

    /**
     * 计算在n天内总共存入的钱数
     * 每周一存入1美元，每天比前一天多存1美元，每周比前一周的对应天多存1美元
     *
     * @param n 天数
     * @return n天内总共存入的钱数
     */
    public int totalMoney(int n) {
        final int D = 7;
        // 计算完整的周数和剩余天数
        int w = n / D;
        int r = n % D;
        // 根据等差数列求和公式计算总金额
        return (w * D * (w + D) + r * (w * 2 + r + 1)) / 2;
    }

}


