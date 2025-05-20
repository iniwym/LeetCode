package T2505;

/**
 * @Description: 差分数组
 * @Author: iniwym
 * @Date: 2025-05-20
 * @Link: https://leetcode.cn/problems/zero-array-transformation-i/
 */
public class Code3355_ZeroArrayTransformationI {

    /**
     * 检查数组经过一系列区间加1操作后，是否可以将所有元素变为0
     * 通过差分数组优化处理区间加1操作，避免直接操作原始数组导致效率低下
     *
     * @param nums    原始整数数组
     * @param queries 区间查询数组，每个查询由两个整数l和r组成，表示一个闭区间[l, r]
     * @return 如果经过所有查询操作后，数组中的所有元素都能变为0，则返回true；否则返回false
     */
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1]; // 差分数组，长度设为n+1以处理r+1越界的情况

        // 处理每个查询，更新差分数组
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            diff[l]++;          // 区间起始位置加1
            if (r + 1 <= n) {
                diff[r + 1]--;  // 区间结束位置的下一位减1
            }
        }

        int current = 0;
        for (int i = 0; i < n; i++) {
            current += diff[i]; // 计算当前索引的覆盖次数
            if (nums[i] > current) {
                return false;   // 如果当前元素的值大于覆盖次数，无法归零
            }
        }
        return true;
    }

}
