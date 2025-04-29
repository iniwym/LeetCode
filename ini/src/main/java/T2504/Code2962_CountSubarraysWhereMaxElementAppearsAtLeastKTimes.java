package T2504;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2025-04-29
 * @Link: https://leetcode.cn/problems/count-subarrays-where-max-element-appears-at-least-k-times/
 */
public class Code2962_CountSubarraysWhereMaxElementAppearsAtLeastKTimes {

    /**
     * 计算数组中包含至少k个最大元素的子数组数量
     *
     * @param nums 一个整数数组
     * @param k    最大元素的最小出现次数
     * @return 返回满足条件的子数组数量
     */
    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        int n = nums.length;
        int max = 0;
        int count = 0;
        // 遍历数组，找出最大值和最大值的出现次数
        for (int i = 0; i < n; i++) {
            if (nums[i] == max) {
                count++;
            }
            if (nums[i] > max) {
                max = nums[i];
                count = 1;
            }
        }
        // 如果最大元素的出现次数少于k，直接返回0
        if (count < k) {
            return 0;
        }

        int l = 0;
        int cnt = 0;
        // 再次遍历数组，使用滑动窗口计算满足条件的子数组数量
        for (int r = 0; r < n; r++) {
            if (nums[r] == max) {
                cnt++;
            }
            // 当窗口内最大元素的出现次数等于k时，调整窗口的左边界
            while (cnt == k) {
                if (nums[l] == max) {
                    cnt--;
                }
                l++;
            }
            // 每个右端点 r 对应的有效左端点数目为 l，所有可能的左端点位于 [0, l-1] 区间内
            ans += l;
        }

        return ans;
    }

}
