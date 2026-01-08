package T2601;

import java.util.Arrays;

/**
 * @Description: 记忆化搜索
 * @Author: iniwym
 * @Date: 2026-01-08
 * @Link: https://leetcode.cn/problems/max-dot-product-of-two-subsequences/
 */
public class Code1458_MaxDotProductOfTwoSubsequences {
    /**
     * 计算两个数组的最大点积
     * 通过动态规划的方式找到两个数组中子序列的最大点积
     *
     * @param nums1 第一个整数数组
     * @param nums2 第二个整数数组
     * @return 两个数组能够得到的最大点积
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // 初始化记忆化数组，用于存储已计算的状态结果
        int[][] memo = new int[n][m];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return dfs(n - 1, m - 1, nums1, nums2, memo);
    }


    /**
     * 使用深度优先搜索和记忆化计算两个数组长度相同的非空子序列的最大点积
     *
     * @param i     nums1数组的当前索引位置
     * @param j     nums2数组的当前索引位置
     * @param nums1 第一个整数数组
     * @param nums2 第二个整数数组
     * @param memo  记忆化数组，用于存储已计算的结果
     * @return 从nums1[0, i]和nums2[0, j]中选择长度相同的非空子序列的最大点积
     */
    // 从 nums1[0,i] 和 nums2[0,j] 中选两个长度相同的【非空】子序列的最大点积
    private int dfs(int i, int j, int[] nums1, int[] nums2, int[][] memo) {
        if (i < 0 || j < 0) {
            // 其中一个数组没有元素，无法选出非空子序列
            return Integer.MIN_VALUE; // 下面计算 max 不会取到无解情况
        }

        if (memo[i][j] != Integer.MAX_VALUE) { // 之前计算过
            return memo[i][j];
        }

        // 选 nums1[i] 和 nums2[j]，并考虑与前面子序列拼接或作为新子序列开始
        int res1 = Math.max(dfs(i - 1, j - 1, nums1, nums2, memo), 0) + nums1[i] * nums2[j];

        // 不选 nums1[i]
        int res2 = dfs(i - 1, j, nums1, nums2, memo);

        // 不选 nums2[j]
        int res3 = dfs(i, j - 1, nums1, nums2, memo);

        memo[i][j] = Math.max(res1, Math.max(res2, res3)); // 记忆化
        return memo[i][j];
    }
}
