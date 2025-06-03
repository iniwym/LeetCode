package T2506;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 广度优先搜索（BFS）
 * @Author: iniwym
 * @Date: 2025-06-03
 * @Link: https://leetcode.cn/problems/maximum-candies-you-can-get-from-boxes/
 */
public class Code1298_MaximumCandiesYouCanGetFromBoxes {

    /**
     * 计算可以获得的最大糖果数
     *
     * @param status         每个盒子的状态数组，0表示锁定，1表示未锁定
     * @param candies        每个盒子里的糖果数量数组
     * @param keys           每个盒子里的钥匙数组，可以解锁其他盒子
     * @param containedBoxes 每个盒子里包含的盒子数组
     * @param initialBoxes   初始时拥有的盒子数组
     * @return 最大可以获得的糖果数
     */
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        // 盒子的数量
        int n = status.length;
        // 表示是否拥有某个盒子
        boolean[] ownedBoxes = new boolean[n];
        // 表示是否拥有某个盒子的钥匙
        boolean[] keysOwned = new boolean[n];
        // 表示某个盒子是否已经被访问过
        boolean[] visited = new boolean[n];
        // 用于广度优先搜索的队列
        Queue<Integer> queue = new LinkedList<>();
        // 记录总共获取到的糖果数量
        int totalCandies = 0;

        // 初始化拥有的盒子，并将未锁定的盒子加入队列
        for (int box : initialBoxes) {
            ownedBoxes[box] = true;
            if (status[box] == 1) {
                visited[box] = true;
                queue.offer(box);
            }
        }

        // 使用广度优先搜索遍历盒子
        while (!queue.isEmpty()) {
            int box = queue.poll();
            totalCandies += candies[box];

            // 处理当前盒子中的钥匙
            for (int key : keys[box]) {
                if (!keysOwned[key]) {
                    keysOwned[key] = true;
                }
                // 如果拥有盒子且未访问过，并且盒子是未锁定或拥有钥匙，则访问该盒子
                if (ownedBoxes[key] && !visited[key] && (status[key] == 1 || keysOwned[key])) {
                    visited[key] = true;
                    queue.offer(key);
                }
            }

            // 处理当前盒子中包含的盒子
            for (int cb : containedBoxes[box]) {
                if (!ownedBoxes[cb]) {
                    ownedBoxes[cb] = true;
                    // 如果未访问过并且盒子是未锁定或拥有钥匙，则访问该盒子
                    if (!visited[cb] && (status[cb] == 1 || keysOwned[cb])) {
                        visited[cb] = true;
                        queue.offer(cb);
                    }
                }
            }
        }

        // 返回总共获取到的糖果数量
        return totalCandies;
    }
}
