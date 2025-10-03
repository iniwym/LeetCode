package T2510;

import java.util.PriorityQueue;

/**
 * @Description: 双指针
 * @Author: iniwym
 * @Date: 2025-10-03
 * @Link: https://leetcode.cn/problems/trapping-rain-water-ii/
 */
public class Code0407_TrappingRainWaterIi {

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 计算二维高度图中可以接住的雨水体积。
     * 使用优先队列（最小堆）实现从边界向内部逐步收缩的方法，
     * 类似于“木桶效应”，每次处理当前最矮的边界点，并更新其相邻未访问点的接水情况。
     *
     * @param heightMap 一个 m x n 的二维数组，表示各个位置的高度
     * @return 返回能够接住的雨水总量
     */
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        // 优先队列按照高度升序排列，存储格式：[高度, 行索引, 列索引]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));

        // 将矩阵四周的格子加入优先队列，并标记为已访问（设为-1）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.add(new int[]{heightMap[i][j], i, j});
                    heightMap[i][j] = -1; // 标记 (i,j) 访问过
                }
            }
        }

        int ans = 0;
        // 方向数组：上、下、左、右四个方向
        int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 不断取出当前最矮的位置进行扩展
        while (!pq.isEmpty()) {
            int[] t = pq.poll(); // 取出当前最短的边界
            int minHeight = t[0], i = t[1], j = t[2]; // minHeight 是当前桶的短板

            // 遍历当前位置的四个相邻格子
            for (int[] d : DIRS) {
                int x = i + d[0], y = j + d[1]; // (i,j) 的邻居坐标
                // 检查是否越界以及该位置是否未被访问
                if (0 <= x && x < m && 0 <= y && y < n && heightMap[x][y] >= 0) {
                    // 如果邻居高度低于当前短板，则可积水
                    ans += Math.max(minHeight - heightMap[x][y], 0);
                    // 新增木板的高度是两者中的较大者
                    pq.add(new int[]{Math.max(minHeight, heightMap[x][y]), x, y});
                    heightMap[x][y] = -1; // 标记为已访问
                }
            }
        }
        return ans;
    }
}


