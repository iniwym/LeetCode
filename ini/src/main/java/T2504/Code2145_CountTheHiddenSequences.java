package T2504;

/**
 * @Description: 数学推导
 * @Author: iniwym
 * @Date: 2025-04-21
 * @Link: https://leetcode.cn/problems/count-the-hidden-sequences/
 */
public class Code2145_CountTheHiddenSequences {

    /**
     * 计算满足条件的起始值数量。
     * 给定差异数组differences，起始值s的范围是[lower, upper]，
     * 使得生成的数组所有元素均在[lower, upper]范围内。
     *
     * @param differences 差异数组，表示每个元素与前一个元素的差值序列
     * @param lower       起始值的最小可能值
     * @param upper       起始值的最大可能值
     * @return 满足条件的起始值的数量
     */
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long minStart = Long.MAX_VALUE;
        long maxStart = Long.MIN_VALUE;

        // 计算差异序列的累积值，并记录最小和最大累积值
        long current = 0;
        long min = 0, max = 0;
        for (int d : differences) {
            current += d;
            min = Math.min(min, current);
            max = Math.max(max, current);
        }

        /**
         * 合法的起始值 s 必须满足以下条件：
         * 条件 1：s + min >= lower → s >= lower - min。
         * 条件 2：s + max <= upper → s <= upper - max。
         * 因此，起始值的合法范围为 [validLower, validUpper]
         */
        long validLower = (long) lower - min;
        long validUpper = (long) upper - max;

        // 计算起始值的合法范围与lower/upper的交集区间
        long actualLower = Math.max(lower, validLower);
        long actualUpper = Math.min(upper, validUpper);

        if (actualLower > actualUpper) {
            return 0;
        }
        return (int) (actualUpper - actualLower + 1);
    }


}
