package T2508;

/**
 * @Description: 滑动窗口优化 DP
 * @Author: iniwym
 * @Date: 2025-08-17
 * @Link: https://leetcode.cn/problems/new-21-game/
 */
public class Code0837_New21Game {

    /**
     * 计算爱丽丝在21点游戏中获胜的概率
     * <p>
     * 游戏规则：爱丽丝从0分开始抽卡，每次抽到的分数在[1, maxPts]范围内等概率随机，
     * 当她的分数达到k分或以上时停止抽卡。如果最终分数不超过n分则获胜。
     *
     * @param n      最高得分限制，超过此分数则失败
     * @param k      停止抽卡的最低分数，达到此分数后停止抽卡
     * @param maxPts 每次抽卡可能获得的最大分数
     * @return 爱丽丝获胜的概率
     */
    public double new21Game(int n, int k, int maxPts) {
        double[] f = new double[n + 1];
        double s = 0;
        for (int i = n; i >= 0; i--) {
            // 计算从当前状态i开始游戏获胜的概率
            // 如果当前分数已达到停止条件，则获胜概率为1
            // 否则为后续所有可能状态获胜概率的平均值
            f[i] = i >= k ? 1 : s / maxPts;

            // 维护滑动窗口和，用于计算后续状态的概率
            // 当前循环计算的是 f[i+1] + ... + f[i+maxPts]
            // 下个循环计算的是 f[i] + ... + f[i+maxPts-1]，多了 f[i]，少了 f[i+maxPts]
            s += f[i];
            if (i + maxPts <= n) {
                s -= f[i + maxPts];
            }
        }
        return f[0];
    }
}
