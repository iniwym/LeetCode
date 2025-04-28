package T2504;

/**
 * @Description: 双指针
 * @Author: iniwym
 * @Date: 2025-04-28
 * @Link: https://leetcode.cn/problems/count-subarrays-with-score-less-than-k/
 */
public class Code2302_CountSubarraysWithScoreLessThanK {

    /**
     * 计算数组中满足子数组的总和乘以长度小于k的子数组数量。
     *
     * @param nums 输入的整数数组
     * @param k    阈值，子数组的总和乘以长度必须小于k
     * @return 满足条件的子数组的数量
     */
    public long countSubarrays(int[] nums, long k) {
        int i = 0;
        long sum = 0;
        long ans = 0;
        for (int j = 0; j < nums.length; j++) {
            // 扩展窗口右边界，累加当前元素到总和
            sum += nums[j];
            int len = j - i + 1;
            // 调整左边界i，确保当前窗口的总和乘以长度小于k
            while (sum * len >= k && i <= j) {
                sum -= nums[i];
                i++;
                len = j - i + 1;
            }
            // 当前窗口[i..j]满足条件，所有以j结尾的子数组长度小于等于len的都满足条件
            ans += len;
        }
        return ans;
    }

}
