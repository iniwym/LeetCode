package T2504;

import java.util.Arrays;

/**
 * @Description: 二分查找
 * @Author: iniwym
 * @Date: 2025-04-19
 * @Link: https://leetcode.cn/problems/count-the-number-of-fair-pairs/
 */
public class Code2563_CountTheNumberOfFairPairs {

    /**
     * 统计满足条件的数对数量。数对(i, j)满足i < j且nums[i] + nums[j]在[lower, upper]范围内。
     *
     * @param nums  输入的整数数组
     * @param lower 数对和的下界（包含）
     * @param upper 数对和的上界（包含）
     * @return 符合条件的数对数量
     */
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums); // 对数组进行排序，以便后续使用二分查找

        long ans = 0;
        // 遍历每个可能的j，计算对应的i的范围并累加符合条件的对数
        for (int j = 0; j < nums.length; j++) {
            int r = lowerBound(nums, j, upper - nums[j] + 1);
            int l = lowerBound(nums, j, lower - nums[j]);
            ans += r - l;
        }

        return ans;
    }

    /**
     * 使用二分查找算法查找有序数组中第一个大于或等于目标值的元素的索引（下界）。
     *
     * @param nums   输入的有序整数数组
     * @param r      初始右边界索引（通常为数组长度）
     * @param target 目标值
     * @return 第一个大于或等于target的元素索引，或可能的插入位置索引
     */
    private int lowerBound(int[] nums, int r, int target) {
        int l = -1; // 初始化左边界为虚拟索引-1
        while (l + 1 < r) { // 循环条件确保搜索区间有效
            int mid = (l + r) >>> 1; // 无符号位移防止整数溢出
            if (nums[mid] < target) {
                l = mid; // 目标在右半区，更新左边界
            } else {
                r = mid; // 目标在左半区或找到候选，更新右边界
            }
        }
        return r; // 循环结束时r指向第一个有效候选位置
    }


}
