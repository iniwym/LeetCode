package T2507;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2025-07-09
 * @Link: https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-i/
 */
public class Code3439_RescheduleMeetingsForMaximumFreeTimeI {

    /**
     * 计算在给定事件时间范围内，可以获得的连续最大空闲时间
     *
     * @param eventTime 总事件时间长度（时间轴总长度）
     * @param k         需要合并的空闲时间段数量（实际窗口长度为k+1）
     * @param startTime 各个事件开始时间数组（需保证按时间顺序排列）
     * @param endTime   各个事件结束时间数组（需保证按时间顺序排列）
     * @return 可获得的连续最大空闲时间
     */
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] free = new int[n + 1];

        // 计算所有空闲时间段：
        // 1. 第一个事件前的空闲时间
        // 2. 各事件之间的空闲时间
        // 3. 最后一个事件后的空闲时间
        free[0] = startTime[0];
        for (int i = 1; i < n; i++) {
            free[i] = startTime[i] - endTime[i - 1];
        }
        free[n] = eventTime - endTime[n - 1];

        // 使用滑动窗口算法找出k+1个连续空闲时间段的最大和
        int ans = 0;
        int s = 0;
        for (int i = 0; i <= n; i++) {
            s += free[i];
            if (i < k) {
                continue;
            }
            ans = Math.max(ans, s);
            s -= free[i - k];
        }
        return ans;
    }

}
