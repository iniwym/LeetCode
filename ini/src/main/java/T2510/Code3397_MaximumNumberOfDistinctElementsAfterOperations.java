package T2510;

import java.util.Arrays;

/**
 * @Description: 贪心策略
 * @Author: iniwym
 * @Date: 2025-10-18
 * @Link: https://leetcode.cn/problems/maximum-number-of-distinct-elements-after-operations/
 */
public class Code3397_MaximumNumberOfDistinctElementsAfterOperations {

    /**
     * 计算在允许修改数组元素的情况下，能够获得的最大不同元素个数
     * 对于每个元素，可以将其值修改为原始值加减k范围内的任意整数
     *
     * @param nums 输入的整数数组
     * @param k    允许修改的范围值
     * @return 返回能够获得的最大不同元素个数
     */
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int pre = Integer.MIN_VALUE; // 记录每个人左边的人的位置
        // 贪心策略：从左到右遍历排序后的数组，为每个元素选择最优的位置
        for (int x : nums) {
            x = Math.min(Math.max(x - k, pre + 1), x + k);
            if (x > pre) {
                ans++;
                pre = x;
            }
        }
        return ans;
    }

}

