package T2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description: Dijkstra
 * @Author: iniwym
 * @Date: 2026-01-27
 * @Link: https://leetcode.cn/problems/minimum-cost-path-with-edge-reversals/
 */
public class Code3650_MinimumCostPathWithEdgeReversals {

    /**
     * 计算从节点0到节点n-1的最小路径成本
     * 图中的边具有不对称的权重：从x到y的权重为wt，从y到x的权重为wt*2
     *
     * @param n     节点数量，节点编号从0到n-1
     * @param edges 边的数组，每条边格式为[x, y, weight]，表示节点x和y之间有一条边，基础权重为weight
     * @return 从节点0到节点n-1的最小路径成本，如果无法到达则返回-1
     */
    public int minCost(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n]; // 邻接表
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int wt = e[2];
            g[x].add(new int[]{y, wt});
            g[y].add(new int[]{x, wt * 2});
        }

        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        // 堆中保存 (起点到节点 x 的最短路长度，节点 x)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dis[0] = 0; // 起点到自己的距离是 0
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int disX = p[0];
            int x = p[1];
            if (disX > dis[x]) { // x 之前出堆过
                continue;
            }
            if (x == n - 1) { // 到达终点
                return disX;
            }
            for (int[] e : g[x]) {
                int y = e[0];
                int wt = e[1];
                int newDisY = disX + wt;
                if (newDisY < dis[y]) {
                    dis[y] = newDisY; // 更新 x 的邻居的最短路
                    // 懒更新堆：只插入数据，不更新堆中数据
                    // 相同节点可能有多个不同的 newDisY，除了最小的 newDisY，其余值都会触发上面的 continue
                    pq.offer(new int[]{newDisY, y});
                }
            }
        }

        return -1;
    }

}
