package T2511;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 前缀和
 * @Author: iniwym
 * @Date: 2025-11-30
 * @Link: https://leetcode.cn/problems/make-sum-divisible-by-p/
 */
public class Code1590_MakeSumDivisibleByP {

    /**
     * 找到最短的子数组，使得移除该子数组后剩余元素的和能被p整除
     *
     * @param nums 输入的整数数组
     * @param p    用于取模的整数
     * @return 返回需要移除的最短子数组的长度，如果无法实现则返回-1
     */
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        // 构建前缀和数组，每个元素都是模p后的结果
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = (s[i] + nums[i]) % p;
        }
        // 计算整个数组的和模p的结果
        int x = s[n];
        if (x == 0) {
            return 0; // 移除空子数组（这行可以不要）
        }

        int ans = n;
        // 使用HashMap存储每个前缀和最后出现的位置，预分配空间提高性能
        Map<Integer, Integer> last = new HashMap<>(n + 1, 1); // 预分配空间
        for (int i = 0; i <= n; i++) {
            last.put(s[i], i);
            // 查找是否存在前缀和使得当前前缀和减去该前缀和等于x
            // 如果不存在，-n 可以保证 i-j >= n
            int j = last.getOrDefault((s[i] - x + p) % p, -n);
            ans = Math.min(ans, i - j);
        }
        // 如果找到的最短长度小于数组长度则返回该长度，否则返回-1
        return ans < n ? ans : -1;
    }


}
