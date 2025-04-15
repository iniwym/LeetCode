package T2504;

/**
 * @Description: 树状数组
 * @Author: iniwym
 * @Date: 2025-04-15
 * @Link: https://leetcode.cn/problems/count-good-triplets-in-an-array/
 */
public class Code2179_CountGoodTripletsInAnArray {

    class Solution {
        /**
         * 计算满足特定条件的三元组数目。给定两个等长数组nums1和nums2，其中每个数组都是1到n的排列，
         * 该函数返回满足以下条件的三元组(i, j, k)的数量：
         * - i < j < k
         * - nums1[i]在nums2中的位置小于nums1[j]在nums2中的位置
         * - nums1[k]在nums2中的位置大于nums1[j]在nums2中的位置
         *
         * @param nums1 第一个排列数组
         * @param nums2 第二个排列数组
         * @return 符合条件的三元组数目
         */
        public long goodTriplets(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int[] pos = new int[n];
            // 初始化pos数组，记录nums2中每个元素的索引位置（1-based）
            for (int i = 0; i < n; ++i) {
                pos[nums2[i]] = i + 1;
            }
            BinaryIndexedTree tree = new BinaryIndexedTree(n);
            long ans = 0;
            // 遍历nums1中的每个元素，计算以当前元素为中间元素的符合条件的三元组数目
            for (int num : nums1) {
                int p = pos[num];
                // 计算当前元素左边已插入的元素数目（left）
                long left = tree.query(p);
                // 计算当前元素右边未被插入的元素数目（right）
                long right = n - p - (tree.query(n) - tree.query(p));
                ans += left * right;
                // 将当前元素的位置标记为已插入
                tree.update(p, 1);
            }
            System.out.println(ans);
            return ans;
        }

    }

    class BinaryIndexedTree {
        private int n;
        private int[] tree;

        /**
         * 初始化树状数组。
         *
         * @param n 数组的大小，必须为正整数。
         */
        public BinaryIndexedTree(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        /**
         * 更新位置x的值，增加delta。
         *
         * @param x     要更新的位置（1-based索引）。
         * @param delta 增量值。
         */
        public void update(int x, int delta) {
            while (x <= n) {
                tree[x] += delta;
                x += lowbit(x); // 沿树状数组向上更新，直到超出数组范围
            }
        }

        /**
         * 查询前缀和，计算从1到x的元素之和。
         *
         * @param x 查询的结束位置（1-based索引）。
         * @return 从1到x的累加和。
         */
        public int query(int x) {
            int s = 0;
            while (x > 0) {
                s += tree[x];
                x -= lowbit(x); // 沿树状数组向下累加，直到x变为0
            }
            return s;
        }

        /**
         * 计算x的最低位1的值。
         *
         * @param x 输入的整数。
         * @return x与-x的按位与结果，即最低位1对应的值。
         */
        public int lowbit(int x) {
            return x & -x;
        }
    }
}
