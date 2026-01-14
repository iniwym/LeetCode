package T2601;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Description: 扫描线 + 线段树
 * @Author: iniwym
 * @Date: 2026-01-14
 * @Link: https://leetcode.cn/problems/separate-squares-ii/
 */
public class Code3454_SeparateSquaresIi {

    /**
     * 线段树类，用于处理区间覆盖问题
     */
    class SegmentTree {
        private int[] count;      // 记录每个节点被覆盖的次数
        private int[] covered;    // 记录每个节点区间实际被覆盖的长度
        private int[] xs;         // 坐标离散化后的数组
        private int n;            // 区间数量

        /**
         * 构造函数，初始化线段树
         *
         * @param xs_ 离散化后的坐标数组
         */
        public SegmentTree(int[] xs_) {
            xs = xs_;
            n = xs.length - 1;
            count = new int[4 * n];
            covered = new int[4 * n];
        }

        /**
         * 修改操作，在指定区间内增加或减少覆盖次数
         *
         * @param qleft  查询区间的左边界
         * @param qright 查询区间的右边界
         * @param qval   要增加的值（正数表示增加覆盖，负数表示减少覆盖）
         * @param left   当前节点对应区间的左边界索引
         * @param right  当前节点对应区间的右边界索引
         * @param pos    当前节点在线段树数组中的位置
         */
        private void modify(int qleft, int qright, int qval, int left, int right, int pos) {
            // 如果当前区间与查询区间无交集，直接返回
            if (xs[right + 1] <= qleft || xs[left] >= qright) {
                return;
            }
            // 如果当前区间完全被查询区间包含，更新覆盖次数
            if (qleft <= xs[left] && xs[right + 1] <= qright) {
                count[pos] += qval;
            } else {
                // 否则递归处理左右子区间
                int mid = (left + right) / 2;
                modify(qleft, qright, qval, left, mid, pos * 2 + 1);
                modify(qleft, qright, qval, mid + 1, right, pos * 2 + 2);
            }

            // 根据当前节点的覆盖次数更新被覆盖的长度
            if (count[pos] > 0) {
                // 如果当前区间被覆盖，记录整个区间的长度
                covered[pos] = xs[right + 1] - xs[left];
            } else {
                // 如果当前区间未被直接覆盖，根据子区间的情况计算总覆盖长度
                if (left == right) {
                    covered[pos] = 0;
                } else {
                    covered[pos] = covered[pos * 2 + 1] + covered[pos * 2 + 2];
                }
            }
        }

        /**
         * 更新操作，在指定区间内增加或减少覆盖次数
         *
         * @param qleft  查询区间的左边界
         * @param qright 查询区间的右边界
         * @param qval   要增加的值
         */
        public void update(int qleft, int qright, int qval) {
            modify(qleft, qright, qval, 0, n - 1, 0);
        }

        /**
         * 查询整个区间的覆盖长度
         *
         * @return 返回根节点记录的总覆盖长度
         */
        public int query() {
            return covered[0];
        }
    }


    /**
     * 计算分离正方形所需的水平切割线位置
     * 使用扫描线算法和线段树来计算覆盖区域的面积分布
     * <p>
     * squares 表示正方形的二维数组，每个元素为 [x, y, length]，其中 (x,y) 是左下角坐标，length 是边长
     *
     * @return 返回水平切割线的 y 坐标，使得切割线上下两部分的面积尽可能相等
     */
    class Solution {
        public double separateSquares(int[][] squares) {
            // 存储事件: (y坐标, 类型, 左边界, 右边界)
            List<int[]> events = new ArrayList<>();
            Set<Integer> xsSet = new TreeSet<>();

            for (int[] sq : squares) {
                int x = sq[0], y = sq[1], l = sq[2];
                int xr = x + l;
                events.add(new int[]{y, 1, x, xr});
                events.add(new int[]{y + l, -1, x, xr});
                xsSet.add(x);
                xsSet.add(xr);
            }

            // 按y坐标排序事件
            events.sort((a, b) -> Integer.compare(a[0], b[0]));
            // 离散化坐标
            int[] xs = xsSet.stream().mapToInt(i -> i).toArray();
            // 初始化线段树
            SegmentTree segTree = new SegmentTree(xs);

            List<Long> psum = new ArrayList<>();
            List<Integer> widths = new ArrayList<>();
            Long totalArea = 0L;
            int prev = events.get(0)[0];

            // 扫描：计算总面积和记录中间状态
            for (int[] event : events) {
                int y = event[0], delta = event[1], xl = event[2], xr = event[3];
                int len = segTree.query();
                totalArea += (long) len * (y - prev);
                segTree.update(xl, xr, delta);
                // 记录前缀和和宽度
                psum.add(totalArea);
                widths.add(segTree.query());
                prev = y;
            }

            // 计算目标面积（向上取整的一半）
            long target = (long) (totalArea + 1) / 2;
            // 二分查找
            int i = binarySearch(psum, target);
            double area = psum.get(i);
            // 获取对应的面积、宽度和高度
            int width = widths.get(i), height = events.get(i)[0];

            return height + (totalArea - area * 2) / (width * 2.0);
        }

        /**
         * 在有序列表中二分查找小于目标值的最大索引
         *
         * @param list   有序的长整数列表
         * @param target 目标值
         * @return 返回满足条件的索引位置
         */
        private int binarySearch(List<Long> list, long target) {
            int left = 0;
            int right = list.size() - 1;
            int result = 0;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid) < target) {
                    result = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return result;
        }
    }


}
