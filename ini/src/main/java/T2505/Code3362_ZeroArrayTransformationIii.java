package T2505;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Description: 贪心+最大堆+差分数组
 * @Author: iniwym
 * @Date: 2025-05-22
 * @Link: https://leetcode.cn/problems/zero-array-transformation-iii/
 */
public class Code3362_ZeroArrayTransformationIii {

    /**
     * 计算最大移除数
     * 给定一个整数数组和一系列查询，每个查询由一个区间组成，该函数的目标是移除尽可能多的区间，使得剩余区间的左端点值不大于其右端点值
     *
     * @param nums    整数数组，表示每个位置的值
     * @param queries 查询数组，每个查询由一个起始索引和结束索引组成的数组表示
     * @return 返回可以移除的最大区间数，如果无法移除所有不满足条件的区间则返回-1
     */
    public int maxRemoval(int[] nums, int[][] queries) {
        // 对查询进行排序，基于区间的左端点，以便后续处理
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        // 使用优先队列存储区间的右端点，优先队列按降序排列，确保最大的右端点在队列顶部
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = nums.length;
        // diff数组用于记录每个位置的度数变化，初始时每个位置的度数为其值
        int[] diff = new int[n + 1];
        // sumD用于累计处理到当前位置时的度数总和
        int sumD = 0;
        // j用于遍历queries数组
        int j = 0;
        // 遍历nums数组，对每个位置进行处理
        for (int i = 0; i < n; i++) {
            // 累加当前位置的度数
            sumD += diff[i];
            // 维护左端点 <= i 的区间，将符合条件的区间右端点加入优先队列
            while (j < queries.length && queries[j][0] <= i) {
                pq.add(queries[j][1]);
                j++;
            }
            // 选择右端点最大的区间，尝试移除不满足条件的区间
            while (sumD < nums[i] && !pq.isEmpty() && pq.peek() >= i) {
                sumD++;
                diff[pq.poll() + 1]--;
            }
            // 如果无法移除所有不满足条件的区间，则返回-1
            if (sumD < nums[i]) {
                return -1;
            }
        }
        // 返回可以移除的最大区间数
        return pq.size();
    }

}
