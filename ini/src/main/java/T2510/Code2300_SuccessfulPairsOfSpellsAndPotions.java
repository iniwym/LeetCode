package T2510;

import java.util.Arrays;

/**
 * @Description: 二分查找
 * @Author: iniwym
 * @Date: 2025-10-08
 * @Link: https://leetcode.cn/problems/successful-pairs-of-spells-and-potions/
 */
public class Code2300_SuccessfulPairsOfSpellsAndPotions {

    /**
     * 计算每个咒语与药水组合成功的对数
     *
     * @param spells  咒语数组，每个元素表示咒语的强度
     * @param potions 药水数组，每个元素表示药水的强度
     * @param success 成功的最低乘积阈值
     * @return 每个咒语对应的成功组合数数组
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        // 对药水数组进行排序，为后续二分查找做准备
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            // 计算当前咒语需要的最小药水强度，然后用总药水数减去不满足条件的药水数
            spells[i] = potions.length - lowerBound(potions, (double) success / spells[i]);
        }
        return spells;
    }

    /**
     * 在已排序数组中查找第一个大于等于目标值的元素下标（下界二分查找）
     *
     * @param nums   已排序的整数数组
     * @param target 目标值
     * @return 第一个大于等于目标值的元素下标
     */
    private int lowerBound(int[] nums, double target) {
        int left = -1, right = nums.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] <= target
            // nums[right] > target
            int mid = (left + right) >>> 1; // 比 left+(right-left)/2 更快的写法
            if (nums[mid] >= target) {
                right = mid; // 二分范围缩小到 (left, mid)
            } else {
                left = mid; // 二分范围缩小到 (mid, right)
            }
        }
        return right;
    }


}
