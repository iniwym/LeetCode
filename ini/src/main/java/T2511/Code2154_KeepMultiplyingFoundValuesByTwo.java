package T2511;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-11-19
 * @Link: https://leetcode.cn/problems/keep-multiplying-found-values-by-two/
 */
public class Code2154_KeepMultiplyingFoundValuesByTwo {

    /**
     * 在数组中查找目标值的最终值
     * <p>
     * 算法逻辑：
     * 1. 将数组中的所有元素存入HashSet中，便于快速查找
     * 2. 从original开始，如果当前值在集合中存在，则将其乘以2
     * 3. 重复步骤2，直到当前值不在集合中为止
     * 4. 返回最终的original值
     *
     * @param nums     整数数组，用于构建查找集合
     * @param original 起始的整数值
     * @return 经过重复查找和倍增操作后的最终值
     */
    public int findFinalValue(int[] nums, int original) {
        // 将数组元素存入HashSet，提高查找效率
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // 持续查找并倍增，直到找不到当前值为止
        while (set.contains(original)) {
            original *= 2;
        }
        return original;

    }

}
