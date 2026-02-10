package T2602;

import java.util.HashMap;

/**
 * @Description: 暴力搜索
 * @Author: iniwym
 * @Date: 2026-02-10
 * @Link: https://leetcode.cn/problems/longest-balanced-subarray-i/
 */
public class Code3719_LongestBalancedSubarrayI {

    /**
     * 计算数组中最长的“平衡子数组”的长度。
     * <p>
     * 平衡子数组定义为：子数组中奇数元素的种类数等于偶数元素的种类数。
     *
     * @param nums 输入的整数数组
     * @return 返回最长平衡子数组的长度
     */
    public int longestBalanced(int[] nums) {
        int len = 0;

        // 外层循环遍历所有可能的起始位置
        for (int i = 0; i < nums.length; i++) {
            // 使用两个哈希表分别记录当前子数组中奇数和偶数的出现次数
            HashMap<Integer, Integer> odd = new HashMap<>();
            HashMap<Integer, Integer> even = new HashMap<>();

            // 内层循环扩展子数组的结束位置
            for (int j = i; j < nums.length; j++) {
                // 根据当前元素的奇偶性选择对应的哈希表进行更新
                HashMap<Integer, Integer> map = (nums[j] & 1) == 1 ? odd : even;
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

                // 如果奇数和偶数的种类数相等，则更新最大长度
                if (odd.size() == even.size()) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }

        return len;
    }

}
