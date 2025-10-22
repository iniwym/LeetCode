package T2510;

import java.util.Arrays;

/**
 * @Description: 三指针+双指针
 * @Author: iniwym
 * @Date: 2025-10-22
 * @Link: https://leetcode.cn/problems/maximum-frequency-of-an-element-after-performing-operations-ii/
 */
public class Code3347_MaximumFrequencyOfAnElementAfterPerformingOperationsIi {

    /**
     * 计算在给定操作次数限制下，数组中某个元素的最大频次
     *
     * @param nums          输入的整数数组
     * @param k             操作范围参数，表示可以将元素修改为相差不超过k的值
     * @param numOperations 最大操作次数限制
     * @return 在操作限制下能够达到的最大频次
     */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);

        int n = nums.length;
        int ans = 0;
        int cnt = 0;
        int left = 0;
        int right = 0;
        int left2 = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            // 调整左边界，确保窗口内的元素可以通过操作变为当前元素
            while (nums[left2] < x - k * 2) {
                left2++;
            }
            ans = Math.max(ans, Math.min(i - left2 + 1, numOperations));

            cnt++;
            // 循环直到连续相同段的末尾，这样可以统计出 x 的出现次数
            if (i < n - 1 && x == nums[i + 1]) {
                continue;
            }
            // 调整滑动窗口的左右边界，确保窗口内的元素都可以通过操作变为x
            while (nums[left] < x - k) {
                left++;
            }
            while (right < n && nums[right] <= x + k) {
                right++;
            }
            // 计算当前窗口内可以达到的最大频次
            ans = Math.max(ans, Math.min(right - left, cnt + numOperations));
            cnt = 0;
        }

        return ans;
    }

}
