package T2506;

/**
 * @Description: 二分查找
 * @Author: iniwym
 * @Date: 2025-06-25
 * @Link: https://leetcode.cn/problems/kth-smallest-product-of-two-sorted-arrays/
 */
public class Code2040_KthSmallestProductOfTwoSortedArrays {

    /**
     * 在排序数组中查找满足特定条件的元素索引
     * 本函数旨在解决的问题是，在一个排序数组中，找到第一个与给定值x1相乘后，结果不大于v的元素的位置
     * 如果x1为非负数，直接返回找到的元素的索引；如果x1为负数，则返回从数组末尾开始计算的索引
     *
     * @param nums2 排序数组
     * @param x1    给定的值，用于与数组元素相乘
     * @param v     乘积的结果需要对比的阈值
     * @return 如果x1为非负数，返回找到的元素的索引；如果x1为负数，则返回从数组末尾开始计算的索引
     */
    int f(int[] nums2, long x1, long v) {
        // 获取数组长度
        int n2 = nums2.length;
        // 初始化左右指针
        int left = 0, right = n2 - 1;

        // 使用二分查找法寻找满足条件的元素
        while (left <= right) {
            // 计算中间位置
            int mid = (left + right) / 2;
            // 计算当前中间元素与x1的乘积
            long prod = (long) nums2[mid] * x1;

            // 根据x1的正负和乘积与v的比较结果，决定搜索方向
            if ((x1 >= 0 && prod <= v) || (x1 < 0 && prod > v)) {
                // 如果满足条件，向右搜索
                left = mid + 1;
            } else {
                // 否则，向左搜索
                right = mid - 1;
            }
        }

        // 根据x1的正负，返回相应的索引
        if (x1 >= 0) {
            return left;
        } else {
            return n2 - left;
        }
    }

    /**
     * 寻找两个数组中第k小的乘积
     * 该方法通过二分查找算法实现，利用了乘积的单调性特性
     *
     * @param nums1 第一个数组，包含一组整数
     * @param nums2 第二个数组，包含另一组整数
     * @param k     指定的第k小的乘积
     * @return 返回第k小的乘积值
     */
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int n1 = nums1.length;
        // 初始化二分查找的边界，考虑到乘积可能为负数，因此范围较宽
        long left = -10000000000L, right = 10000000000L;
        // 二分查找目标乘积值
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            // 遍历第一个数组的每个元素，计算小于等于mid的乘积数量
            for (int i = 0; i < n1; i++) {
                count += f(nums2, nums1[i], mid);
            }
            // 根据count的值调整二分查找的范围
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // left最终指向第k小的乘积值
        return left;
    }
}
