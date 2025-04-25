package T2504;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 前缀和 + 哈希表
 * @Author: iniwym
 * @Date: 2025-04-25
 * @Link: https://leetcode.cn/problems/count-of-interesting-subarrays/
 */
public class Code2845_CountOfInterestingSubarrays {

    /**
     * 计算满足特定条件的子数组数量。子数组需满足其中满足nums[j]%modulo ==k的元素个数模modulo等于k。
     *
     * @param nums   输入的整数列表
     * @param modulo 模运算参数
     * @param k      目标余数值
     * @return 符合条件的子数组数量
     */
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        // 前缀和数组，pre[i] 表示前i个元素中满足 nums[j]%modulo ==k 的元素个数
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            pre[i + 1] = pre[i] + ((num % modulo == k) ? 1 : 0);
        }

        // 哈希表记录前缀和模 modulo 的余数出现的次数
        Map<Long, Integer> map = new HashMap<>();
        // 初始化：前缀和为0时出现1次（即空前缀的情况）
        map.put(0L, 1);
        long result = 0;

        for (int j = 1; j <= n; j++) {
            long current = pre[j]; // 当前前缀和
            // 计算目标余数：(current -k) % modulo，保证结果非负
            long target = (current - k) % modulo;
            if (target < 0) {
                target += modulo;
            }
            result += map.getOrDefault(target, 0);

            // 将当前前缀和取模后的值存入哈希表，供后续查找
            long currMod = current % modulo;
            map.put(currMod, map.getOrDefault(currMod, 0) + 1);
        }

        return result;
    }


}
