package T2504;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 双指针
 * @Author: iniwym
 * @Date: 2025-04-16
 * @Link: https://leetcode.cn/problems/count-the-number-of-good-subarrays/
 */
public class Code2537_CountTheNumberOfGoodSubarrays {
    /**
     * 计算数组中满足至少k对相同元素的子数组数目。
     * <p>
     * 子数组必须包含至少k对下标(i,j)满足i<j且arr[i]==arr[j]。
     * 使用滑动窗口算法统计符合条件的子数组数量。
     *
     * @param nums 整数数组
     * @param k    需要满足的最小对数
     * @return 符合条件的子数组数目
     */
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        int same = 0, r = -1;
        Map<Integer, Integer> map = new HashMap<>();
        long ans = 0;
        // 滑动窗口的左指针遍历数组
        for (int l = 0; l < n; l++) {
            // 扩展右指针r，直到当前窗口中的对数至少为k
            while (same < k && r + 1 < n) {
                same += map.getOrDefault(nums[++r], 0);
                map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            }
            // 当前窗口满足条件时，统计所有以r为右端点的有效子数组数目
            if (same >= k) {
                ans += n - r;
            }
            // 移除左指针对应的元素，并更新same计数
            map.put(nums[l], map.getOrDefault(nums[l], 0) - 1);
            same -= map.getOrDefault(nums[l], 0);
        }

        return ans;
    }

}
