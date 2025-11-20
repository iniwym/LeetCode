package T2511;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 栈+二分查找
 * @Author: iniwym
 * @Date: 2025-11-20
 * @Link: https://leetcode.cn/problems/set-intersection-size-at-least-two/
 */
public class Code0757_SetIntersectionSizeAtLeastTwo {

    class Solution {
        /**
         * 计算一个最小集合的大小，使得对于给定的区间数组中的每一个区间，该集合至少包含两个属于该区间的整数。
         *
         * @param intervals 给定的二维数组，每个子数组表示一个闭区间 [start, end]
         * @return 返回满足条件的最小集合的大小
         */
        public int intersectionSizeTwo(int[][] intervals) {
            // 按照区间的右端点升序排序
            Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

            // 栈中保存闭区间左右端点以及从栈底到当前项所覆盖的总长度
            List<int[]> st = new ArrayList<>();

            // 添加哨兵节点，确保不会与任何实际区间相交
            st.add(new int[]{-2, -2, 0});

            // 遍历所有已排序的区间
            for (int[] t : intervals) {
                int start = t[0], end = t[1];

                // 找出在当前处理过程中的相关区间
                int[] e = st.get(lowerBound(st, start) - 1);

                // 计算还需要多少个点来满足“至少两个”的要求，并减去已经在运行中的时间点数量
                int d = 2 - (st.get(st.size() - 1)[2] - e[2]);

                // 如果起始位置位于已有区间内，则进一步减少需要新增的时间点数
                if (start <= e[1]) {
                    d -= e[1] - start + 1;
                }

                // 如果不需要额外添加点，则跳过本次循环
                if (d <= 0) {
                    continue;
                }

                // 当剩余需填充的数量大于等于当前末尾区间的可扩展空间时，合并区间
                while (end - st.get(st.size() - 1)[1] <= d) {
                    e = st.remove(st.size() - 1);
                    d += e[1] - e[0] + 1;
                }

                // 将新构造的区间加入栈中
                st.add(new int[]{end - d + 1, end, st.get(st.size() - 1)[2] + d});
            }

            // 返回最终累计的总长度
            return st.get(st.size() - 1)[2];
        }

        /**
         * 使用二分查找找到第一个左端点大于等于目标值的位置（lower bound）。
         *
         * @param st     存储区间的列表
         * @param target 目标值
         * @return 第一个左端点大于等于目标值的索引
         */
        private int lowerBound(List<int[]> st, int target) {
            int left = -1, right = st.size();

            // 在开区间(left, right)中进行二分搜索
            while (left + 1 < right) {
                int mid = (left + right) >>> 1;

                // 若中间元素的左端点小于目标值，则调整左边界
                if (st.get(mid)[0] < target) {
                    left = mid;
                } else {
                    // 否则调整右边界
                    right = mid;
                }
            }

            // 返回找到的目标位置
            return right;
        }
    }

}
