package T2503;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 图-下标循环怼
 * @Author: iniwym
 * @Date: 2025-03-29
 * @Link: https://leetcode.cn/problems/longest-cycle-in-a-graph/
 */
public class Code2360_LongestCycleInAGraph {

    /**
     * 找到给定边数组中表示的有向图中的最长环的长度。
     *
     * @param edges 一个整数数组，其中 edges[i] 表示节点i的下一个节点。如果 edges[i] = -1，则节点i没有出边。
     * @return 最长环的长度，若图中不存在环则返回-1。
     */
    public static int longestCycle(int[] edges) {
        int n = edges.length;
        boolean[] visited = new boolean[n];
        int maxCycle = -1;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Map<Integer, Integer> nodeMap = new HashMap<>();
                int current = i;
                int time = 0;

                // 沿着当前路径遍历节点，记录每个节点的访问时间，并检测是否存在环
                while (current != -1 && !visited[current]) {
                    if (nodeMap.containsKey(current)) {
                        // 如果当前节点已在路径中，说明发现环，计算环的长度
                        int cycleLength = time - nodeMap.get(current);
                        maxCycle = Math.max(maxCycle, cycleLength);
                        break;
                    }
                    nodeMap.put(current, time);
                    time++;
                    current = edges[current];
                }

                // 将当前路径中的所有节点标记为已访问，避免重复处理
                for (int node : nodeMap.keySet()) {
                    visited[node] = true;
                }
            }
        }

        return maxCycle;
    }

    /**
     * 寻找图中的最长环的长度。每个节点通过edges数组指向下一个节点，-1表示无连接。
     *
     * @param edges 图的边数组，edges[i]表示节点i的下一个节点，-1表示无连接
     * @return 图中的最长环的长度，若不存在则返回-1
     */
    public static int longestCycle2(int[] edges) {
        int n = edges.length;
        int[] timestamp = new int[n];
        Arrays.fill(timestamp, -1);
        int maxCycle = -1;
        int currentTime = 1;

        // 遍历每个节点，处理未被访问的节点
        for (int i = 0; i < n; i++) {
            if (timestamp[i] == -1) {
                int startTime = currentTime;
                int current = i;

                // 沿着当前路径追踪节点，记录访问时间戳，检测环的形成
                while (current != -1) {
                    if (timestamp[current] != -1) {
                        // 检查是否遇到已访问节点，判断是否形成环
                        if (timestamp[current] >= startTime) {
                            maxCycle = Math.max(maxCycle, currentTime - timestamp[current]);
                        }
                        break;
                    }
                    timestamp[current] = currentTime++;
                    current = edges[current];
                }
            }
        }

        return maxCycle;
    }
}
