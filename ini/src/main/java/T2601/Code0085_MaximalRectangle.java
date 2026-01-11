package T2601;

import java.util.Stack;

/**
 * @Description: 单调栈
 * @Author: iniwym
 * @Date: 2026-01-11
 * @Link: https://leetcode.cn/problems/maximal-rectangle/
 */
public class Code0085_MaximalRectangle {

    /**
     * 在二进制矩阵中找到只包含'1'的最大矩形面积
     *
     * @param matrix 输入的二维字符数组，包含'0'和'1'
     * @return 最大矩形的面积
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];  // 存储当前行的高度数组
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            // 更新高度数组：如果当前位置是'1'，则高度加1；否则重置为0
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }

            // 计算当前高度数组的最大矩形面积
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }


    /**
     * 计算直方图中最大矩形面积
     * 使用单调栈算法，通过维护一个递增的栈来快速找到每个高度对应的最宽矩形
     *
     * @param heights 直方图各柱子的高度数组
     * @return 最大矩形面积
     */
    private int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // 遍历每个柱子
        for (int i = 0; i <= n; i++) {
            // 当前柱子的高度（i == n 时高度为 0，作为哨兵）
            int currentHeight = (i == n) ? 0 : heights[i];

            // 当当前柱子高度小于栈顶柱子高度时
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                // 弹出栈顶柱子，计算以该柱子为高的矩形面积
                int height = heights[stack.pop()];
                // 宽度 = 右边界(i) - 左边界 - 1
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            // 将当前柱子索引入栈
            stack.push(i);
        }

        return maxArea;
    }


    /**
     * 计算矩阵中由'1'组成的最大矩形面积
     *
     * @param matrix 输入的字符二维数组，包含'0'和'1'
     * @return 最大矩形面积
     */
    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols + 1];  // 多一个位置作为哨兵
        heights[cols] = 0;  // 哨兵位置设为 0
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            // 更新每行对应的高度数组
            for (int j = 0; j < cols; j++) {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }

            // 使用单调栈计算当前行的最大矩形面积
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j <= cols; j++) {
                while (!stack.isEmpty() && heights[j] < heights[stack.peek()]) {
                    int height = heights[stack.pop()];
                    int width = stack.isEmpty() ? j : j - stack.peek() - 1;
                    maxArea = Math.max(maxArea, height * width);
                }
                stack.push(j);
            }
        }

        return maxArea;
    }

}
