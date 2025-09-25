package T2509;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-09-25
 * @Link: https://leetcode.cn/problems/triangle/
 */
public class Code0120_Triangle {

    /**
     * 计算三角形中从顶到底的最小路径和
     *
     * @param triangle 二维列表，表示三角形结构，第i行有i+1个元素
     * @return 从顶部到底部的最小路径和
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 创建备忘录数组，用于存储已计算的结果，避免重复计算
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE); // Integer.MIN_VALUE 表示没有计算过
        }
        return dfs(triangle, 0, 0, memo);
    }


    /**
     * 使用深度优先搜索和记忆化技术计算三角形从顶点到当前节点的最小路径和
     *
     * @param triangle 二维列表，表示三角形结构，每个元素代表节点值
     * @param i        当前行索引
     * @param j        当前列索引
     * @param memo     记忆化数组，用于存储已计算过的节点结果，避免重复计算
     * @return 从当前位置(i, j)到底层的最小路径和
     */
    private int dfs(List<List<Integer>> triangle, int i, int j, int[][] memo) {
        // 到达最后一行，直接返回当前节点值
        if (i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }
        // 如果当前节点已经计算过，直接返回记忆化的结果
        if (memo[i][j] != Integer.MIN_VALUE) { // 之前计算过
            return memo[i][j];
        }
        // 计算当前节点的最小路径和：当前节点值 + 下一层相邻两节点的最小路径和
        return memo[i][j] = Math.min(dfs(triangle, i + 1, j, memo),
                dfs(triangle, i + 1, j + 1, memo)) + triangle.get(i).get(j);
    }

}
