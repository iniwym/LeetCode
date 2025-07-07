package T2507;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-07-07
 * @Link: https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/
 */
public class Code1353_MaximumNumberOfEventsThatCanBeAttended {

    /**
     * 计算可以参加的最大事件数
     * <p>
     * 该函数使用贪心算法，优先参加结束时间最早的事件，以最大化可参加的事件数量
     *
     * @param events 二维数组表示的事件列表，每个事件包含两个元素[startDay, endDay]
     *               startDay表示事件开始日期，endDay表示事件结束日期
     * @return 可以参加的最大事件数量
     */
    public static int maxEvents(int[][] events) {
        // 获取事件数量并按开始日期排序
        int n = events.length;
        Arrays.sort(events, (x1, x2) -> x1[0] - x2[0]);

        // 计算所有事件中的最早开始日期和最晚结束日期
        int min = events[0][0];
        int max = events[0][1];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, events[i][1]);
        }

        // 使用优先队列(最小堆)存储当前可参加事件的结束日期
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int ans = 0;
        int i = 0;

        // 遍历每一天，从最早日期到最晚日期
        for (int day = min; day <= max; day++) {
            // 将当天开始的事件加入队列
            while (i < n && events[i][0] == day) {
                queue.add(events[i++][1]);
            }

            // 移除已经过期的事件(结束日期早于当前日期)
            while (!queue.isEmpty() && queue.peek() < day) {
                queue.poll();
            }

            // 如果还有可参加的事件，选择结束最早的事件参加
            if (!queue.isEmpty()) {
                queue.poll();
                ans++;
            }
        }

        return ans;
    }
}
