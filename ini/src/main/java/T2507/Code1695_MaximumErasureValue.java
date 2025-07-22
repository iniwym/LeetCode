package T2507;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2025-07-22
 * @Link: https://leetcode.cn/problems/maximum-erasure-value/
 */
public class Code1695_MaximumErasureValue {

    /**
     * 计算数组中无重复元素子数组的最大和（滑动窗口解法）
     * <p>
     * 使用滑动窗口技术维护一个不含重复元素的子数组，并记录窗口内元素的和。
     * 当遇到重复元素时，移动左边界直到窗口内不再有重复元素。
     *
     * @param nums 输入数组，包含需要处理的整数
     * @return 无重复元素子数组的最大和
     */
    public int maximumUniqueSubarray(int[] nums) {
        // 计算数组最大值，用于初始化标记数组
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        // 初始化标记数组、结果变量、窗口和及左边界
        boolean[] has = new boolean[mx + 1];
        int ans = 0, s = 0, left = 0;

        // 滑动窗口处理主循环
        for (int x : nums) {
            // 当遇到重复元素时，移动左边界并更新窗口状态
            while (has[x]) {
                has[nums[left]] = false;
                s -= nums[left];
                left++;
            }
            // 标记当前元素并更新窗口和
            has[x] = true;
            s += x;
            // 更新最大和结果
            ans = Math.max(ans, s);
        }
        return ans;
    }

}
