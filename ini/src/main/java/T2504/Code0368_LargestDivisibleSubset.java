package T2504;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-04-06
 * @Link: https://leetcode.cn/problems/largest-divisible-subset/
 */
public class Code0368_LargestDivisibleSubset {
    /**
     * 寻找数组中最大的可被整除子集。
     *
     * @param nums 输入的整数数组
     * @return 包含最大可被整除子集的列表（元素按升序排列）
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 排序后便于后续的整除性判断
        Arrays.sort(nums);

        int n = nums.length;
        // dp[i]表示以nums[i]结尾的最长可被整除子集长度
        int[] dp = new int[n];
        // prev[i]记录生成dp[i]时的前驱元素索引
        int[] prev = new int[n];

        // 初始化每个元素自身至少长度为1
        Arrays.fill(dp, 1);
        // 初始化前驱索引为-1
        Arrays.fill(prev, -1);
        // 记录全局最大长度
        int maxLen = 1;
        // 记录最大子集的末尾索引
        int maxIndex = 0;

        // 动态规划处理：双重循环比较所有可能的前驱关系
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // 通过回溯前驱指针构建结果列表
        List<Integer> result = new ArrayList<>();
        // 从最大子集的末尾开始回溯
        int current = maxIndex;
        while (current != -1) {
            result.add(nums[current]);
            current = prev[current];
        }
        // 反转得到从头到尾的正确顺序
        Collections.reverse(result);
        return result;
    }

}
