package T2509;

import java.util.Arrays;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-09-28
 * @Link: https://leetcode.cn/problems/largest-perimeter-triangle/
 */
public class Code0976_LargestPerimeterTriangle {

    /**
     * 找到数组中能构成三角形的最大周长
     * <p>
     * 解题思路：
     * 1. 首先对数组进行升序排序
     * 2. 从最大的三个数开始检查，判断是否能构成三角形
     * 3. 三角形构成条件：任意两边之和大于第三边，由于已排序，只需检查最小两边之和是否大于最大边
     * 4. 找到第一个满足条件的三元组即为最大周长
     *
     * @param nums 输入的整数数组，表示可能的边长
     * @return 能构成三角形的最大周长，如果无法构成三角形则返回0
     */
    public int largestPerimeter(int[] nums) {
        // 对数组进行升序排序，便于后续贪心策略
        Arrays.sort(nums);

        // 从最大的三个数开始向前遍历，寻找能构成三角形的三元组
        for (int i = nums.length - 1; i >= 2; i--) {
            // 检查是否满足三角形构成条件：两小边之和大于最大边
            // 由于数组已排序，只需验证这一种情况即可
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                // 找到满足条件的三元组，返回周长
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }

        // 无解
        return 0;
    }

}
