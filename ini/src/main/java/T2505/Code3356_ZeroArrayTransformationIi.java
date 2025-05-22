package T2505;

/**
 * @Description: 线段树
 * @Author: iniwym
 * @Date: 2025-05-21
 * @Link: https://leetcode.cn/problems/zero-array-transformation-ii/
 */
public class Code3356_ZeroArrayTransformationIi {
    /**
     * 解决最少操作使数组元素和非负的问题
     * 通过构建线段树来高效处理区间更新和查询最小值的操作
     *
     * @param nums    初始数组，表示每个元素的值
     * @param queries 查询数组，每个查询包含三个整数(l, r, val)，表示将区间[l, r]的每个元素增加val
     * @return 返回最少需要的操作次数使数组元素和非负，如果无法实现则返回-1
     */
    public int minZeroArray(int[] nums, int[][] queries) {
        // 初始化数组长度
        int n = nums.length;
        // 创建一个新数组，用于线段树的初始化，将元素值取反以便于后续操作
        int[] initial = new int[n];
        for (int i = 0; i < n; i++) {
            initial[i] = -nums[i];
        }
        // 使用初始化数组构建线段树
        int[] zerolithx = initial;
        SegmentTree st = new SegmentTree(zerolithx);
        // 如果线段树的最小值大于等于0，说明数组元素和已经非负，不需要任何操作
        if (st.getMin() >= 0) {
            return 0;
        }
        // 遍历查询数组，进行区间更新操作
        for (int k = 0; k < queries.length; k++) {
            // 解析查询的区间和更新值
            int l = queries[k][0];
            int r = queries[k][1];
            int val = queries[k][2];
            // 更新线段树中指定区间的值
            st.update(l, r, val);
            // 如果更新后线段树的最小值大于等于0，说明数组元素和已经非负，返回当前操作次数
            if (st.getMin() >= 0) {
                return k + 1;
            }
        }
        // 如果所有操作结束后，数组元素和仍然为负，返回-1
        return -1;
    }

    /**
     * 线段树数据结构，用于高效管理区间查询和更新操作
     * 主要功能是构建线段树、更新区间值和获取整个区间的最小值
     */
    class SegmentTree {
        // 存储每个区间最小值的数组
        private int[] min;
        // 存储每个区间待更新值的数组，用于懒更新
        private int[] add;
        // 数组长度
        private int n;

        /**
         * 构造函数，初始化线段树
         *
         * @param arr 线段树的初始数组
         */
        public SegmentTree(int[] arr) {
            n = arr.length;
            min = new int[4 * n];
            add = new int[4 * n];
            build(0, 0, n - 1, arr);
        }

        /**
         * 构建线段树
         *
         * @param node 当前节点的索引
         * @param l    当前节点表示的区间左边界
         * @param r    当前节点表示的区间右边界
         * @param arr  初始数组
         */
        private void build(int node, int l, int r, int[] arr) {
            if (l == r) {
                min[node] = arr[l];
                return;
            }
            int mid = (l + r) / 2;
            build(2 * node + 1, l, mid, arr);
            build(2 * node + 2, mid + 1, r, arr);
            min[node] = Math.min(min[2 * node + 1], min[2 * node + 2]);
        }

        /**
         * 将懒更新值推送到子节点
         *
         * @param node 当前节点的索引
         * @param l    当前节点表示的区间左边界
         * @param r    当前节点表示的区间右边界
         */
        private void push(int node, int l, int r) {
            if (add[node] != 0 && l < r) {
                add[2 * node + 1] += add[node];
                min[2 * node + 1] += add[node];
                add[2 * node + 2] += add[node];
                min[2 * node + 2] += add[node];
                add[node] = 0;
            }
        }

        /**
         * 更新指定区间的值
         *
         * @param node 当前节点的索引
         * @param l    当前节点表示的区间左边界
         * @param r    当前节点表示的区间右边界
         * @param ul   更新区间的左边界
         * @param ur   更新区间的右边界
         * @param val  更新的值
         */
        private void updateRange(int node, int l, int r, int ul, int ur, int val) {
            if (ur < l || ul > r) {
                return;
            }
            if (ul <= l && r <= ur) {
                min[node] += val;
                add[node] += val;
                return;
            }
            push(node, l, r);
            int mid = (l + r) / 2;
            updateRange(2 * node + 1, l, mid, ul, ur, val);
            updateRange(2 * node + 2, mid + 1, r, ul, ur, val);
            min[node] = Math.min(min[2 * node + 1], min[2 * node + 2]);
        }

        /**
         * 更新指定区间的值
         *
         * @param l   更新区间的左边界
         * @param r   更新区间的右边界
         * @param val 更新的值
         */
        public void update(int l, int r, int val) {
            updateRange(0, 0, n - 1, l, r, val);
        }

        /**
         * 获取整个数组的最小值
         *
         * @return 整个数组的最小值
         */
        public int getMin() {
            return min[0];
        }
    }

}

