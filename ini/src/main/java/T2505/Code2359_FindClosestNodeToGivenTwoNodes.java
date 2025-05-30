package T2505;

import java.util.Arrays;

/**
 * @Description: 图
 * @Author: iniwym
 * @Date: 2025-05-30
 * @Link: https://leetcode.cn/problems/find-closest-node-to-given-two-nodes/
 */
public class Code2359_FindClosestNodeToGivenTwoNodes {

    /**
     * 寻找最近的相遇节点
     *
     * @param edges 表示图的边的数组，每个元素代表节点指向的下一个节点
     * @param node1 第一个节点的起始点
     * @param node2 第二个节点的起始点
     * @return 返回最近的相遇节点，如果没有相遇节点则返回-1
     */
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        // 图中节点的数量
        int n = edges.length;
        // dist1数组用于存储从node1出发到每个节点的距离
        int[] dist1 = new int[n];
        // dist2数组用于存储从node2出发到每个节点的距离
        int[] dist2 = new int[n];
        // 初始化dist1数组的所有元素为-1，表示尚未访问
        Arrays.fill(dist1, -1);
        // 初始化dist2数组的所有元素为-1，表示尚未访问
        Arrays.fill(dist2, -1);

        // 从 node1 开始遍历
        int cur = node1;
        int d = 0;
        // 遍历直到遇到已访问的节点或到达终点(-1)
        while (cur != -1 && dist1[cur] == -1) {
            dist1[cur] = d;
            d++;
            cur = edges[cur];
        }

        // 从 node2 开始遍历
        cur = node2;
        d = 0;
        // 遍历直到遇到已访问的节点或到达终点(-1)
        while (cur != -1 && dist2[cur] == -1) {
            dist2[cur] = d;
            d++;
            cur = edges[cur];
        }

        // 初始化最小的最大距离为整型最大值
        int minMax = Integer.MAX_VALUE;
        // 初始化答案为-1，表示尚未找到相遇节点
        int ans = -1;
        // 遍历所有节点以找到最近的相遇节点
        for (int i = 0; i < n; i++) {
            // 如果节点i在两个遍历中都被访问过
            if (dist1[i] != -1 && dist2[i] != -1) {
                int maxVal = Math.max(dist1[i], dist2[i]);
                // 如果当前节点的最大距离小于已知的最小最大距离
                if (maxVal < minMax) {
                    minMax = maxVal;
                    ans = i;
                } else if (maxVal == minMax) {
                    // 如果当前节点的最大距离等于已知的最小最大距离，更新答案为较小的节点编号
                    if (i < ans) {
                        ans = i;
                    }
                }
            }
        }
        // 返回最近的相遇节点
        return ans;
    }
}
