package T2511;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2025-11-17
 * @Link: https://leetcode.cn/problems/check-if-all-1s-are-at-least-length-k-places-away/
 */
public class Code1437_CheckIfAll1sAreAtLeastLengthKPlacesAway {

    /**
     * 检查数组中所有1之间是否至少相隔k个位置
     *
     * @param nums 输入的二进制数组，只包含0和1
     * @param k    两个1之间需要相隔的最小距离
     * @return 如果所有1都满足相隔至少k个位置则返回true，否则返回false
     */
    public boolean kLengthApart(int[] nums, int k) {
        // 记录上一个1的位置，初始值设为-k-1以确保第一个1不会违反条件
        int last1 = -k - 1;

        // 遍历数组查找所有的1
        for (int i = 0; i < nums.length; i++) {
            // 跳过不是1的元素
            if (nums[i] != 1) {
                continue;
            }

            // 检查当前1与上一个1之间的距离是否小于等于k
            if (i - last1 <= k) {
                return false;
            }

            // 更新上一个1的位置
            last1 = i;
        }

        // 所有1都满足距离要求
        return true;
    }

}
