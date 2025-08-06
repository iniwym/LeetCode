package T2508;

/**
 * @Description: 线段树
 * @Author: iniwym
 * @Date: 2025-08-06
 * @Link: https://leetcode.cn/problems/fruits-into-baskets-iii/
 */
public class Code3479_FruitsIntoBasketsIii {

    /**
     * 线段树类，用于维护区间最大值，并支持查找区间内第一个大于等于某个值的元素并将其更新为-1。
     */
    class SegmentTree {
        private final int[] max;

        /**
         * 构造函数，根据给定数组构建线段树。
         *
         * @param a 输入数组，用于初始化线段树的叶子节点
         */
        public SegmentTree(int[] a) {
            int n = a.length;
            max = new int[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
            build(a, 1, 0, n - 1);
        }

        /**
         * 在指定区间内查找第一个大于等于x的数，将其更新为-1，并返回该数的下标。
         * 如果不存在这样的数，则返回-1。
         *
         * @param o 当前线段树节点的索引
         * @param l 当前区间的左边界
         * @param r 当前区间的右边界
         * @param x 要查找的目标值
         * @return 第一个大于等于x的数的下标，若不存在则返回-1
         */
        public int findFirstAndUpdate(int o, int l, int r, int x) {
            if (max[o] < x) { // 区间没有 >= x 的数
                return -1;
            }
            if (l == r) {
                max[o] = -1; // 更新为 -1，表示不能放水果
                return l;
            }
            int m = (l + r) / 2;
            int i = findFirstAndUpdate(o * 2, l, m, x); // 先递归左子树
            if (i < 0) { // 左子树没找到
                i = findFirstAndUpdate(o * 2 + 1, m + 1, r, x); // 再递归右子树
            }
            maintain(o);
            return i;
        }

        /**
         * 维护当前节点的最大值，使其等于左右子节点的最大值。
         *
         * @param o 当前节点的索引
         */
        private void maintain(int o) {
            max[o] = Math.max(max[o * 2], max[o * 2 + 1]);
        }

        /**
         * 递归构建线段树。
         *
         * @param a 输入数组
         * @param o 当前节点在树中的索引
         * @param l 当前区间的左边界
         * @param r 当前区间的右边界
         */
        private void build(int[] a, int o, int l, int r) {
            if (l == r) {
                max[o] = a[l];
                return;
            }
            int m = (l + r) / 2;
            build(a, o * 2, l, m);
            build(a, o * 2 + 1, m + 1, r);
            maintain(o);
        }
    }

    /**
     * 计算无法放入篮子中的水果数量
     *
     * @param fruits  水果数组，每个元素表示水果的大小
     * @param baskets 篮子数组，每个元素表示篮子的容量
     * @return 无法放置的水果数量
     */
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        // 使用线段树来管理篮子容量，支持快速查找和更新
        SegmentTree t = new SegmentTree(baskets);
        int n = baskets.length;
        int ans = 0;

        // 遍历每个水果，尝试将其放入合适的篮子中
        for (int x : fruits) {
            // 查找第一个能容纳当前水果的篮子并更新其容量
            // 如果返回值小于0，说明没有找到合适的篮子
            if (t.findFirstAndUpdate(1, 0, n - 1, x) < 0) {
                ans++;
            }
        }

        return ans;
    }


}
