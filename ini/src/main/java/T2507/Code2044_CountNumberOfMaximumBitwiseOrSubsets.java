package T2507;

/**
 * @Description: dfs
 * @Author: iniwym
 * @Date: 2025-07-28
 * @Link: https://leetcode.cn/problems/count-number-of-maximum-bitwise-or-subsets/
 */
public class Code2044_CountNumberOfMaximumBitwiseOrSubsets {

    /**
     * 计算数组中能够产生最大按位或值的非空子集数量
     *
     * @param nums 输入的整数数组
     * @return 能够产生最大按位或值的子集数量
     */
    public int countMaxOrSubsets(int[] nums) {
        // 计算所有元素的按位或结果，即可能达到的最大按位或值
        int totalOr = 0;
        for (int x : nums) {
            totalOr |= x;
        }

        // 通过深度优先搜索统计能够达到最大按位或值的子集数量
        dfs(0, 0, nums, totalOr);
        return ans;
    }


    private int ans = 0;

    /**
     * 使用深度优先搜索计算能够获得目标或值的子集数量
     *
     * @param i        当前处理的数组索引
     * @param subsetOr 当前子集的或运算结果
     * @param nums     输入的整数数组
     * @param totalOr  目标或值（整个数组的或运算结果）
     */
    private void dfs(int i, int subsetOr, int[] nums, int totalOr) {
        // 如果当前子集的或值已经等于目标值，则剩余元素可以任意选择
        if (subsetOr == totalOr) {
            ans += 1 << (nums.length - i);
            return;
        }
        // 如果已经处理完所有元素，则结束递归
        if (i == nums.length) {
            return;
        }
        dfs(i + 1, subsetOr, nums, totalOr); // 不选 nums[i]
        dfs(i + 1, subsetOr | nums[i], nums, totalOr); // 选 nums[i]
    }

}
