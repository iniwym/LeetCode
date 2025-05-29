package T2505;

import java.util.*;

/**
 * @Description: 广度优先搜索（BFS）/ 奇偶性
 * @Author: iniwym
 * @Date: 2025-05-29
 * @Link: https://leetcode.cn/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/
 */
public class Code3373_MaximizeTheNumberOfTargetNodesAfterConnectingTreesIi {

    /**
     * 计算两个树中最大目标节点数
     * 该方法主要解决的问题是找到两个树中，每一棵树中距离根节点较远的节点集合，并计算这些集合的大小
     * 最后，根据第一个树中每个节点到根节点的距离，决定该节点的目标节点数是基于第一个树的偶数距离节点集合大小
     * 还是奇数距离节点集合大小，然后加上第二个树中较大的目标节点集合大小
     *
     * @param edges1 第一个树的边集合，表示节点之间的连接
     * @param edges2 第二个树的边集合，表示节点之间的连接
     * @return 返回一个数组，表示第一个树中每个节点的目标节点数
     */
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        // 第一棵树的节点数
        int n = edges1.length + 1;
        // 第二棵树的节点数
        int m = edges2.length + 1;

        // 处理第一棵树：计算每个节点深度的奇偶性
        // 创建邻接表
        List<List<Integer>> adj1 = new ArrayList<>();
        // 初始化邻接表的每个节点列表
        for (int i = 0; i < n; i++) {
            adj1.add(new ArrayList<>());
        }
        // 遍历所有边构建邻接表
        for (int[] e : edges1) {
            adj1.get(e[0]).add(e[1]);
            adj1.get(e[1]).add(e[0]);
        }

        // 存储第一棵树每个节点的奇偶性（0:偶，1:奇）
        int[] d1 = new int[n];
        // 初始化为-1表示未访问
        Arrays.fill(d1, -1);
        // 根节点0的初始深度为0（偶数）
        d1[0] = 0;
        // 将根节点加入队列
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        // BFS遍历计算所有节点的奇偶性
        while (!queue.isEmpty()) {
            int u = queue.poll();
            // 遍历当前节点的所有邻接节点
            for (int v : adj1.get(u)) {
                // 如果未访问过
                if (d1[v] == -1) {
                    // 奇偶性翻转（异或1）
                    d1[v] = d1[u] ^ 1;
                    // 将子节点加入队列
                    queue.offer(v);
                }
            }
        }

        // 统计第一棵树中奇偶节点的数量
        int c0 = 0, c1 = 0;
        for (int val : d1) {
            if (val == 0) {
                c0++;
            } else {
                c1++;
            }
        }

        // 处理第二棵树：同样计算奇偶性分布
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            adj2.add(new ArrayList<>());
        }
        for (int[] e : edges2) {
            adj2.get(e[0]).add(e[1]);
            adj2.get(e[1]).add(e[0]);
        }

        int[] d2 = new int[m];
        Arrays.fill(d2, -1);
        d2[0] = 0;
        queue.clear();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj2.get(u)) {
                if (d2[v] == -1) {
                    d2[v] = d2[u] ^ 1;
                    queue.offer(v);
                }
            }
        }

        // 统计第二棵树的奇偶节点数，并取最大值
        int c20 = 0, c21 = 0;
        for (int val : d2) {
            if (val == 0) {
                c20++;
            } else {
                c21++;
            }
        }
        // 第二棵树中更大的奇偶类别数量
        int maxSec = Math.max(c20, c21);

        // 计算每个节点的最大目标节点数
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 当前节点的奇偶性决定第一棵树的贡献，加上第二棵树的最大可能贡献
            ans[i] = (d1[i] == 0 ? c0 : c1) + maxSec;
        }
        return ans;
    }

}
