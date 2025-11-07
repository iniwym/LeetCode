package T2511;

/**
 * @Description: 前缀和
 * @Author: iniwym
 * @Date: 2025-11-07
 * @Link: https://leetcode.cn/problems/maximize-the-minimum-powered-city/
 */
public class Code2528_MaximizeTheMinimumPoweredCity {

    /**
     * Solution 类用于解决供电站建设问题，目标是在给定约束条件下最大化城市的最小供电量。
     */
    class Solution {
        /**
         * 计算在给定供电站数组、供电半径和可建供电站数量的情况下，城市最小供电量的最大值。
         *
         * @param stations 原始供电站数量数组，stations[i] 表示第 i 个城市初始拥有的供电站数量
         * @param r        每个供电站的供电半径
         * @param k        最多可以新建的供电站数量
         * @return 返回在最优建设策略下，城市最小供电量的最大值
         */
        public long maxPower(int[] stations, int r, int k) {
            int n = stations.length;
            // 计算前缀和数组，用于快速计算区间和
            long[] sum = new long[n + 1];
            for (int i = 0; i < n; i++) {
                sum[i + 1] = sum[i] + stations[i];
            }

            // 计算每个城市初始的供电量，并找出最小值
            long[] power = new long[n];
            long mn = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                power[i] = sum[Math.min(i + r + 1, n)] - sum[Math.max(i - r, 0)];
                mn = Math.min(mn, power[i]);
            }

            // 使用开区间二分法查找最大可能的最小供电量
            long left = mn + k / n;
            long right = mn + k + 1;
            while (left + 1 < right) {
                long mid = left + (right - left) / 2;
                if (check(mid, power, r, k)) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        /**
         * 检查是否可以通过新建最多 k 个供电站，使得所有城市的供电量都不低于 low。
         *
         * @param low   目标最低供电量
         * @param power 每个城市的初始供电量数组
         * @param r     供电站的供电半径
         * @param k     可新建的供电站数量上限
         * @return 如果可以达到目标则返回 true，否则返回 false
         */
        private boolean check(long low, long[] power, int r, int k) {
            int n = power.length;
            long[] diff = new long[n + 1]; // 差分数组，用于区间更新操作
            long sumD = 0; // 当前位置的差分值累加和
            long built = 0; // 已经新建的供电站总数
            for (int i = 0; i < n; i++) {
                sumD += diff[i]; // 累加差分值
                long m = low - (power[i] + sumD);
                if (m <= 0) {
                    continue;
                }
                // 需要在 i+r 额外建造 m 个供电站以满足要求
                built += m;
                if (built > k) { // 不满足要求
                    return false;
                }
                // 把区间 [i, i+2r] 增加 m（通过差分数组实现）
                sumD += m; // 由于 diff[i] 后面不会再访问，我们直接加到 sumD 中
                diff[Math.min(i + r * 2 + 1, n)] -= m;
            }
            return true;
        }
    }

}
