package T2507;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 遍历数组
 * @Author: iniwym
 * @Date: 2025-07-25
 * @Link: https://leetcode.cn/problems/maximum-unique-subarray-sum-after-deletion/
 */
public class Code3487_MaximumUniqueSubarraySumAfterDeletion {

    /**
     * 计算数组中不重复正数的最大和
     * 如果数组中没有正数，则返回最大的负数
     *
     * @param nums 输入的整数数组
     * @return 不重复正数的最大和，或者最大的负数（当没有正数时）
     */
    public int maxSum(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        int ans = 0;

        // 遍历数组，分别处理正数和负数
        for (int x : nums) {
            if (x < 0) {
                // 记录最大的负数
                max = Math.max(x, max);
            } else if (set.add(x)) {
                // 累加不重复的正数
                ans += x;
            }

        }

        // 如果没有正数，返回最大负数；否则返回正数和
        return set.isEmpty() ? max : ans;
    }


}
