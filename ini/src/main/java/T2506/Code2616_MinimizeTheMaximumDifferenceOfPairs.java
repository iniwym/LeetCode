package T2506;

import java.util.Arrays;

/**
 * @Description: 二分查找
 * @Author: iniwym
 * @Date: 2025-06-13
 * @Link: https://leetcode.cn/problems/minimize-the-maximum-difference-of-pairs/
 */
public class Code2616_MinimizeTheMaximumDifferenceOfPairs {
    /**
     * 使用二分查找算法来找到满足特定条件的最小最大值
     * 该方法主要用于处理一对数值对的问题，其中需要找到一个阈值，使得不超过p数量的数对满足条件
     *
     * @param nums 一个整数数组，其中包含待处理的数对
     * @param p    一个整数，表示满足条件的数对的最大数量
     * @return 返回满足条件的最小最大值
     */
    public int minimizeMax(int[] nums, int p) {
        // 对数组进行排序，以便后续处理
        Arrays.sort(nums);
        // 初始化搜索范围的左边界为-1，表示没有有效的最大值
        int left = -1;
        // 初始化搜索范围的右边界为数组中最大值与最小值的差，这是可能的最大值
        int right = nums[nums.length - 1] - nums[0];
        // 使用二分查找法来寻找满足条件的最小最大值
        while (left + 1 < right) {
            // 计算中间值，作为当前的阈值尝试
            int mid = (left + right) >>> 1;
            // 使用check方法判断当前阈值是否满足条件
            if (check(mid, nums, p)) {
                // 如果满足条件，尝试寻找更小的阈值，即向左半边搜索
                right = mid;
            } else {
                // 如果不满足条件，说明当前阈值太小，向右半边搜索
                left = mid;
            }
        }
        // 返回最终找到的满足条件的最小最大值
        return right;
    }


    /**
     * 检查是否存在至少p对索引，它们的值的差不超过mx
     *
     * @param mx   一个假设的最大差值
     * @param nums 一个整数数组，其中包含要检查的元素
     * @param p    要求的至少对数
     * @return 如果存在至少p对元素的差不超过mx，则返回true；否则返回false
     */
    private boolean check(int mx, int[] nums, int p) {
        int cnt = 0; // 初始化满足条件的对数计数器
        // 遍历数组，但不包括最后一个元素，因为它没有后续元素可以形成一对
        for (int i = 0; i < nums.length - 1; i++) {
            // 如果当前元素和下一个元素的差值不超过mx，则认为找到了一对满足条件的元素
            if (nums[i + 1] - nums[i] <= mx) {
                cnt++; // 计数器增加
                i++; // 跳过下一个元素，因为它已经被当前一对元素使用
            }
        }
        // 如果找到的满足条件的对数大于等于p，则返回true，否则返回false
        return cnt >= p;
    }

}
