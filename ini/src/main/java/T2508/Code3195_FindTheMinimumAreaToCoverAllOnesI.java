package T2508;

/**
 * @Description: 寻找边界
 * @Author: iniwym
 * @Date: 2025-08-22
 * @Link: https://leetcode.cn/problems/find-the-minimum-area-to-cover-all-ones-i/
 */
public class Code3195_FindTheMinimumAreaToCoverAllOnesI {

    /**
     * 计算二维网格中所有值为1的元素所构成的最小矩形区域的面积
     *
     * @param grid 二维网格数组，其中元素值为0或1
     * @return 包含所有值为1元素的最小矩形区域的面积
     */
    public int minimumArea(int[][] grid) {
        // 初始化边界值，用于记录包含所有1元素的矩形边界
        int left = Integer.MAX_VALUE;
        int right = 0;
        int top = Integer.MAX_VALUE;
        int bottom = 0;

        // 遍历整个网格，找到所有值为1的元素的位置，并更新矩形边界
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    top = Math.min(top, i);
                    bottom = i;
                }
            }
        }

        // 计算并返回最小矩形区域的面积
        return (right - left + 1) * (bottom - top + 1);
    }


}
