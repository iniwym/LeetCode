package T2603;

import java.util.Arrays;

/**
 * @Description: 暴力破解
 * @Author: iniwym
 * @Date: 2026-03-20
 * @Link: https://leetcode.cn/problems/minimum-absolute-difference-in-sliding-submatrix/
 */
public class Code3567_MinimumAbsoluteDifferenceInSlidingSubmatrix {

    /**
     * 计算滑动子矩阵中的最小绝对差
     * 对于每个 k x k 的子矩阵，找出其中任意两个不同元素的最小绝对差值
     *
     * @param grid 输入的二维整数矩阵
     * @param k    子矩阵的边长
     * @return 二维数组，ans[i][j] 表示以 (i,j) 为左上角的 k x k 子矩阵中的最小绝对差
     */
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // 结果数组，存储每个 k x k 子矩阵的最小绝对差
        int[][] ans = new int[m - k + 1][n - k + 1];
        // 用于存储当前子矩阵中所有元素的临时数组
        int[] a = new int[k * k];
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                // 提取当前 k x k 子矩阵的所有元素到数组 a 中
                int idx = 0;
                for (int x = 0; x < k; x++) {
                    for (int y = 0; y < k; y++) {
                        a[idx++] = grid[i + x][j + y];
                    }
                }
                // 对数组排序，使相邻元素成为最可能产生最小差值的候选
                Arrays.sort(a);

                // 遍历排序后的数组，计算相邻不同元素之间的最小差值
                int res = Integer.MAX_VALUE;
                for (int p = 1; p < a.length; p++) {
                    if (a[p] > a[p - 1]) { // 题目要求相减的两个数必须不同
                        res = Math.min(res, a[p] - a[p - 1]);
                    }
                }
                // 如果找到了有效的最小差值，则存入结果数组
                if (res < Integer.MAX_VALUE) {
                    ans[i][j] = res;
                }
            }
        }
        return ans;
    }


}
