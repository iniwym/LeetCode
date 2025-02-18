package T2502;

import java.util.ArrayDeque;

/**
 * @Description: bfs
 * @Author: iniwym
 * @Date: 2025-02-12
 * @Link: https://leetcode.cn/problems/as-far-from-land-as-possible/
 */
public class Code1162_AsFarFromLandAsPossible {

    /**
     * 计算给定网格中陆地和水域之间的最大距离
     *
     * @param grid 二维网格，其中0表示水域，1表示陆地
     * @return 最大的距离；如果不存在水域或陆地，则返回-1
     */
    public int maxDistance(int[][] grid) {
        // 检查网格是否为空或无效
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        // 获取网格的行数和列数
        int m = grid.length;
        int n = grid[0].length;

        // 创建一个布尔数组来标记已访问的单元格
        boolean[][] visited = new boolean[m][n];

        // 创建队列 queue，用于存放陆地单元格坐标 [x, y]
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        // 计算陆地的数量
        int lands = 0;

        // 遍历网格，将所有陆地单元格的坐标加入队列，并标记为已访问
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    visited[i][j] = true;
                    queue.add(new int[]{i, j});
                    lands++;
                }
            }
        }

        // 检查网格是否全是水域或全是陆地，如果是，则返回 -1
        if (lands == 0 || lands == m * n) {
            return -1;
        }

        // 定义四个方向的移动（上、右、下、左）
        int[] move = {-1, 0, 1, 0, -1};

        // 初始化层级为0，表示从陆地开始向外扩展的层数
        int level = 0;
        // 当队列不为空时，进行广度优先搜索
        while (!queue.isEmpty()) {
            // 当前层级的单元格数量
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 取出队列头部的单元格坐标
                int[] v = queue.poll();
                int x = v[0];
                int y = v[1];
                // 遍历四个方向
                for (int j = 0; j < 4; j++) {
                    // 计算新的坐标
                    int nx = x + move[j];
                    int ny = y + move[j + 1];
                    // 检查新坐标是否有效且未被访问过
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n
                            && !visited[nx][ny]) {
                        // 将新坐标加入队列，并标记为已访问
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            // 层级加一，表示扩展了一层
            level++;
        }

        // 如果层级为0，表示没有水域；否则返回层级减一，即最大距离
        return level == 0 ? -1 : level - 1;
    }
}
