package T2510;

/**
 * @Description: 模拟
 * @Author: iniwym
 * @Date: 2025-10-01
 * @Link: https://leetcode.cn/problems/water-bottles/
 */
public class Code1518_WaterBottles {

    /**
     * 计算最多可以喝到多少瓶水
     *
     * @param numBottles  初始购买的水瓶数量
     * @param numExchange 需要多少个空瓶才能兑换一瓶新水
     * @return 最多可以喝到的水瓶总数
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        // 当空瓶数量足够兑换新水时，继续兑换过程
        while (numBottles / numExchange > 0) {
            int temp = numBottles / numExchange;
            ans += temp;
            // 更新空瓶数量：减去用于兑换的空瓶数，加上新喝完的水瓶数
            numBottles = numBottles - (temp * numExchange) + temp;
        }
        return ans;
    }

}
