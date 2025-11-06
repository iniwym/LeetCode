package T2511;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description: DFS
 * @Author: iniwym
 * @Date: 2025-11-06
 * @Link: https://leetcode.cn/problems/power-grid-maintenance/
 */
public class Code3607_PowerGridMaintenance {

    class Solution {
        /**
         * 处理一系列查询操作，包括设备离线和查找连通分量中编号最小的在线设备。
         *
         * @param c           设备的数量，设备编号从 1 到 c
         * @param connections 设备之间的连接关系，connections[i] = [a, b] 表示设备 a 和 b 相连
         * @param queries     查询操作数组，queries[i] = [type, x]：
         *                    - 如果 type == 1，表示查询设备 x 所在连通分量中编号最小的在线设备；
         *                    - 如果 type == 2，表示将设备 x 离线。
         * @return 返回所有类型为 1 的查询结果数组
         */
        public int[] processQueries(int c, int[][] connections, int[][] queries) {
            // 构建无向图的邻接表表示
            List<Integer>[] g = new ArrayList[c + 1];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : connections) {
                int x = e[0], y = e[1];
                g[x].add(y);
                g[y].add(x);
            }

            // belong[i] 表示设备 i 属于第几个连通分量（即在哪个堆中）
            int[] belong = new int[c + 1];
            Arrays.fill(belong, -1);

            // 每个连通分量维护一个最小堆，用于快速获取该连通分量中编号最小的在线设备
            List<PriorityQueue<Integer>> heaps = new ArrayList<>();
            PriorityQueue<Integer> pq;

            // 遍历所有设备，对未访问的设备进行 DFS，找出所有连通分量并建立堆
            for (int i = 1; i <= c; i++) {
                if (belong[i] >= 0) {
                    continue;
                }
                pq = new PriorityQueue<>();
                dfs(i, g, belong, heaps.size(), pq);
                heaps.add(pq);
            }

            // 统计类型为 1 的查询数量，用于初始化结果数组
            int ansSize = 0;
            for (int[] q : queries) {
                if (q[0] == 1) {
                    ansSize++;
                }
            }

            int[] ans = new int[ansSize];
            int idx = 0;

            // 记录每个设备是否已离线
            boolean[] offline = new boolean[c + 1];

            // 处理每个查询
            for (int[] q : queries) {
                int x = q[1];
                if (q[0] == 2) {
                    // 类型 2：将设备 x 离线
                    offline[x] = true;
                    continue;
                }

                // 类型 1：查询设备 x 所在连通分量中编号最小的在线设备
                if (!offline[x]) {
                    // 如果设备 x 本身在线，则直接返回它自己
                    ans[idx++] = x;
                    continue;
                }

                // 否则从堆中查找最小的在线设备
                pq = heaps.get(belong[x]);

                // 懒删除：只在取出堆顶时检查是否已离线，若是则删除
                while (!pq.isEmpty() && offline[pq.peek()]) {
                    pq.poll();
                }

                // 如果堆为空，说明该连通分量中所有设备都已离线，返回 -1
                ans[idx++] = pq.isEmpty() ? -1 : pq.peek();
            }

            return ans;
        }

        /**
         * 使用深度优先搜索遍历连通分量，并将节点加入对应的堆中。
         *
         * @param x      当前访问的设备编号
         * @param g      图的邻接表表示
         * @param belong 记录每个设备所属的连通分量编号
         * @param compId 当前连通分量的编号
         * @param pq     当前连通分量对应的最小堆
         */
        private void dfs(int x, List<Integer>[] g, int[] belong, int compId, PriorityQueue<Integer> pq) {
            belong[x] = compId; // 标记设备 x 属于当前连通分量
            pq.offer(x);        // 将设备 x 加入当前连通分量的堆中
            for (int y : g[x]) {
                if (belong[y] < 0) {
                    // 如果相邻设备 y 尚未访问，则递归访问
                    dfs(y, g, belong, compId, pq);
                }
            }
        }
    }

}
