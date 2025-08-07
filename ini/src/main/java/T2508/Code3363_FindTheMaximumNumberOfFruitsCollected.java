package T2508;

import java.util.Arrays;

/**
 * @Description: 记忆化搜索
 * @Author: iniwym
 * @Date: 2025-08-07
 * @Link: https://leetcode.cn/problems/find-the-maximum-number-of-fruits-collected/
 */
public class Code3363_FindTheMaximumNumberOfFruitsCollected {

    /**
     * 计算三个小朋友收集水果的最大数量
     *
     * @param fruits 表示水果分布的二维矩阵，fruits[i][j] 表示位置 (i,j) 上的水果数量
     * @return 三个小朋友能够收集到的水果总数量
     */
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }

        int ans = 0;
        // 从 (0, 0) 出发的小朋友
        for (int i = 0; i < n; i++) {
            ans += fruits[i][i];
        }

        // 从 (0, n - 1) 出发的小朋友（倒着走）
        // 从下往上走，方便 1:1 翻译成递推
        ans += dfs(n - 2, n - 1, fruits, memo);

        // 从 (n - 1, 0) 出发的小朋友（按照主对角线翻转，然后倒着走）
        // 把下三角形中的数据填到上三角形中
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                fruits[j][i] = fruits[i][j];
            }
        }
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return ans + dfs(n - 2, n - 1, fruits, memo);
    }

    /**
     * 使用深度优先搜索计算从顶部到底部路径上的最大水果值
     *
     * @param i      当前行索引
     * @param j      当前列索引
     * @param fruits 存储水果值的二维数组
     * @param memo   记忆化数组，用于存储已计算的结果
     * @return 从当前位置到顶部路径上的最大水果值总和
     */
    private int dfs(int i, int j, int[][] fruits, int[][] memo) {
        int n = fruits.length;
        // 边界检查：列索引超出有效范围
        if (j < n - 1 - i || j >= n) {
            return 0;
        }
        // 到达顶部行，返回当前水果值
        if (i == 0) {
            return fruits[i][j];
        }
        // 如果已经计算过该位置的结果，直接返回记忆化的值
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 递归计算从三个可能的上一层位置到达当前位置的最大值
        int res1 = dfs(i - 1, j - 1, fruits, memo);
        int res2 = dfs(i - 1, j, fruits, memo);
        int res3 = dfs(i - 1, j + 1, fruits, memo);
        // 取三个路径中的最大值加上当前位置的水果值，并进行记忆化存储
        int res = Math.max(Math.max(res1, res2), res3) + fruits[i][j];
        return memo[i][j] = res;
    }

}