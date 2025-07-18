package T2507;

import java.util.PriorityQueue;

/**
 * @Description: 前后缀分解 + 堆
 * @Author: iniwym
 * @Date: 2025-07-18
 * @Link: https://leetcode.cn/problems/minimum-difference-in-sums-after-removal-of-elements/
 */
public class Code2163_MinimumDifferenceInSumsAfterRemovalOfElements {

    /**
     * 计算将数组分成两部分后的最小差值。
     * 数组被分成两部分：前2n个元素和后n个元素，其中n为数组长度的1/3。
     * 前2n个元素被分成两个大小为n的子数组，计算前n个元素的和与后n个元素的和的差值的最小值。
     *
     * @param nums 输入整数数组，长度为3n
     * @return 前n个元素和与后n个元素和的最小差值
     */
    public long minimumDifference(int[] nums) {
        int m = nums.length;
        int n = m / 3;

        // 使用最小堆维护后n个元素中的最大值，并计算后缀最大和
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        long sum = 0;
        for (int i = m - 1; i >= m - n; i--) {
            minPQ.offer(nums[i]);
            sum += nums[i];
        }

        // 计算从每个位置i开始的后n个元素的最大和（后缀最大和）
        long[] sufMax = new long[m - n + 1];
        sufMax[m - n] = sum;
        for (int i = m - n - 1; i >= n; i--) {
            int v = nums[i];
            if (v > minPQ.peek()) {
                sum += v - minPQ.poll();
                minPQ.offer(v);
            }
            sufMax[i] = sum;
        }

        // 使用最大堆维护前n个元素中的最小值，并计算前缀最小和
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        long preMin = 0;
        for (int i = 0; i < n; ++i) {
            maxPQ.offer(nums[i]);
            preMin += nums[i];
        }

        // 遍历所有可能的分割点，计算最小差值
        long ans = preMin - sufMax[n];
        for (int i = n; i < m - n; i++) {
            int v = nums[i];
            if (v < maxPQ.peek()) {
                preMin += v - maxPQ.poll();
                maxPQ.offer(v);
            }
            ans = Math.min(ans, preMin - sufMax[i + 1]);
        }
        return ans;
    }

}
