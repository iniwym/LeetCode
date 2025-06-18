package T2506;

import java.util.Arrays;

/**
 * @Description: 排序分组
 * @Author: iniwym
 * @Date: 2025-06-18
 * @Link: https://leetcode.cn/problems/divide-array-into-arrays-with-max-difference/
 */
public class Code2966_DivideArrayIntoArraysWithMaxDifference {

    /**
     * 将一个整数数组分成多个长度为3的子数组，每个子数组中的最大值与最小值的差不超过k
     *
     * @param nums 原始整数数组
     * @param k    子数组中允许的最大差值
     * @return 分割后的子数组构成的二维数组，如果无法满足条件，则返回空数组
     */
    public int[][] divideArray(int[] nums, int k) {
        // 对数组进行排序，以便后续处理
        Arrays.sort(nums);

        // 检查数组长度是否为3的倍数，如果不是，则无法满足每个子数组长度为3的条件，直接返回空数组
        int n = nums.length;
        if (n % 3 != 0) {
            return new int[0][0];
        }

        // 根据数组长度计算可以形成的子数组数量
        int groupCount = n / 3;
        // 初始化结果数组，用于存放分割后的子数组
        int[][] ans = new int[groupCount][3];
        // 标记分割是否有效，初始设为true
        boolean valid = true;

        // 遍历每个子数组
        for (int i = 0; i < groupCount; i++) {
            // 计算当前子数组的起始索引
            int start = i * 3;
            // 检查当前子数组中的最大值与最小值的差是否超过k，如果超过，则分割无效，退出循环
            if (nums[start + 2] - nums[start] > k) {
                valid = false;
                break;
            }
            // 将排序后的元素按顺序放入子数组中
            ans[i][0] = nums[start];
            ans[i][1] = nums[start + 1];
            ans[i][2] = nums[start + 2];
        }

        // 根据分割是否有效，返回结果数组或空数组
        return valid ? ans : new int[0][0];
    }


}
