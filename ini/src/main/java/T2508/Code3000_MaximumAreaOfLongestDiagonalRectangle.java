package T2508;

/**
 * @Description: 遍历数组
 * @Author: iniwym
 * @Date: 2025-08-26
 * @Link: https://leetcode.cn/problems/maximum-area-of-longest-diagonal-rectangle/
 */
public class Code3000_MaximumAreaOfLongestDiagonalRectangle {

    /**
     * 计算给定矩形数组中具有最大对角线长度的矩形的面积
     * 如果多个矩形具有相同的最大对角线长度，则返回其中面积最大的矩形的面积
     *
     * @param dimensions 二维数组，每个子数组包含两个整数，分别表示矩形的长和宽
     * @return 具有最大对角线长度的矩形的面积，如果对角线长度相同则返回面积最大的
     */
    public int areaOfMaxDiagonal(int[][] dimensions) {

        int max = 0;
        int ans = 0;

        // 遍历所有矩形，找出对角线最长的矩形
        // 如果对角线长度相等，则选择面积更大的矩形
        for (int[] d : dimensions) {
            int x = d[0], y = d[1];
            int temp = x * x + y * y;
            if (temp > max || temp == max && x * y > ans) {
                max = temp;
                ans = x * y;
            }

        }

        return ans;

    }


}
