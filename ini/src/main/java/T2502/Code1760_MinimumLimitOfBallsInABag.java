package T2502;

/**
 * @Description: 二分查找法
 * @Author: iniwym
 * @Date: 2025-02-12
 * @Link: https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/
 */
public class Code1760_MinimumLimitOfBallsInABag {

    /**
     * 计算所有数字分解成不大于某个值时所需的最小操作次数
     *
     * @param nums          数组，包含需要进行分解的数字
     * @param maxOperations 最大操作次数
     * @return 返回满足条件的最小数字值
     */
    public int minimumSize(int[] nums, int maxOperations) {
        // 初始化搜索范围的左右边界
        int left = 1, right = 0;

        // 遍历数组，确定搜索范围的右边界
        for (int num : nums) {
            right = Math.max(right, num);
        }

        // 使用二分查找法寻找最小的数字值，使得操作次数不超过maxOperations
        while (left < right) {
            // 计算中间值
            int mid = (left + right) / 2;
            // 初始化当前操作次数为0
            int operations = 0;
            // 遍历数组，计算当前分解方案下的总操作次数
            for (int num : nums) {
                operations += (num - 1) / mid;
            }
            // 如果当前操作次数超过最大允许的操作次数，调整搜索范围的左边界
            if (operations > maxOperations) {
                left = mid + 1;
            } else {
                // 否则，调整搜索范围的右边界
                right = mid;
            }
        }

        // 返回满足条件的最小数字值
        return right;
    }
}
