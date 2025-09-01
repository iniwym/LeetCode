package T2509;

import java.util.PriorityQueue;

/**
 * @Description: 大顶堆
 * @Author: iniwym
 * @Date: 2025-09-01
 * @Link: https://leetcode.cn/problems/maximum-average-pass-ratio/
 */
public class Code1792_MaximumAveragePassRatio {

    /**
     * 计算在分配额外学生后，所有班级平均通过率的最大值
     * <p>
     * 算法思路：使用优先队列，每次将额外学生分配给能够获得最大通过率提升的班级
     * 通过率提升的比较标准：(a+1)/(b+1) - a/b 的大小关系
     *
     * @param classes       二维数组，每个子数组包含两个元素：[通过学生数, 总学生数]
     * @param extraStudents 需要分配的额外学生数量
     * @return 分配完额外学生后，所有班级平均通过率的最大值
     */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // 创建优先队列，按照通过率提升幅度排序
        // 比较器使用交叉相乘避免浮点数精度问题
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                Long.compare(1L * (b[1] - b[0]) * a[1] * (a[1] + 1), 1L * (a[1] - a[0]) * b[1] * (b[1] + 1))
        );

        // 将所有班级加入优先队列
        for (int[] c : classes) {
            pq.add(c);
        }

        // 分配额外学生：每次取出通过率提升最大的班级，增加一个学生后重新入队
        while (extraStudents-- > 0) {
            int[] top = pq.poll();
            top[0]++;
            top[1]++;
            pq.add(top);
        }

        // 计算所有班级的平均通过率
        double sum = 0;
        int n = pq.size();
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            sum += 1.0 * top[0] / top[1];
        }
        return sum / n;
    }

}
