package T2506;

import java.util.Arrays;

/**
 * @Description: 排序 + 分割
 * @Author: iniwym
 * @Date: 2025-06-19
 * @Link: https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k/
 */
public class Code2294_PartitionArraySuchThatMaximumDifferenceIsK {

    /**
     * 根据给定的间隔k对数组进行分区
     * 该方法旨在计算，当数组按升序排序后，相邻元素之间的差值首次超过k时，数组可以被分区成多少个子数组
     *
     * @param nums 待分区的整数数组
     * @param k    分区的间隔阈值
     * @return 分区的数量
     */
    public int partitionArray(int[] nums, int k) {
        // 对数组进行排序，以便后续处理
        Arrays.sort(nums);
        // 初始化分区计数器为1，因为至少会有一个分区
        int ans = 1;
        // 记录当前分区的最大值
        int temp = nums[0];
        // 遍历排序后的数组，寻找分区点
        for (int i = 1; i < nums.length; i++) {
            // 如果当前元素与分区最大值的差大于k，则找到一个分区点
            if (temp + k < nums[i]) {
                // 更新当前分区的最大值为当前元素，开始新的分区
                temp = nums[i];
                // 分区计数器加一
                ans++;
            }
        }
        // 返回分区的数量
        return ans;
    }
}
