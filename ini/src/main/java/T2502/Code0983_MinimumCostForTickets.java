package T2502;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-02-24
 * @Link: https://leetcode.cn/problems/minimum-cost-for-tickets/
 */
public class Code0983_MinimumCostForTickets {

    public static int[] durations = {1, 7, 30};

    public int mincostTickets1(int[] days, int[] costs) {

        return f1(days, costs, 0);
    }

    public int f1(int[] days, int[] costs, int i) {
        // 已经没有天需要考虑了
        if (i == days.length) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        for (int k = 0, j = i; k < 3; k++) {
            while (j < days.length && days[i] + durations[k] > days[j]) {
                j++;
            }

            ans = Math.min(ans, costs[k] + f1(days, costs, j));
        }
        return ans;
    }

    /**
     * dp表
     */
    public int mincostTickets2(int[] days, int[] costs) {
        int[] dp = new int[days.length + 1];
        Arrays.fill(dp, -1);
        return f2(days, costs, 0, dp);
    }

    public int f2(int[] days, int[] costs, int i, int[] dp) {
        // 已经没有天需要考虑了
        if (i == days.length) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int ans = Integer.MAX_VALUE;

        for (int k = 0, j = i; k < 3; k++) {
            while (j < days.length && days[i] + durations[k] > days[j]) {
                j++;
            }

            ans = Math.min(ans, costs[k] + f2(days, costs, j, dp));
        }
        dp[i] = ans;
        return ans;
    }


    static int MAX = 366;
    static int[] dp = new int[MAX];

    /**
     * 自底向上
     */
    public int mincostTickets3(int[] days, int[] costs) {
        int n = days.length;
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0, j = i; k < 3; k++) {
                while (j < days.length && days[i] + durations[k] > days[j]) {
                    j++;
                }
                dp[i] = Math.min(dp[i], costs[k] + dp[j]);
            }
        }

        return dp[0];
    }

}
