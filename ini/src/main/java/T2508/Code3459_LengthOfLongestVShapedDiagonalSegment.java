package T2508;

/**
 * @Description: 记忆化搜索
 * @Author: iniwym
 * @Date: 2025-08-27
 * @Link: https://leetcode.cn/problems/length-of-longest-v-shaped-diagonal-segment/
 */
public class Code3459_LengthOfLongestVShapedDiagonalSegment {
    /**
     * 四个方向：右下、右上、左上、左下（用于遍历对角线方向）
     */
    private static final int[][] DIRS = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    /**
     * 计算网格中满足条件的最长“V型对角线”路径长度。
     * <p>
     * 一条合法的 V 型对角线路径由以下规则构成：
     * - 起始于值为 1 的格子；
     * - 每一步必须沿着对角线方向移动（即 DIRS 中的四个方向之一）；
     * - 可以在某个点右转一次，之后不能再转向；
     * - 路径上的值必须交替为 1 和 2。
     *
     * @param grid 输入的二维网格，每个元素为 0、1 或 2
     * @return 最长 V 型对角线路径的长度
     */
    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // memo[i][j][mask] 表示从位置 (i,j) 出发，在特定状态 mask 下能走的最长路径长度
        // mask 编码了方向 k 和是否还能右转的信息
        int[][][] memo = new int[m][n][1 << 3];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) { // 枚举起始方向
                        ans = Math.max(ans, dfs(i, j, k, 1, 2, grid, memo) + 1);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 使用深度优先搜索（DFS）计算从当前位置出发的最长路径长度。
     *
     * @param i       当前行坐标（上一步的位置）
     * @param j       当前列坐标（上一步的位置）
     * @param k       当前移动方向索引（对应 DIRS 数组）
     * @param canTurn 是否还能右转（1 表示可以，0 表示不可以）
     * @param target  当前位置期望的目标值（1 或 2）
     * @param grid    网格数据
     * @param memo    记忆化数组，避免重复计算
     * @return 从当前位置开始可延伸的最长路径长度
     */
    private int dfs(int i, int j, int k, int canTurn, int target, int[][] grid, int[][][] memo) {
        i += DIRS[k][0];
        j += DIRS[k][1];
        // 边界检查或值不匹配则终止
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != target) {
            return 0;
        }
        int mask = k << 1 | canTurn;
        if (memo[i][j][mask] > 0) { // 之前已经计算过该状态
            return memo[i][j][mask];
        }
        int res = dfs(i, j, k, canTurn, 2 - target, grid, memo) + 1; // 继续直行
        if (canTurn == 1) {
            res = Math.max(res, dfs(i, j, (k + 1) % 4, 0, 2 - target, grid, memo) + 1); // 右转
        }
        return memo[i][j][mask] = res; // 记忆化结果并返回
    }

}
