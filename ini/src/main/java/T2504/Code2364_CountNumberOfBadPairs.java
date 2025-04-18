package T2504;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-04-18
 * @Link: https://leetcode.cn/problems/count-number-of-bad-pairs/
 */
public class Code2364_CountNumberOfBadPairs {

    /**
     * 计算数组中的坏对数量。坏对定义为满足 j - i != nums[j] - nums[i] 的索引对 (i, j)，其中 i < j。
     * 通过跟踪元素值与索引差值的分布，高效计算坏对数量。
     *
     * @param nums 输入的整数数组
     * @return 坏对的总数
     */
    public static long countBadPairs(int[] nums) {
        int n = nums.length;
        long ans = 0;
        // 存储差值（nums[i] - i）及其出现次数
        Map<Integer, Integer> map = new HashMap<>();

        // 遍历数组，维护每个差值的出现次数，并计算当前元素与之前元素形成的坏对数量
        for (int i = 0; i < n; i++) {
            int key = nums[i] - i;
            // 当前元素与之前所有具有相同差值的元素形成好对，其余为坏对。将坏对数量累加到结果中
            ans += i - map.getOrDefault(key, 0);
            // 更新该差值的出现次数
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return ans;
    }

}
