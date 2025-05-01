package T2505;

import java.util.Arrays;

/**
 * @Description: 二分查找和队列
 * @Author: iniwym
 * @Date: 2025-05-01
 * @Link: https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/
 */
public class Code1295_MaximumNumberOfTasksYouCanAssign {

    public static int[] tasks;

    public static int[] workers;

    public static int MAXN = 50001;

    public static int[] deque = new int[MAXN];

    public static int h, t;

    /**
     * 使用二分查找和队列策略来解决最大任务分配问题
     * 该方法旨在通过给定的任务难度、工人能力、药片数量和力量增益来最大化分配给工人的任务数
     *
     * @param ts       任务难度数组，每个任务有一个难度值
     * @param ws       工人能力数组，每个工人有一个能力值
     * @param pills    药片数量，工人服用后能力值会增加
     * @param strength 药片提供的力量增益值
     * @return 可以分配的最大任务数
     */
    public static int maxTaskAssign(int[] ts, int[] ws, int pills, int strength) {
        // 初始化任务和工人的数组
        tasks = ts;
        workers = ws;
        // 对任务难度和工人能力进行排序，以便后续处理
        Arrays.sort(tasks);
        Arrays.sort(workers);
        // 获取任务和工人的数量
        int tasksSize = tasks.length;
        int workersSize = workers.length;
        // 初始化最大可分配任务数为0
        int ans = 0;
        // 使用二分查找确定最大可分配任务数
        for (int l = 0, r = Math.min(tasksSize, workersSize), m; l <= r; ) {
            // 计算中间值作为当前可分配的最大任务数
            m = (l + r) / 2;
            // 检查当前分配方案是否可行
            if (f(0, m - 1, workersSize - m, workersSize - 1, pills, strength)) {
                // 如果可行，更新最大任务数并尝试增加任务数
                ans = m;
                l = m + 1;
            } else {
                // 如果不可行，减少任务数
                r = m - 1;
            }
        }
        // 返回最大可分配任务数
        return ans;
    }

    /**
     * 判断是否能够完成分配任务
     *
     * @param tl       任务列表的起始索引
     * @param tr       任务列表的结束索引
     * @param wl       工人列表的起始索引
     * @param wr       工人列表的结束索引
     * @param pills    可用的药片数量，每颗药片可以临时提升工人的能力
     * @param strength 药片能够提升的强度值
     * @return 如果能够完成分配，则返回true；否则返回false
     * <p>
     * 本函数的目的是为了判断在给定的任务和工人列表中，是否能够通过使用药片来完成所有任务
     * 它通过尝试为每个工人分配任务，并在必要时使用药片来提升工人的能力
     */
    public static boolean f(int tl, int tr, int wl, int wr, int pills, int strength) {
        // 初始化头部和尾部指针，以及计数器
        h = t = 0;
        int cnt = 0;

        // 遍历工人列表，尝试为每个工人分配任务
        for (int i = wl, j = tl; i <= wr; i++) {

            // 将所有当前工人能够处理的任务加入双端队列
            for (; j <= tr && tasks[j] <= workers[i]; j++) {
                deque[t++] = j;
            }

            // 如果当前工人能够处理队列中的任务，则移除该任务
            if (h < t && tasks[deque[h]] <= workers[i]) {
                h++;
            } else {
                // 如果当前工人无法处理队列中的任务，则尝试使用药片提升工人能力，并分配任务
                for (; j <= tr && tasks[j] <= workers[i] + strength; j++) {
                    deque[t++] = j;
                }

                // 如果成功分配任务，则计数器加一，表示使用了一颗药片
                if (h < t) {
                    cnt++;
                    t--;
                } else {
                    // 如果无法分配任务，则返回false，表示无法完成所有任务
                    return false;
                }
            }
        }

        // 如果使用的药片数量不超过可用的药片数量，则返回true，表示可以完成所有任务
        return cnt <= pills;
    }

    public static void main(String[] args) {
        int[] tasks = {5, 9, 8, 5, 9};
        int[] workers = {1, 6, 4, 2, 6};
        System.out.println(maxTaskAssign(tasks, workers, 1, 5));
    }
}
