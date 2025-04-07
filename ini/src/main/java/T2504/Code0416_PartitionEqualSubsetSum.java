package T2504;

/**
 * @Description: 动态规划(01背包)
 * @Author: iniwym
 * @Date: 2025-04-07
 * @Link: https://leetcode.cn/problems/partition-equal-subset-sum/
 */
public class Code0416_PartitionEqualSubsetSum {

    /**
     * 判断是否能将给定整数数组分割成两个子集，使得两子集的和相等。
     * 二维动态规划
     *
     * @param nums 输入的整数数组
     * @return 如果可以分割则返回true，否则返回false
     */
    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int max = 0;
        // 计算数组总和及最大元素值
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        int target = sum / 2;
        // 总和为奇数时直接返回false
        if (sum % 2 != 0) {
            return false;
        }
        // 最大元素超过目标值时无法分割
        if (max > target) {
            return false;
        }
        // 设 dp[i][j] 表示前 i 个元素是否能组成和为 j 的子集。
        boolean[][] dp = new boolean[n + 1][target + 1];
        // 初始化：和为0的子集总是存在
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        // 动态规划填充：遍历每个元素及可能的和值，判断是否可达
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 1; j <= target; j++) {
                if (j == num) {
                    dp[i][j] = true;
                } else if (j > num) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    /**
     * 判断是否能将给定整数数组分割成两个子集，使得两子集的和相等。
     * 该方法使用一维动态规划优化空间复杂度。
     *
     * @param nums 输入的整数数组
     * @return 如果可以分割则返回true，否则返回false
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int max = 0;
        // 计算数组总和及最大元素值
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        int target = sum / 2;
        // 总和为奇数时直接返回false
        if (sum % 2 != 0) {
            return false;
        }
        // 最大元素超过目标值时无法分割
        if (max > target) {
            return false;
        }
        // 使用一维数组压缩空间，dp[j]表示和为j的子集是否存在
        boolean[] dp = new boolean[target + 1];
        // 初始化：和为0的子集始终存在
        dp[0] = true;
        // 动态规划填充：逆序遍历避免重复选择同一元素
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
            // 提前终止条件：当目标和已达成时直接返回(或运算)
            if (dp[target]) {
                return true;
            }
        }
        return dp[target];
    }

}
