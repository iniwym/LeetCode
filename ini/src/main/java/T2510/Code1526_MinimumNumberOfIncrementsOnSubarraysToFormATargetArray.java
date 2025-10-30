package T2510;

/**
 * @Description: 差分
 * @Author: iniwym
 * @Date: 2025-10-30
 * @Link: https://leetcode.cn/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 */
public class Code1526_MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {

    /**
     * 计算构造目标数组所需的最少操作次数
     * 每次操作可以选择任意子数组并将其中所有元素增加1
     *
     * @param target 目标数组，表示最终要构造的数组
     * @return 构造目标数组所需的最少操作次数
     */
    public int minNumberOperations(int[] target) {
        // 题目保证答案在 int 范围内
        int ans = target[0];
        // 遍历数组，累加每个位置相对于前一个位置的增量（如果为正的话）
        // 这个增量表示需要额外进行的操作次数
        for (int i = 1; i < target.length; i++) {
            ans += Math.max(target[i] - target[i - 1], 0);
        }
        return ans;
    }


}
