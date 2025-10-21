package T2510;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: 差分
 * @Author: iniwym
 * @Date: 2025-10-21
 * @Link: https://leetcode.cn/problems/maximum-frequency-of-an-element-after-performing-operations-i/
 */
public class Code3346_MaximumFrequencyOfAnElementAfterPerformingOperationsI {

    /**
     * 计算在最多执行numOperations次操作的情况下，数组中某个元素的最大频次
     * 每次操作可以将数组中的一个元素增加或减少最多k的值
     *
     * @param nums          输入的整数数组
     * @param k             每次操作中元素可以改变的最大幅度
     * @param numOperations 最多可以执行的操作次数
     * @return 在给定操作次数下，数组中某个元素能达到的最大频次
     */
    int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> diff = new TreeMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // 统计每个元素的出现次数
            diff.putIfAbsent(x, 0); // 把 x 插入 diff，以保证下面能遍历到 x
            // 使用差分数组技术记录每个值可以被覆盖的范围
            diff.merge(x - k, 1, Integer::sum); // 在位置x-k处加1
            diff.merge(x + k + 1, -1, Integer::sum); // 在位置x+k+1处减1
        }

        int ans = 0;
        int sumD = 0;
        // 遍历差分数组，计算每个位置的实际覆盖次数，并更新最大频次
        for (Map.Entry<Integer, Integer> e : diff.entrySet()) {
            sumD += e.getValue();
            ans = Math.max(ans, Math.min(sumD, cnt.getOrDefault(e.getKey(), 0) + numOperations));
        }
        return ans;
    }

}

