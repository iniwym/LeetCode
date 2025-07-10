package T2507;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-07-10
 * @Link: https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-ii/
 */
public class Code3440_RescheduleMeetingsForMaximumFreeTimeIi {
    private int eventTime;
    private int[] startTime, endTime;

    /**
     * 计算在给定事件时间下，通过移动最多一个时间段能够获得的最大空闲时间
     *
     * @param eventTime 事件时间，用于计算空闲时间
     * @param startTime 各时间段的开始时间数组
     * @param endTime   各时间段的结束时间数组（与startTime一一对应）
     * @return 能够获得的最大空闲时间
     */
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        this.eventTime = eventTime;
        this.startTime = startTime;
        this.endTime = endTime;
        int n = startTime.length;

        // 找出前三大空闲时间段的位置（a为最大，b为次大，c为第三大）
        int a = 0, b = -1, c = -1;
        for (int i = 1; i <= n; i++) {
            int sz = get(i);
            if (sz > get(a)) {
                c = b;
                b = a;
                a = i;
            } else if (b < 0 || sz > get(b)) {
                c = b;
                b = i;
            } else if (c < 0 || sz > get(c)) {
                c = i;
            }
        }

        int ans = 0;
        // 遍历所有时间段，计算移动或不移动该时间段能获得的最大空闲时间
        for (int i = 0; i < n; i++) {
            int sz = endTime[i] - startTime[i];
            // 判断当前时间段是否可以移动（不影响前三大空闲时间段）
            if (i != a && i + 1 != a && sz <= get(a) ||
                    i != b && i + 1 != b && sz <= get(b) ||
                    sz <= get(c)) {
                ans = Math.max(ans, get(i) + sz + get(i + 1));
            } else {
                ans = Math.max(ans, get(i) + get(i + 1));
            }
        }
        return ans;
    }

    // 计算空位长度
    private int get(int i) {
        if (i == 0) {
            return startTime[0];
        }
        int n = startTime.length;
        if (i == n) {
            return eventTime - endTime[n - 1];
        }
        return startTime[i] - endTime[i - 1];
    }

}
