package T2508;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-08-24
 * @Link: https://leetcode.cn/problems/find-the-minimum-area-to-cover-all-ones-ii/
 */
public class Code3197_FindTheMinimumAreaToCoverAllOnesIi {

    /**
     * 计算将网格划分为三个矩形区域后，每个区域内包含所有1的最小矩形面积之和。
     * 通过原始网格和旋转90度后的网格分别计算，返回更小的结果。
     *
     * @param grid 输入的二维网格数组，元素为0或1
     * @return 三个矩形区域中包含所有1的最小总面积
     */
    public int minimumSum(int[][] grid) {
        return Math.min(solve(grid), solve(rotate(grid)));
    }

    /**
     * 在给定网格中尝试多种划分方式，计算三个矩形区域包含所有1的最小面积之和。
     *
     * @param a 输入的二维网格数组
     * @return 三个矩形区域的最小总面积
     */
    private int solve(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int ans = Integer.MAX_VALUE;

        // 尝试将网格水平划分为三部分的情况（上中下）
        if (m >= 3) {
            for (int i = 1; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    // 图片上左
                    int area = minimumArea(a, 0, i, 0, n);
                    area += minimumArea(a, i, j, 0, n);
                    area += minimumArea(a, j, m, 0, n);
                    ans = Math.min(ans, area);
                }
            }
        }

        // 尝试将网格划分为L型或其他组合形式的情况
        if (m >= 2 && n >= 2) {
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    // 图片上中：上一块，下左和下右两块
                    int area = minimumArea(a, 0, i, 0, n);
                    area += minimumArea(a, i, m, 0, j);
                    area += minimumArea(a, i, m, j, n);
                    ans = Math.min(ans, area);

                    // 图片上右：上左、上右和下整块
                    area = minimumArea(a, 0, i, 0, j);
                    area += minimumArea(a, 0, i, j, n);
                    area += minimumArea(a, i, m, 0, n);
                    ans = Math.min(ans, area);
                }
            }
        }

        return ans;
    }

    /**
     * 计算在指定行列范围内的最小矩形面积，该矩形必须包含该区域内的所有1。
     *
     * @param a 输入的二维网格数组
     * @param u 起始行索引（包含）
     * @param d 结束行索引（不包含）
     * @param l 起始列索引（包含）
     * @param r 结束列索引（不包含）
     * @return 包含所有1的最小矩形面积
     */
    private int minimumArea(int[][] a, int u, int d, int l, int r) {
        int left = r;
        int right = 0;
        int top = d;
        int bottom = 0;
        for (int i = u; i < d; i++) {
            for (int j = l; j < r; j++) {
                if (a[i][j] == 1) {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    top = Math.min(top, i);
                    bottom = i;
                }
            }
        }
        return (right - left + 1) * (bottom - top + 1);
    }

    /**
     * 将输入的二维矩阵顺时针旋转90度。
     *
     * @param a 输入的二维矩阵
     * @return 顺时针旋转90度后的新矩阵
     */
    private int[][] rotate(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] b = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[j][m - 1 - i] = a[i][j];
            }
        }
        return b;
    }

}
