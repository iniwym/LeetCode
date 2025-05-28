package T2505;

import java.util.*;

/**
 * @Description: 广度优先搜索（BFS）
 * @Author: iniwym
 * @Date: 2025-05-28
 * @Link: https://leetcode.cn/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/
 */
public class Code3372_MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {


    /**
     * 计算两个图中，最多可以达到的目标节点数
     * 该方法主要解决的问题是，在两个不同的图中，如何选择起点，使得从该起点出发，经过不超过k步，可以到达的节点数最大化
     *
     * @param edges1 第一个图的边集合，表示图的结构
     * @param edges2 第二个图的边集合，表示图的结构
     * @param k      从起点出发的最大步数
     * @return 返回一个数组，表示从每个节点出发，可以达到的最大目标节点数
     */
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        // 计算第一个图的节点数
        int n = edges1.length + 1;
        // 计算第二个图的节点数
        int m = edges2.length + 1;

        // 构建第一个图的邻接表表示
        List<List<Integer>> adj1 = buildAdj(n, edges1);
        // 初始化第一个图中每个节点的目标节点数数组
        int[] cnt1 = new int[n];
        // 遍历第一个图中的每个节点，计算从该节点出发的目标节点数
        for (int i = 0; i < n; i++) {
            cnt1[i] = computeCount(adj1, i, k, n);
        }

        // 构建第二个图的邻接表表示
        List<List<Integer>> adj2 = buildAdj(m, edges2);
        // 初始化第二个图中每个节点的目标节点数数组
        int[] cnt2 = new int[m];
        // 初始化第二个图中最大目标节点数
        int maxCnt2 = 0;
        // 根据k值计算第二个图中的目标节点数
        int target = k >= 1 ? k - 1 : -1;
        // 当目标步数大于等于0时，计算第二个图中每个节点的目标节点数，并找出最大值
        if (target >= 0) {
            for (int u = 0; u < m; u++) {
                cnt2[u] = computeCount(adj2, u, target, m);
            }
            maxCnt2 = Arrays.stream(cnt2).max().orElse(0);
        }

        // 初始化答案数组
        int[] answer = new int[n];
        // 遍历第一个图中的每个节点，计算最终结果
        for (int i = 0; i < n; i++) {
            answer[i] = cnt1[i] + maxCnt2;
        }
        // 返回最终结果数组
        return answer;
    }

    /**
     * 构建无向图的邻接表表示
     *
     * @param size  图中顶点的数量
     * @param edges 图中的边，每个边由一对顶点组成
     * @return 返回图的邻接表表示
     */
    private List<List<Integer>> buildAdj(int size, int[][] edges) {
        // 初始化邻接表，为每个顶点创建一个空的邻接列表
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adj.add(new ArrayList<>());
        }

        // 遍历边集合，构建无向图的邻接表
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            // 无向图中，边的两个顶点互相包含对方
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        // 返回构建好的邻接表
        return adj;
    }

    /**
     * 计算从指定起点开始，在最大距离范围内的可达节点数
     *
     * @param adj     表示图的邻接表，每个节点与其相邻节点的列表
     * @param start   起始节点的索引
     * @param maxDist 最大可达到的距离
     * @param size    图中节点的总数
     * @return 在指定最大距离范围内可达的节点数
     */
    private int computeCount(List<List<Integer>> adj, int start, int maxDist, int size) {
        // 如果最大距离小于0，则没有可达节点，返回0
        if (maxDist < 0) return 0;

        // 初始化距离数组，用于记录从起点到每个节点的距离
        int[] dist = new int[size];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        // 使用队列来进行广度优先搜索
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        // 广度优先搜索，计算从起点到每个节点的最短距离
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                // 如果节点v尚未被访问，则更新其距离并加入队列
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    // 如果节点v在最大距离范围内，则将其加入队列以继续探索
                    if (dist[v] <= maxDist) {
                        queue.offer(v);
                    }
                }
            }
        }

        // 统计在最大距离范围内的节点数
        int count = 0;
        for (int d : dist) {
            // 如果节点的距离不为-1且在最大距离范围内，则计数加一
            if (d != -1 && d <= maxDist) {
                count++;
            }
        }
        return count;
    }

}
