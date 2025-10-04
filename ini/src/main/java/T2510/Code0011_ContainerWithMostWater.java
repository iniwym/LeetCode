package T2510;

/**
 * @Description: 双指针
 * @Author: iniwym
 * @Date: 2025-10-04
 * @Link: https://leetcode.cn/problems/container-with-most-water/
 */
public class Code0011_ContainerWithMostWater {

    /**
     * 计算数组中能盛最多水的容器面积
     * 使用双指针法从两端向中间收缩，每次移动较矮的那根柱子的指针
     *
     * @param height 表示柱子高度的数组
     * @return 能盛最多水的面积
     */
    public int maxArea(int[] height) {
        int res = 0;
        int i = 0;
        int j = height.length - 1;
        // 双指针从两端向中间移动，计算最大面积
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            res = Math.max(res, area);
            // 移动较短边的指针，尝试寻找更高的柱子
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }


}
