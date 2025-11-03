package T2511;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-11-03
 * @Link: https://leetcode.cn/problems/minimum-time-to-make-rope-colorful/
 */
public class Code1578_MinimumTimeToMakeRopeColorful {

    /**
     * 计算移除气球的最小成本
     * 给定一个颜色字符串和每个位置气球的移除时间，要求移除一些气球使得没有两个相邻气球颜色相同，
     * 返回达到目标的最小移除成本。
     *
     * @param colors     表示气球颜色的字符串，每个字符代表一个气球的颜色
     * @param neededTime 表示移除每个位置气球所需时间的数组
     * @return 达到目标所需的最小移除成本
     */
    public int minCost(String colors, int[] neededTime) {
        int n = neededTime.length;
        int ans = 0;
        int maxT = 0;
        for (int i = 0; i < n; i++) {
            int t = neededTime[i];
            ans += t;
            maxT = Math.max(maxT, t);
            if (i == n - 1 || colors.charAt(i) != colors.charAt(i + 1)) {
                // 遍历到了连续同色段的末尾
                ans -= maxT; // 保留耗时最大的气球
                maxT = 0; // 准备计算下一段的最大耗时
            }
        }
        return ans;
    }

}
