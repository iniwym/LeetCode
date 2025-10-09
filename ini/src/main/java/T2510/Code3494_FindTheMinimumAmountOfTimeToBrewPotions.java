package T2510;

/**
 * @Description: 正反两次扫描
 * @Author: iniwym
 * @Date: 2025-10-09
 * @Link: https://leetcode.cn/problems/find-the-minimum-amount-of-time-to-brew-potions/
 */
public class Code3494_FindTheMinimumAmountOfTimeToBrewPotions {

    /**
     * 计算所有巫师完成所有药水制作的最短时间
     *
     * @param skill 每个巫师的技能值数组，skill[i]表示第i个巫师制作一瓶药水所需的时间
     * @param mana  每种药水所需的法力值数组，mana[i]表示第i种药水所需的法力值
     * @return 所有巫师完成所有药水制作的最短总时间
     */
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        long[] lastFinish = new long[n]; // 第 i 名巫师完成上一瓶药水的时间

        // 遍历每种药水的法力值
        for (int m : mana) {
            // 按题意模拟每个巫师依次制作当前药水的过程
            long sumT = 0;
            for (int i = 0; i < n; i++) {
                sumT = Math.max(sumT, lastFinish[i]) + skill[i] * m;
            }

            // 倒推更新每个巫师完成当前药水的时间
            // 如果酿造药水的过程中没有停顿，那么 lastFinish[i] 应该是多少
            lastFinish[n - 1] = sumT;
            for (int i = n - 2; i >= 0; i--) {
                lastFinish[i] = lastFinish[i + 1] - skill[i + 1] * m;
            }
        }

        // 返回最后一个巫师完成所有药水制作的时间
        return lastFinish[n - 1];
    }
}
