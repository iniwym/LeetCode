package T2504;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-04-09
 * @Link: https://leetcode.cn/problems/minimum-operations-to-make-array-values-equal-to-k/
 */
public class Code3375_MinimumOperationsToMakeArrayValuesEqualToK {
    /**
     * 计算将数组调整为满足特定条件所需的最小操作次数。
     * 数组必须满足所有元素至少为k，且元素严格递减。
     * 操作允许减少元素的值，但不能增加。
     *
     * @param nums 输入的整数数组
     * @param k    所有元素必须至少为k的最小值
     * @return 满足条件的最小操作次数，若无法满足返回-1
     */
    public int minOperations(int[] nums, int k) {
        // 将数组按升序排序，便于后续处理
        Arrays.sort(nums);
        int n = nums.length;
        // 如果最小元素小于k，无法满足条件
        if (nums[0] < k) {
            return -1;
        }
        // 如果第一个元素等于k，无需调整；否则需要至少一次操作将其减少到k
        int ans = nums[0] == k ? 0 : 1;
        // 从倒数第二个元素开始向前遍历
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] != nums[i + 1]) {
                // 如果当前元素与下一个不同，说明需要保留该元素作为递减序列的一部分
                ans++;
            }
        }
        return ans;
    }


    /**
     * 计算满足条件的最小操作次数。
     *
     * @param nums 输入的整数数组
     * @param k    目标整数值
     * @return 如果数组中存在元素小于k，则返回-1；否则返回数组中大于k的元素的不重复数量。
     */
    public int minOperations2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        // 遍历数组元素，检查是否满足条件并收集大于k的元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < k) {
                return -1;
            } else if (nums[i] > k) {
                set.add(nums[i]);
            }
        }
        return set.size();
    }


}
