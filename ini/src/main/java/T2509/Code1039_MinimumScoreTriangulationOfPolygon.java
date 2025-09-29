package T2509;

import java.util.Arrays;

/**
 * @Description: 递归
 * @Author: iniwym
 * @Date: 2025-09-29
 * @Link: https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/
 */
public class Code1039_MinimumScoreTriangulationOfPolygon {

    /**
     * 计算多边形三角剖分的最小得分
     *
     * @param values 多边形顶点的值数组，按顺时针或逆时针顺序排列
     * @return 三角剖分的最小得分
     */
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        // 创建记忆化数组，用于存储子问题的计算结果
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        // 调用递归函数计算从顶点0到顶点n-1的最小得分
        return dfs(0, n - 1, values, memo);
    }

    /**
     * 使用动态规划和记忆化搜索解决凸多边形三角剖分问题
     * 该函数通过递归地将多边形分割成子多边形来找到最小得分的三角剖分方案
     *
     * @param i    起始顶点索引
     * @param j    结束顶点索引
     * @param v    顶点权值数组
     * @param memo 记忆化数组，用于存储已计算的结果避免重复计算
     * @return 从顶点i到顶点j的子多边形进行三角剖分的最小得分
     */
    private int dfs(int i, int j, int[] v, int[][] memo) {
        if (i + 1 == j) {
            return 0; // 只有两个点，无法组成三角形
        }

        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }

        int res = Integer.MAX_VALUE;
        // 枚举中间顶点k，将多边形分割成三个部分：[i,k]、[k,j]和三角形(i,k,j)
        for (int k = i + 1; k < j; k++) { // 枚举顶点 k
            int subRes = dfs(i, k, v, memo) + dfs(k, j, v, memo) + v[i] * v[j] * v[k];
            res = Math.min(res, subRes);
        }

        return memo[i][j] = res; // 记忆化
    }


}
