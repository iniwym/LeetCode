package T2504;

/**
 * @Description: 暴力法
 * @Author: iniwym
 * @Date: 2025-04-17
 * @Link: https://leetcode.cn/problems/count-equal-and-divisible-pairs-in-an-array/
 */
public class Code2176_CountEqualAndDivisiblePairsInAnArray {

    /**
     * 计算数组中满足条件的数对数量。
     * 两个元素构成有效数对的条件是：它们的值相等，且它们的索引乘积能被k整除。
     *
     * @param nums 输入的整数数组
     * @param k    用于判断索引乘积是否为倍数的除数
     * @return 满足条件的数对数量
     */
    public static int countPairs(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        // 遍历每个可能的起始索引i
        for (int i = 0; i < n - 1; i++) {
            // 遍历i之后的每个索引j以避免重复计数
            for (int j = i + 1; j < n; j++) {
                // 检查当前元素是否相等且索引乘积能被k整除
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {10, 2, 3, 4, 9, 6, 3, 10, 3, 6, 3, 9, 1};
        int k = 4;
        System.out.println(countPairs(nums, k));
    }
}
