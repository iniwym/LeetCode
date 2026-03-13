package T2603;

import java.util.PriorityQueue;

/**
 * @Description: 最小堆
 * @Author: iniwym
 * @Date: 2026-03-13
 * @Link: https://leetcode.cn/problems/minimum-number-of-seconds-to-make-mountain-height-zero/
 */
public class Code3296_MinimumNumberOfSecondsToMakeMountainHeightZero {


    /**
     * 计算使山峰高度降为零所需的最少秒数
     *
     * @param mountainHeight 山峰的初始高度，表示需要移除的山体单位数量
     * @param workerTimes    工人时间数组，每个元素表示对应工人完成第 i 次工作所需的基础时间增量
     *                       工人完成第 k 次工作的耗时为：baseTime * (1 + 2 + ... + k) = baseTime * k * (k+1) / 2
     * @return 使山峰高度降为零所需的最少秒数
     */
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        // 创建优先队列，按照工人的累计总时间升序排列，确保每次选择当前耗时最少的工人
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        // 初始化每个工人的状态并加入优先队列
        // 数组三个元素分别为：[累计总时间，下一次工作耗时，基础时间增量]
        for (int t : workerTimes) {
            pq.offer(new long[]{t, t, t});
        }

        long ans = 0;

        // 每次循环移除一个单位高度的山体，选择当前能最快完成工作的工人
        while (mountainHeight-- > 0) {
            // 取出当前累计总时间最小的工人
            long[] top = pq.poll();
            long total = top[0], cur = top[1], base = top[2];

            // 更新答案为当前工人完成工作的时间点
            ans = total;

            // 计算该工人下一次工作的累计时间和耗时，并重新加入队列
            pq.offer(new long[]{total + cur + base, cur + base, base});
        }

        return ans;
    }

}
