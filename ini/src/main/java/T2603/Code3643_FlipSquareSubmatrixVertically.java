package T2603;

/**
 * @Description: 双指针
 * @Author: iniwym
 * @Date: 2026-03-21
 * @Link: https://leetcode.cn/problems/flip-square-submatrix-vertically/
 */
public class Code3643_FlipSquareSubmatrixVertically {

    /**
     * 垂直翻转二维数组中的k×k子矩阵
     * <p>
     * 该方法在原数组上进行操作，将指定位置的k×k子矩阵沿水平中轴线进行垂直翻转。
     * 翻转操作是通过从子矩阵的最外层行开始，逐步向内层交换对应行来完成的。
     *
     * @param grid 输入的二维整数数组，函数将在原数组上进行修改
     * @param x    子矩阵左上角的行索引
     * @param y    子矩阵左上角的列索引
     * @param k    子矩阵的大小（k×k）
     * @return 返回经过垂直翻转后的二维数组（与输入数组相同，因为是原地修改）
     */
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int l = x;
        int r = x + k - 1;
        // 从子矩阵的最外层行开始，向中心移动，逐层交换对应行
        while (l < r) {
            // 交换第l行和第r行在子矩阵范围内的所有元素
            for (int j = y; j < y + k; j++) {
                int tmp = grid[l][j];
                grid[l][j] = grid[r][j];
                grid[r][j] = tmp;
            }
            l++;
            r--;
        }
        return grid;
    }

}
