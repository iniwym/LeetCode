package T2504;

/**
 * @Description: 贪心/前缀树
 * @Author: iniwym
 * @Date: 2025-04-03
 * @Link: https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-ii/
 */
public class Code2874_MaximumValueOfAnOrderedTripletIi {

    /**
     * 计算数组中三元组的最大值，其中三元组由元素i<j<k组成，计算表达式（leftMax[j] - nums[j]）* rightMax[j]的最大值。
     * 其中leftMax[j]是j之前元素的最大值，rightMax[j]是j之后元素的最大值。
     *
     * @param nums 输入的整数数组
     * @return 最大的三元组值，若不存在有效三元组则返回0
     */
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long ans = 0;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // 计算每个位置j的leftMax[j]，即j之前元素的最大值
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);
        }

        // 计算每个位置k的rightMax[k]，即k之后元素的最大值
        for (int k = n - 2; k >= 0; k--) {
            rightMax[k] = Math.max(rightMax[k + 1], nums[k + 1]);
        }

        // 遍历中间元素j，计算并更新最大三元组值
        for (int j = 1; j < n - 1; j++) {
            ans = Math.max(ans, (long) (leftMax[j] - nums[j]) * rightMax[j]);
        }
        return ans;
    }

}
