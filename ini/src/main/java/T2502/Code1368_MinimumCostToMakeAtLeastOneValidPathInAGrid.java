package T2502;

import java.util.ArrayDeque;

/**
 * @Description: bfs
 * @Author: iniwym
 * @Date: 2025-02-17
 * @Link: https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
 */
public class Code1368_MinimumCostToMakeAtLeastOneValidPathInAGrid {

    /**
     * 使用BFS和双端队列优化来计算从网格的左上角到右下角的最小障碍数
     * 网格中的数字表示方向，1表示向右，2表示向左，3表示向下，4表示向上
     *
     * @param grid 二维数组，表示网格
     * @return 返回从左上角到右下角的最小障碍数，如果无法到达，则返回-1
     */
    public int minCost(int[][] grid) {

        // 1234 表示四个方向，右左下上
        int[][] move = {{}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
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
            for (int i = 1; i <= 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];
                int weight = grid[x][y] != i ? 1 : 0;
                // 如果新位置有效且通过当前点到达新点的障碍数更小，则更新距离
                if (nx >= 0 && nx < m && ny >= 0 && ny < n &&
                        weight + distance[x][y] < distance[nx][ny]) {
                    distance[nx][ny] = weight + distance[x][y];
                    // 如果新位置无障碍，优先搜索，加入队列头部；否则加入队列尾部
                    if (weight == 0) {
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
