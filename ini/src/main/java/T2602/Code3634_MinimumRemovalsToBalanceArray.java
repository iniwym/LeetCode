package T2602;

import java.util.Arrays;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2026-02-06
 * @Link: https://leetcode.cn/problems/minimum-removals-to-balance-array/
 */
public class Code3634_MinimumRemovalsToBalanceArray {

    /**
     * 计算从数组中移除最少元素的数量，使得剩余元素的最大值不超过最小值的k倍。
     *
     * @param nums 输入的整数数组
     * @param k    倍数限制因子
     * @return 需要移除的最少元素数量
     */
    public int minRemoval(int[] nums, int k) {
        // 对数组进行排序，便于后续处理
        Arrays.sort(nums);

        int maxSave = 0; // 记录满足条件的最长子数组长度
        int left = 0;    // 滑动窗口左边界指针

        // 遍历数组，使用滑动窗口寻找满足条件的最长子数组
        for (int i = 0; i < nums.length; i++) {
            // 调整左边界，确保窗口内最大值不超过最小值的k倍
            while ((long) nums[left] * k < nums[i]) {
                left++;
            }
            // 更新满足条件的最长子数组长度
            maxSave = Math.max(maxSave, i - left + 1);
        }

        // 返回需要移除的元素数量（总长度减去最长子数组长度）
        return nums.length - maxSave;
    }

}
