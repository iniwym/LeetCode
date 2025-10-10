package T2510;

/**
 * @Description: 后缀和
 * @Author: iniwym
 * @Date: 2025-10-10
 * @Link: https://leetcode.cn/problems/taking-maximum-energy-from-the-mystic-dungeon/
 */
public class Code3494_TakingMaximumEnergyFromTheMysticDungeon {

    /**
     * 计算最大能量值
     *
     * @param energy 能量数组，表示每个位置的能量值
     * @param k      步长，表示每次跳跃的间隔
     * @return 返回能够获得的最大能量值
     */
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int ans = Integer.MIN_VALUE;

        // 枚举所有可能的终点位置，从倒数第k个位置开始到末尾
        for (int i = n - k; i < n; i++) {
            int sufSum = 0;

            // 从当前终点开始，以步长k向前累加能量值，计算所有可能的后缀和
            for (int j = i; j >= 0; j -= k) {
                sufSum += energy[j];
                ans = Math.max(ans, sufSum);
            }
        }
        return ans;
    }

}

