package T2511;

/**
 * @Description: 求和取模
 * @Author: iniwym
 * @Date: 2025-11-29
 * @Link: https://leetcode.cn/problems/minimum-operations-to-make-array-sum-divisible-by-k/
 */
public class Code3512_MinimumOperationsToMakeArraySumDivisibleByK {
    /**
     * 计算数组元素和对k取模的结果
     *
     * @param nums 整数数组
     * @param k    取模的基数
     * @return 数组元素和对k取模的结果
     */
    public int minOperations(int[] nums, int k) {
        // 计算数组中所有元素的和
        int sum = 0;
        for (int temp : nums) {
            sum += temp;
        }
        // 返回总和对k取模的结果
        return sum % k;
    }

}
