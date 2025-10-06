package T2510;

import java.util.Arrays;

/**
 * @Description: 二分
 * @Author: iniwym
 * @Date: 2025-10-06
 * @Link: https://leetcode.cn/problems/swim-in-rising-water/
 */
public class Code0778_SwimInRisingWater {

    private static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 计算在水域中游泳的最小时间
     *
     * @param grid 表示水域深度的二维数组，grid[i][j] 表示位置 (i,j) 的水深
     * @return 从左上角 (0,0) 游到右下角 (n-1,n-1) 所需的最小时间
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] vis = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(vis[i], -1);
        }

        // 二分查找答案，初始范围为最大起点终点水深-1到n*n-1
        int left = Math.max(grid[0][0], grid[n - 1][n - 1]) - 1;
        int right = n * n - 1;
        while (left + 1 < right) { // 开区间二分
            int mid = left + (right - left) / 2;
            if (dfs(0, 0, mid, grid, vis)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    /**
     * 使用深度优先搜索判断在只访问 grid[i][j] <= mx 的情况下，能否从当前位置到达终点
     *
     * @param i    当前行坐标
     * @param j    当前列坐标
     * @param mx   限制的最大访问值
     * @param grid 网格数据
     * @param vis  访问标记数组，用于记录不同mx值下的访问状态
     * @return 能否到达终点
     */
    private boolean dfs(int i, int j, int mx, int[][] grid, int[][] vis) {
        int n = grid.length;
        if (i == n - 1 && j == n - 1) { // 到达终点
            return true;
        }
        // 标记访问过，避免重复访问
        // 用 mx 区分不同时候的二分，如果 vis[x][y] != mx，说明不是本轮二分访问过的格子
        vis[i][j] = mx;
        for (int[] dir : DIRS) { // 访问相邻的格子
            int x = i + dir[0], y = j + dir[1];
            if (0 <= x && x < n && 0 <= y && y < n && grid[x][y] <= mx && vis[x][y] != mx) {
                if (dfs(x, y, mx, grid, vis)) {
                    return true;
                }
            }
        }
        return false;
    }

}
