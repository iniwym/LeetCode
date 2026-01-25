package T2601;

import java.util.Arrays;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2026-01-25
 * @Link: https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
 */
public class Code1984_MinimumDifferenceBetweenHighestAndLowestOfKScores {

    /**
     * 找到数组中k个元素的最小差值
     * 通过排序后使用滑动窗口的方式，找到连续k个元素中最大值与最小值的最小差值
     *
     * @param nums 输入的整数数组
     * @param k    要选择的元素个数
     * @return k个元素中的最小差值（最大值与最小值的差）
     */
    public int minimumDifference(int[] nums, int k) {

        Arrays.sort(nums);

        int l = 0;
        int r = k - 1;

        // 初始化答案为前k个元素的最大值与最小值之差
        int ans = nums[r] - nums[l];
        int len = nums.length;

        // 滑动窗口遍历所有可能的k个连续元素组合
        while (r < len) {
            ans = Math.min(ans, nums[r] - nums[l]);
            l++;
            r++;
        }
        return ans;
    }

}
