package T2507;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-07-08
 * @Link: https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii/
 */
public class Code1751_MaximumNumberOfEventsThatCanBeAttendedIi {
    /**
     * 计算在最多参加k个会议的情况下能获得的最大收益
     *
     * @param events 二维数组表示所有会议，每个会议包含[startDay, endDay, value]
     * @param k      最多可以参加的会议数量
     * @return 可获得的最大收益
     */
    public static int maxValue(int[][] events, int k) {
        int n = events.length;
        // 按照会议结束时间升序排序
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        // dp[i][j]表示前i个会议中最多选j个会议时的最大收益
        int[][] dp = new int[n][k + 1];

        // 初始化：对于第一个会议，选任意j(>=1)次都是该会议的价值
        for (int j = 1; j <= k; j++) {
            dp[0][j] = events[0][2];
        }

        // 动态规划处理每个会议
        for (int i = 1, pre; i < n; i++) {
            // 查找在当前会议开始时间之前结束的最后一个会议
            pre = find(events, i - 1, events[i][0]);

            // 计算选或不选当前会议的最大收益
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(
                        dp[i - 1][j], // 不选当前会议
                        (pre == -1 ? 0 : dp[pre][j - 1]) + events[i][2] // 选当前会议
                );
            }
        }

        return dp[n - 1][k];
    }

    /**
     * 在已排序的会议数组中查找满足条件的会议编号
     *
     * @param events 二维数组表示会议列表，每个会议包含[start, end]两个元素，且events[0..i]已按结束时间排序
     * @param i      查找范围的右边界(0-based)
     * @param s      目标结束时间的阈值
     * @return 返回满足events[mid][1] < s的最右会议编号，若不存在则返回-1
     */
    public static int find(int[][] events, int i, int s) {
        // 初始化二分查找边界
        int l = 0, r = i, mid;
        int ans = -1;

        // 执行二分查找
        while (l <= r) {
            mid = (l + r) / 2;
            // 当前会议结束时间满足条件时，记录位置并继续向右查找
            if (events[mid][1] < s) {
                ans = mid;
                l = mid + 1;
            } else {
                // 否则向左查找
                r = mid - 1;
            }
        }
        return ans;
    }
}
