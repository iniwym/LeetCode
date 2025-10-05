package T2510;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: DFS
 * @Author: iniwym
 * @Date: 2025-10-05
 * @Link: https://leetcode.cn/problems/pacific-atlantic-water-flow/
 */
public class Code0417_PacificAtlanticWaterFlow {

    // 四个方向：左、右、上、下
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 解决太平洋大西洋水流问题的类。
     * 给定一个二维矩阵表示岛屿的高度，找出所有能够同时流向太平洋和大西洋的坐标点。
     * 找出可以同时流向太平洋和大西洋的所有单元格坐标。
     *
     * @param heights 表示岛屿高度的二维数组，其中 heights[i][j] 是第 i 行第 j 列的高度
     * @return 包含坐标的列表，每个坐标是一个能同时流到两个大洋的单元格位置 [row, col]
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;

        // 从太平洋边界开始进行深度优先搜索（DFS）
        boolean[][] pacificVis = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            dfs(0, j, pacificVis, heights); // 上边界（太平洋）
        }
        for (int i = 1; i < m; i++) {
            dfs(i, 0, pacificVis, heights); // 左边界（太平洋）
        }

        // 从大西洋边界开始进行深度优先搜索（DFS）
        boolean[][] atlanticVis = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            dfs(m - 1, j, atlanticVis, heights); // 下边界（大西洋）
        }
        for (int i = 0; i < m - 1; i++) {
            dfs(i, n - 1, atlanticVis, heights); // 右边界（大西洋）
        }

        // 收集同时可达太平洋和大西洋的位置作为结果
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificVis[i][j] && atlanticVis[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    /**
     * 使用深度优先搜索标记可以从指定起点反向流动到海洋的所有单元格。
     *
     * @param i       当前单元格的行索引
     * @param j       当前单元格的列索引
     * @param vis     记录已访问单元格的布尔矩阵
     * @param heights 岛屿高度矩阵
     */
    private void dfs(int i, int j, boolean[][] vis, int[][] heights) {
        if (vis[i][j]) { // 若当前节点已被访问过则直接返回
            return;
        }
        vis[i][j] = true; // 标记当前位置为已访问
        for (int[] d : DIRS) { // 遍历四个方向
            int x = i + d[0], y = j + d[1];
            // 检查是否越界，并且目标点高度大于等于当前点高度（水往低处流的逆过程）
            if (0 <= x && x < heights.length && 0 <= y && y < heights[x].length && heights[x][y] >= heights[i][j]) {
                dfs(x, y, vis, heights);
            }
        }
    }

}
