package T2507;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-07-05
 * @Link: https://leetcode.cn/problems/find-lucky-integer-in-an-array/
 */
public class Code1394_FindLuckyIntegerInAnArray {

    /**
     * 查找数组中的幸运数
     * <p>
     * 幸运数定义：数组中某个数字的出现次数等于该数字本身，这样的数字称为幸运数。
     * 如果有多个幸运数，则返回最大的那个；如果没有幸运数，则返回-1。
     *
     * @param arr 待查找的整数数组
     * @return 最大的幸运数，如果没有则返回-1
     */
    public int findLucky(int[] arr) {

        int ans = -1;

        // 使用哈希表统计每个数字的出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer temp : arr) {
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        // 遍历哈希表，查找满足条件的最大幸运数
        for (Map.Entry<Integer, Integer> temp : map.entrySet()) {
            if (temp.getKey().equals(temp.getValue())) {
                ans = Math.max(ans, temp.getKey());
            }
        }

        return ans;
    }

}
