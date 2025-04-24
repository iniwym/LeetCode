package T2504;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 双指针
 * @Author: iniwym
 * @Date: 2025-04-24
 * @Link: https://leetcode.cn/problems/count-complete-subarrays-in-an-array/
 */
public class Code2799_CountCompleteSubarraysInAnArray {


    /**
     * 计算数组中包含所有不同元素的子数组的数量。
     *
     * @param nums 输入的整数数组
     * @return 包含所有不同元素的子数组的数量
     */
    public int countCompleteSubarrays(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        // 收集数组中的所有不同元素，并计算不同元素的数量
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int distinct = set.size();
        int n = nums.length;

        int res = 0;
        int right = 0;

        // 使用滑动窗口方法统计满足条件的子数组数量
        for (int left = 0; left < n; left++) {
            // 当左指针移动时，从map中移除前一个元素
            if (left > 0) {
                int remove = nums[left - 1];
                map.put(remove, map.get(remove) - 1);
                if (map.get(remove) == 0) {
                    map.remove(remove);
                }
            }

            // 扩张右指针直到窗口包含所有不同元素或到达数组末尾
            while (right < n && map.size() < distinct) {
                int add = nums[right];
                map.put(add, map.getOrDefault(add, 0) + 1);
                right++;
            }

            // 如果当前窗口包含所有不同元素，则所有以left为起点、结束位置在[right-1, n-1]的子数组都满足条件
            if (map.size() == distinct) {
                res += (n - right + 1);
            }
        }
        return res;
    }

}
