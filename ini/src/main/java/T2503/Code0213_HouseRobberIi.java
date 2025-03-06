package T2503;

/**
 * @Description: 子数组最大累加和
 * @Author: iniwym
 * @Date: 2025-03-06
 * @Link: https://leetcode.cn/problems/house-robber-ii/
 */
public class Code0213_HouseRobberIi {
    /**
     * 解决房屋偷窃问题，确保不连续偷窃相邻房屋，特殊情况是首尾房屋也不能同时偷窃
     * 此方法通过动态规划的思想，计算出在不触动警报的情况下，能偷窃到的最大金额
     *
     * @param nums 一个整数数组，代表每个房屋所能偷窃到的金额
     * @return 返回在不触动警报的情况下，能偷窃到的最大金额
     */
    public int rob(int[] nums) {
        int length = nums.length;
        // 如果只有一间房屋，直接返回该房屋的金额
        if (length == 1) {
            return nums[0];
        }

        // 情况一：偷窃 nums[1...n-1] 的房屋
        // 情况二：偷窃 nums[0] + nums[2...n-2] 的房屋
        // 这里使用了动态规划的思想，将原问题分解为两个子问题，分别计算两种情况下的最大偷窃金额
        return Math.max(nums[0] + robRange(nums, 2, length - 2), robRange(nums, 1, length - 1));
    }

    /**
     * 在指定范围内进行抢劫，返回能获得的最大金额
     * 该方法主要解决了在一条线上的房屋中，如何选择抢劫的房屋以获得最大金额的问题
     * 特别的是，第一间和最后一间房屋被认为是相邻的，因此不能同时抢劫
     *
     * @param nums 一个整数数组，表示每间房屋能获得的金额
     * @param l    抢劫范围的左边界
     * @param r    抢劫范围的右边界
     * @return 返回在指定范围内能获得的最大金额
     */
    private int robRange(int[] nums, int l, int r) {
        // 如果左边界大于右边界，说明没有房屋可抢，返回0
        if (l > r) {
            return 0;
        }
        // 如果左边界等于右边界，说明只有一间房屋，直接返回该房屋的金额
        if (l == r) {
            return nums[l];
        }
        // 如果左边界和右边界相邻，说明只有两间房屋，返回金额较大的一间
        if (l + 1 == r) {
            return Math.max(nums[l], nums[r]);
        }

        // 初始化前两间房屋的最大金额
        int prepre = nums[l];
        int pre = Math.max(nums[l], nums[l + 1]);
        // 从第三间房屋开始，计算每间房屋的最大金额
        for (int i = l + 2; i <= r; i++) {
            // 当前房屋的最大金额是：当前房屋的金额加上前一间房屋的最大金额，或者前两间房屋的最大金额中的较大值
            int cur = Math.max(pre, nums[i] + Math.max(prepre, 0));
            // 更新前两间房屋的最大金额
            prepre = pre;
            pre = cur;
        }
        // 返回最后一间房屋的最大金额
        return pre;
    }
}
