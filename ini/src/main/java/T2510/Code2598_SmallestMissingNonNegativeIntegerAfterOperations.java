package T2510;

/**
 * @Description: 同余分组
 * @Author: iniwym
 * @Date: 2025-10-16
 * @Link: https://leetcode.cn/problems/smallest-missing-non-negative-integer-after-operations/
 */
public class Code2598_SmallestMissingNonNegativeIntegerAfterOperations {

    /**
     * 找到数组中无法表示为任意子序列和对m取模后的最小非负整数
     *
     * @param nums 输入的整数数组
     * @param m    取模的基数
     * @return 最小的无法表示的非负整数（对m取模意义下的mex）
     */
    public int findSmallestInteger(int[] nums, int m) {
        // 统计每个数字对m取模后的分布情况
        int[] cnt = new int[m];
        for (int x : nums) {
            cnt[(x % m + m) % m]++; // 保证取模结果在 [0, m) 中
        }

        // 寻找最小的无法表示的非负整数
        int mex = 0;
        while (cnt[mex % m]-- > 0) {
            mex++;
        }
        return mex;
    }
}
