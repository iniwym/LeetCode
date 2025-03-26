package T2503;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-03-26
 * @Link: https://leetcode.cn/problems/determine-the-minimum-sum-of-a-k-avoiding-array/
 */
public class Code2829_DetermineTheMinimumSumOfAKAvoidingArray {

    /**
     * 计算满足特定条件的n个正整数的最小总和。
     * 条件为：任何两个数的和不等于k。
     * 算法通过分阶段选择最小的数来构造数组：
     * 1. 前mid = k/2个数选择1到mid，确保它们的和不超过k-1，因此两两之和不会等于k。
     * 2. 剩余元素从k开始递增，避免与前面的数产生k的和。
     *
     * @param n 要选择的正整数的数量
     * @param k 限制条件值，任何两个数的和不能等于k
     * @return 满足条件的最小总和
     */
    public static int minimumSum(int n, int k) {
        int ans = 0;
        int mid = k / 2;
        int temp = 0;
        // 计算前mid个数的总和（尽可能小的数），确保两两之和不等于k
        for (int i = 1; i <= Math.min(n, mid); i++) {
            ans += i;
            temp++;
        }
        if (n > temp) {
            // 处理剩余的元素，从k开始递增以满足条件
            int ex = n - mid;
            for (int i = 0; i < ex; i++) {
                ans += k;
                k++;
            }
        }
        return ans;
    }

}
