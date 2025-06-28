package T2506;

import java.util.Arrays;

/**
 * @Description: 排序
 * @Author: iniwym
 * @Date: 2025-06-28
 * @Link: https://leetcode.cn/problems/find-subsequence-of-length-k-with-the-largest-sum/
 */
public class Code2099_FindSubsequenceOfLengthKWithTheLargestSum {

    /**
     * 返回包含k个元素且具有最大和的子序列。
     *
     * @param nums 给定的整数数组
     * @param k    子序列的长度
     * @return 包含最大和的子序列
     */
    public int[] maxSubsequence(int[] nums, int k) {
        // 初始化结果数组
        int n = nums.length;
        int[] ans = new int[k];

        // 创建二维数组存储索引和对应的值
        // vals[i][0] 存储索引 i
        // vals[i][1] 存储 nums[i] 的值
        int[][] vals = new int[n][2];

        // 将数组元素及其索引存入二维数组
        for (int i = 0; i < n; i++) {
            vals[i][0] = i;
            vals[i][1] = nums[i];
        }

        // 按照数值大小对二维数组进行降序排序
        Arrays.sort(vals, (x1, x2) -> Integer.compare(x2[1], x1[1]));

        // 在排序后的数组中，按照索引顺序对前k个元素进行排序
        Arrays.sort(vals, 0, k, (x1, x2) -> Integer.compare(x1[0], x2[0]));

        // 提取排序后元素的值组成最终答案
        for (int i = 0; i < k; ++i) {
            ans[i] = vals[i][1];
        }
        return ans;
    }

}
