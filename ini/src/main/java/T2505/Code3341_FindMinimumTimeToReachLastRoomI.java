package T2505;

import java.util.PriorityQueue;

/**
 * @Description: Dijkstra
 * @Author: iniwym
 * @Date: 2025-05-07
 * @Link: https://leetcode.cn/problems/find-minimum-time-to-reach-last-room-i/
 */
public class Code3341_FindMinimumTimeToReachLastRoomI {

    // 0:上，1:右，2:下，3:左
    public static int[] move = new int[]{-1, 0, 1, 0, -1};

    /**
     * 计算从左上角到右下角的最小时间
     *
     * @param moveTime 二维数组，表示每个位置的移动时间
     * @return 返回从左上角到右下角的最小时间，如果无法到达则返回-1
     */
    public int minTimeToReach(int[][] moveTime) {
        // (0,0)源点
        // -> (x,y)
        // 初始化行数和列数
        int n = moveTime.length;
        int m = moveTime[0].length;
        // 初始化距离数组，用于记录从起点到每个点的最短时间
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 将所有距离初始化为最大值，表示未计算最短路径
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        // 起点到起点的距离为0
        distance[0][0] = 0;
        // 初始化访问数组，用于标记某个点是否已被访问
        boolean[][] visited = new boolean[n][m];
        // 优先队列，用于存储待访问的点及其距离，按距离从小到大排序
        // 0 : 格子的行
        // 1 : 格子的列
        // 2 : 源点到当前格子的代价
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
        // 将起点加入优先队列
        heap.add(new int[]{0, 0, 0});
        // 循环直到优先队列为空
        while (!heap.isEmpty()) {
            // 取出距离最小的点
            int[] record = heap.poll();
            int x = record[0];
            int y = record[1];
            int c = record[2];
            // 如果该点已被访问，则跳过
            if (visited[x][y]) {
                continue;
            }
            // 如果到达终点，则返回当前代价
            if (x == n - 1 && y == m - 1) {
                // 常见剪枝
                // 发现终点直接返回
                // 不用等都结束
                return c;
            }
            // 标记当前点为已访问
            visited[x][y] = true;
            // 遍历四个方向
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i];
                int ny = y + move[i + 1];
                // 检查新点是否在范围内且未被访问
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    // 计算新点的代价
                    int nc = Math.max(c, moveTime[nx][ny]) + 1;
                    // 如果新代价小于已记录的最短距离，则更新距离并加入优先队列
                    if (nc < distance[nx][ny]) {
                        distance[nx][ny] = nc;
                        heap.add(new int[]{nx, ny, nc});
                    }
                }
            }
        }
        // 如果无法到达终点，返回-1
        return -1;
    }

}
