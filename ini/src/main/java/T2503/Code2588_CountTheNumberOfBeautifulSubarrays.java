package T2503;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 前缀异或和
 * @Author: iniwym
 * @Date: 2025-03-06
 * @Link: https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays/
 */
public class Code2588_CountTheNumberOfBeautifulSubarrays {
    /**
     * 计算美丽子数组的数量
     * 美丽子数组定义为子数组中所有元素的异或结果为0的子数组
     * 此方法通过异或运算和哈希表来高效地计算美丽子数组的数量
     *
     * @param nums 输入的整数数组
     * @return 返回美丽子数组的数量
     */
    public static long beautifulSubarrays(int[] nums) {

        // 存储从数组开始到当前位置的异或结果及其出现的次数
        Map<Integer, Integer> cnt = new HashMap<>();
        // 当前遍历到的异或结果
        int mask = 0;
        // 美丽子数组的数量
        long ans = 0;
        // 初始化哈希表，表示异或结果为0的情况出现了一次
        cnt.put(0, 1);
        // 遍历数组中的每个元素
        for (int x : nums) {
            // 当前位置的异或结果等于前一个位置的异或结果与当前元素的异或
            mask ^= x;
            // 累加美丽子数组的数量，如果当前异或结果之前出现过，则表示找到了一个或多个美丽子数组
            ans += cnt.getOrDefault(mask, 0);
            // 更新哈希表，记录当前异或结果出现的次数
            cnt.put(mask, cnt.getOrDefault(mask, 0) + 1);
        }
        // 返回美丽子数组的总数量
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 3, 1, 2, 4};  // 符合条件子数组：[3, 1, 2], [4, 3, 1, 2, 4]
        System.out.println(beautifulSubarrays(nums1));  // 输出: 2
    }
}
