package T2502;

import java.util.ArrayDeque;

/**
 * @Description: bfs
 * @Author: iniwym
 * @Date: 2025-02-17
 * @Link: https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner/
 */
public class Code2290_MinimumObstacleRemovalToReachCorner {

    /**
     * 计算从网格的左上角到右下角的最小障碍数
     * 使用0-1BFS算法，优化传统的BFS，以更快地找到最小障碍路径
     *
     * @param grid 网格，其中grid[i][j]表示单元格(i,j)的障碍数，0表示无障碍，1表示有障碍
     * @return 从左上角到右下角的最小障碍数，如果无法到达则返回-1
     */
    public int minimumObstacles(int[][] grid) {

        // 定义四个方向移动的坐标变化
        int[] move = {-1, 0, 1, 0, -1};
        // 获取网格的行数和列数
        int m = grid.length;
        int n = grid[0].length;
        // 初始化距离数组，记录从起点到每个点的最小障碍数
        int[][] distance = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        // 使用双端队列来优化BFS搜索
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        // 将起点加入队列
        queue.add(new int[]{0, 0});
        // 初始化起点的距离为0
        distance[0][0] = 0;
        // 当队列不为空时，继续搜索
        while (!queue.isEmpty()) {
            int[] record = queue.pollFirst();
            int x = record[0];
            int y = record[1];
            // 如果到达终点，返回最小障碍数
            if (x == m - 1 && y == n - 1) {
                return distance[x][y];
            }
            // 遍历四个方向
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i];
                int ny = y + move[i + 1];
                // 如果新位置有效且通过当前点到达新点的障碍数更小，则更新距离
                if (nx >= 0 && nx < m && ny >= 0 && ny < n &&
                        grid[nx][ny] + distance[x][y] < distance[nx][ny]) {
                    distance[nx][ny] = grid[nx][ny] + distance[x][y];
                    // 如果新位置无障碍，优先搜索，加入队列头部；否则加入队列尾部
                    if (grid[nx][ny] == 0) {
                        queue.addFirst(new int[]{nx, ny});
                    } else {
                        queue.addLast(new int[]{nx, ny});
                    }
                }
            }
        }
        // 如果无法到达终点，返回-1
        return -1;
    }

}
