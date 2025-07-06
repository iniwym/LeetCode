package T2507;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-07-06
 * @Link: https://leetcode.cn/problems/finding-pairs-with-a-certain-sum/
 */
public class Code1865_FindingPairsWithACertainSum {


    /**
     * 用于统计两个数组中元素之和等于目标值的对数，支持动态修改第二个数组的元素
     */
    class FindSumPairs {
        int[] nums1;
        int[] nums2;
        Map<Integer, Integer> map = new HashMap<>();

        /**
         * 构造函数，初始化两个数组并构建nums2的频率哈希表
         *
         * @param nums1 第一个整数数组，查询时作为固定元素
         * @param nums2 第二个整数数组，查询时作为可变元素，支持动态修改
         */
        public FindSumPairs(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            // 统计nums2中每个元素的出现频率
            for (int x : nums2) {
                map.merge(x, 1, Integer::sum);
            }
        }

        /**
         * 修改nums2数组中指定位置的元素值，并更新频率哈希表
         *
         * @param index 要修改的元素下标
         * @param val   要增加的值（可为负数）
         */
        public void add(int index, int val) {
            // 更新原值的频率（减少1次）
            map.merge(nums2[index], -1, Integer::sum);
            // 修改数组元素
            nums2[index] += val;
            // 更新新值的频率（增加1次）
            map.merge(nums2[index], 1, Integer::sum);
        }

        /**
         * 统计nums1[i] + nums2[j] == tot的所有(i,j)对数
         *
         * @param tot 目标总和值
         * @return 满足条件的元素对数
         */
        public int count(int tot) {
            int ans = 0;
            // 遍历nums1，查找nums2中能与当前元素组成tot的互补值数量
            for (int x : nums1) {
                ans += map.getOrDefault(tot - x, 0);
            }
            return ans;
        }
    }
}
