package T2602;

import java.util.Arrays;

/**
 * @Description: 数组排序
 * @Author: iniwym
 * @Date: 2026-02-01
 * @Link: https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-i/
 */
public class Code3010_DivideAnArrayIntoSubarraysWithMinimumCostI {

    /**
     * 计算数组中前三个元素的最小成本。
     * <p>
     * 该函数首先对数组从索引1开始到末尾的部分进行排序，
     * 然后返回数组前三个元素的和。
     *
     * @param nums 输入的整数数组，至少包含三个元素
     * @return 前三个元素的和，表示最小成本
     */
    public int minimumCost(int[] nums) {
        // 对数组从索引1到末尾的部分进行排序
        Arrays.sort(nums, 1, nums.length);
        // 返回前三个元素的和
        return nums[0] + nums[1] + nums[2];
    }


}
