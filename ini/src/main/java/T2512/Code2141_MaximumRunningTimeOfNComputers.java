package T2512;

/**
 * @Description: 二分查找
 * @Author: iniwym
 * @Date: 2025-12-01
 * @Link: https://leetcode.cn/problems/maximum-running-time-of-n-computers/
 */
public class Code2141_MaximumRunningTimeOfNComputers {

    /**
     * 计算n台电脑可以同时运行的最大分钟数
     *
     * @param n         电脑的数量
     * @param batteries 电池数组，每个元素表示对应电池可以供电的分钟数
     * @return n台电脑可以同时运行的最大分钟数
     */
    public long maxRunTime(int n, int[] batteries) {
        // 计算所有电池的总电量
        long tot = 0;
        for (int b : batteries) {
            tot += b;
        }

        // 使用二分查找确定最大运行时间
        long l = 0;
        long r = tot / n + 1;
        while (l + 1 < r) {
            long x = l + (r - l) / 2;
            // 计算在时间x内，所有电池能够提供的总电量
            long sum = 0;
            for (int b : batteries) {
                sum += Math.min(b, x);
            }
            // 判断是否能够支持n台电脑运行x分钟
            if (n * x <= sum) {
                l = x;
            } else {
                r = x;
            }
        }
        return l;
    }
}
