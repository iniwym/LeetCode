package T2507;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-07-26
 * @Link: https://leetcode.cn/problems/maximize-subarrays-after-removing-one-conflicting-pair/
 */
public class Code3480_MaximizeSubarraysAfterRemovingOneConflictingPair {

    /**
     * 计算满足冲突约束条件下的最大子数组价值
     *
     * @param n                冲突对涉及的元素个数
     * @param conflictingPairs 冲突对数组，每个元素是一个包含两个整数的数组，表示两个元素不能在同一个子数组中
     * @return 满足约束条件下的最大子数组价值
     */
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        // 初始化分组数组，用于存储每个位置的冲突关系
        List<Integer>[] groups = new ArrayList[n + 1];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int[] p : conflictingPairs) {
            int a = p[0];
            int b = p[1];
            groups[Math.min(a, b)].add(Math.max(a, b));
        }

        long ans = 0;
        long maxExtra = 0;
        long[] extra = new long[n + 2];
        // 维护最小和次小的b值
        List<Integer> b = new ArrayList<>();
        b.add(n + 1);
        b.add(n + 1);

        // 从后往前遍历，计算每个位置的贡献值
        for (int i = n; i > 0; i--) {
            // 维护最小 b 和次小 b
            b.addAll(groups[i]);
            Collections.sort(b);
            b.subList(2, b.size()).clear();

            int b0 = b.get(0);
            ans += b0 - i;
            extra[b0] += b.get(1) - b0;
            maxExtra = Math.max(maxExtra, extra[b0]);
        }

        return ans + maxExtra;
    }

}
