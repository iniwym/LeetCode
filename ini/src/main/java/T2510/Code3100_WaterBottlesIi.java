package T2510;

/**
 * @Description: 模拟
 * @Author: iniwym
 * @Date: 2025-10-02
 * @Link: https://leetcode.cn/problems/water-bottles-ii/
 */
public class Code3100_WaterBottlesIi {

    /**
     * 计算最多可以喝多少瓶酒
     *
     * @param numBottles  初始拥有的酒瓶数量
     * @param numExchange 初始兑换需要的空瓶数量
     * @return 最多可以喝的酒瓶总数
     */
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = 0;
        // 当剩余瓶子数量大于等于当前兑换所需数量时，继续兑换
        while (numBottles >= numExchange) {
            ans += numExchange;
            numBottles -= numExchange - 1;
            numExchange++;
        }
        // 返回已喝掉的酒瓶数加上剩余无法兑换的酒瓶数
        return ans + numBottles;
    }
}
