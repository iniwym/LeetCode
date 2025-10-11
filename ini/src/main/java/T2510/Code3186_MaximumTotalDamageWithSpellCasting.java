package T2510;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 记忆化搜索
 * @Author: iniwym
 * @Date: 2025-10-11
 * @Link: https://leetcode.cn/problems/maximum-total-damage-with-spell-casting/
 */
public class Code3186_MaximumTotalDamageWithSpellCasting {
    /**
     * 计算魔法师的最大总伤害值
     *
     * @param power 法师的伤害值数组
     * @return 最大总伤害值
     */
    public long maximumTotalDamage(int[] power) {
        // 统计每个伤害值出现的次数
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
        }

        // 提取所有不同的伤害值并排序
        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        // 使用记忆化搜索计算最大伤害值
        long[] memo = new long[n];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        return dfs(a, cnt, memo, n - 1);
    }

    /**
     * 使用深度优先搜索计算数组中满足特定条件的元素组合的最大价值和
     *
     * @param a    待处理的整数数组，已排序
     * @param cnt  每个元素出现次数的映射表
     * @param memo 记忆化搜索的备忘录数组，用于存储已计算的结果
     * @param i    当前处理的位置索引
     * @return 从位置i开始能获得的最大价值和
     */
    private long dfs(int[] a, Map<Integer, Integer> cnt, long[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) { // 之前计算过
            return memo[i];
        }

        int x = a[i];
        // 找到所有与当前元素差值不超过2的连续元素段的起始位置
        int j = i;
        while (j > 0 && a[j - 1] >= x - 2) {
            j--;
        }
        // 在选择当前元素和不选择当前元素两种方案中取最大值
        // 选择当前元素时，需要跳过所有与当前元素差值不超过2的元素
        return memo[i] = Math.max(dfs(a, cnt, memo, i - 1), dfs(a, cnt, memo, j - 1) + (long) x * cnt.get(x));
    }

}
