package T2505;

import java.util.*;

/**
 * @Description: 拓扑排序
 * @Author: iniwym
 * @Date: 2025-05-26
 * @Link: https://leetcode.cn/problems/largest-color-value-in-a-directed-graph/
 */
public class Code1857_LargestColorValueInADirectedGraph {
    /**
     * 计算图中从任意节点出发的最大颜色值
     *
     * @param colors 字符串，表示每个节点的颜色
     * @param edges  二维数组，表示图中的边
     * @return 返回最大的颜色值，如果图中存在环，则返回-1
     */
    public int largestPathValue(String colors, int[][] edges) {
        // 节点数量
        int n = colors.length();
        // 邻接表表示图
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        // 入度数组，用于统计每个节点的入度
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            inDegree[v]++;
        }

        // maxColors[i][j]表示节点i及其所有祖先节点中颜色j的最大数量
        int[][] maxColors = new int[n][26];
        // 拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // processed用于统计已处理的节点数
        int processed = 0;
        // globalMax用于记录全局最大颜色值
        int globalMax = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            processed++;

            // 当前节点的颜色
            int color = colors.charAt(u) - 'a';
            // dp数组用于临时存储当前节点的颜色数量
            int[] dp = new int[26];
            for (int c = 0; c < 26; c++) {
                dp[c] = maxColors[u][c];
            }
            dp[color]++;

            // 计算当前节点的最大颜色值
            int currentMax = 0;
            for (int c = 0; c < 26; c++) {
                currentMax = Math.max(currentMax, dp[c]);
            }
            globalMax = Math.max(globalMax, currentMax);

            // 更新邻接节点的颜色数量
            for (int v : adj.get(u)) {
                for (int c = 0; c < 26; c++) {
                    if (dp[c] > maxColors[v][c]) {
                        maxColors[v][c] = dp[c];
                    }
                }
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // 检查是否存在环
        if (processed != n) {
            return -1;
        }
        return globalMax;
    }
}
