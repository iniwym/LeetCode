package T2506;

/**
 * @Description: 前缀后缀数组
 * @Author: iniwym
 * @Date: 2025-06-16
 * @Link: https://leetcode.cn/problems/maximum-difference-between-increasing-elements/
 */
public class Code2016_MaximumDifferenceBetweenIncreasingElements {

    /**
     * 计算给定数组中任意两个元素之间的最大差值
     * 其中，最大差值定义为最大元素与最小元素之间的差值
     * 此方法首先通过动态规划的方式找出每个位置左侧的最小值和右侧的最大值，然后计算可能的最大差值
     *
     * @param nums 输入的整数数组
     * @return 返回任意两个元素之间的最大差值如果不存在这样的两个元素使得它们的差值为正，则返回-1
     */
    public int maximumDifference(int[] nums) {
        // 数组长度
        int n = nums.length;
        // min[i]表示从nums[0]到nums[i]之间的最小值
        int[] min = new int[n];
        min[0] = nums[0];
        // max[i]表示从nums[n-1]到nums[i]之间的最大值
        int[] max = new int[n];
        max[n - 1] = nums[n - 1];

        // 填充min数组，确保min[i]存储的是从nums[0]到nums[i]的最小值
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }

        // 填充max数组，确保max[i]存储的是从nums[i]到nums[n-1]的最大值
        for (int i = n - 2; i >= 0; i--) {
            max[i] = Math.max(nums[i], max[i + 1]);
        }

        // 初始化答案为-1，表示如果没有找到有效的最大差值则返回-1
        int ans = -1;
        // 遍历数组，计算最大差值
        for (int i = 0; i < n; i++) {
            // 当且仅当max[i] > min[i]时，才有可能形成正的差值
            if (max[i] > min[i]) {
                // 更新最大差值
                ans = Math.max(max[i] - min[i], ans);
            }
        }

        // 返回计算得到的最大差值
        return ans;
    }

    /**
     * 计算数组中任意两个元素之间的最大差值
     * 差值定义为较大元素与较小元素的差，且较大元素在较小元素之后
     * 如果不存在这样的两个元素，返回-1
     *
     * @param nums 整数数组，代表待分析的数据集
     * @return 数组中任意两个元素之间的最大差值，如果不存在则返回-1
     */
    public int maximumDifference1(int[] nums) {
        // 初始化最大差值为0
        int ans = 0;
        // 初始化前面元素的最小值为整型最大值，便于比较
        int preMin = Integer.MAX_VALUE;
        // 遍历数组中的每个元素
        for (int x : nums) {
            // 计算当前元素与前面元素最小值的差，更新最大差值
            ans = Math.max(ans, x - preMin); // 把 x 当作 nums[j]
            // 更新前面元素的最小值，为下一次比较做准备
            preMin = Math.min(preMin, x);    // 把 x 当作 nums[i]
        }
        // 如果最大差值大于0，则返回最大差值；否则，返回-1，表示没有找到满足条件的元素对
        return ans > 0 ? ans : -1;
    }
}
