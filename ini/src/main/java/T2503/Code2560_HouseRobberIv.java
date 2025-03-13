package T2503;

/**
 * @Description: 子数组最大累加和 + 二分 + 贪心
 * @Author: iniwym
 * @Date: 2025-03-11
 * @Link: https://leetcode.cn/problems/house-robber-iv/
 */
public class Code2560_HouseRobberIv {
    /**
     * 计算在不能选择相邻元素的情况下，选择至少k个元素时的最小可能的最大元素值。
     *
     * @param nums 包含房屋能力值的数组
     * @param k    需要选择的最小房屋数量
     * @return 满足条件的最小最大能力值
     */
    public int minCapability(int[] nums, int k) {

        // 计算初始的二分查找左右边界（最小和最大可能值）
        int l = 0;
        int r = 0;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            r = Math.max(r, nums[i]);
            l = Math.min(l, nums[i]);
        }

        // 二分查找确定最小的最大能力值
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mostRob(nums, n, mid) >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    /**
     * 在给定能力限制下计算可窃取的最多不相邻房间数。
     * 通过动态规划选择不相邻的房间，确保每个房间的能力值不超过m。
     *
     * @param nums 房间的能力值数组，每个元素代表对应房间的能力需求
     * @param n    房间的总数量
     * @param m    允许的最大能力值
     * @return 在满足条件的情况下可窃取的最大房间数量
     */
    private int mostRob1(int[] nums, int n, int m) {
        // 处理基本情况：当只有一个房间时，判断其能力是否符合条件
        if (n == 1) {
            return nums[0] <= m ? 1 : 0;
        }

        // 处理两个房间的情况，只能选择其中一个符合条件的
        if (n == 2) {
            return (nums[0] <= m || nums[1] <= m) ? 1 : 0;
        }

        int[] dp = new int[n];
        dp[0] = nums[0] <= m ? 1 : 0;
        dp[1] = (nums[0] <= m || nums[1] <= m) ? 1 : 0;

        // 动态规划核心：比较选择当前房间（需加上i-2的结果）或不选当前房间（沿用i-1的结果）
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], (nums[i] <= m ? 1 : 0) + dp[i - 2]);
        }
        return dp[n - 1];
    }


    /**
     * 计算在数组中选择不相邻元素且每个元素的值不超过阈值m时能选中的最大元素数量。
     *
     * @param nums 输入的元素数组
     * @param n    数组的长度
     * @param m    元素值的上限阈值
     * @return 满足条件（不相邻且元素值≤m）时的最大元素数量
     */
    private int mostRob3(int[] nums, int n, int m) {
        // 处理边界情况：当只有一个元素时，检查是否符合条件
        if (n == 1) {
            return nums[0] <= m ? 1 : 0;
        }
        // 处理边界情况：当有两个元素时，至少选一个符合条件的元素即可
        if (n == 2) {
            return (nums[0] <= m || nums[1] <= m) ? 1 : 0;
        }
        // 初始化前两个状态：prePre为前前元素的最大数量，pre为前一个元素的最大数量
        int prePre = nums[0] <= m ? 1 : 0;
        int pre = (nums[0] <= m || nums[1] <= m) ? 1 : 0;

        // 动态规划状态转移：计算每个位置的最大数量
        for (int i = 2; i < n; i++) {
            int cnt = Math.max(pre, (nums[i] <= m ? 1 : 0) + prePre);
            prePre = pre;
            pre = cnt;
        }
        return pre;
    }


    /**
     * 使用贪心算法计算在给定数组中最多可选的数量。
     * 选择条件为元素值小于等于m，且不能连续选择相邻元素。
     *
     * @param nums 输入的整数数组
     * @param n    数组的有效长度或处理的元素个数
     * @param m    元素的阈值，只有当元素值≤m时才可被选中
     * @return 在满足条件的情况下可选择的最大元素个数
     */
    private int mostRob(int[] nums, int n, int m) {
        int ans = 0;
        // 遍历数组，每次选择符合条件的元素后跳过下一个以避免连续选择
        for (int i = 0; i < n; i++) {
            if (nums[i] <= m) {
                ans++;
                i++;
            }
        }
        return ans;
    }

}
