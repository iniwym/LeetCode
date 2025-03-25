package T2503;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 对角线标识：利用 r - c 快速分组。
 * @Author: iniwym
 * @Date: 2025-03-25
 * @Link: https://leetcode.cn/problems/difference-of-number-of-distinct-values-on-diagonals/
 */
public class Code2711_DifferenceOfNumberOfDistinctValuesOnDiagonals {
    /**
     * 计算网格中每个单元格所在对角线的左上方和右下方不同元素数量的绝对差。
     *
     * @param grid 输入的二维网格，每个元素代表单元格的值
     * @return 二维数组，其中每个元素表示对应位置所在对角线的差值结果
     */
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] answer = new int[m][n];
        Map<Integer, List<int[]>> diagonals = new HashMap<>();

        // 将所有单元格按对角线分组，使用r - c作为对角线标识
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // 计算对角线标识
                int key = r - c;
                diagonals.putIfAbsent(key, new ArrayList<>());
                // 存储单元格坐标
                diagonals.get(key).add(new int[]{r, c});
            }
        }

        // 处理每条对角线的单元格并计算结果
        for (List<int[]> cells : diagonals.values()) {
            int len = cells.size();
            if (len == 0) {
                continue;
            }

            // 计算左上部分的不同值数量
            int[] topLeft = new int[len];
            Set<Integer> uniqueLeft = new HashSet<>();
            for (int i = 0; i < len; i++) {
                // 当前单元格左上方的不同值数量
                topLeft[i] = uniqueLeft.size();
                int r = cells.get(i)[0];
                int c = cells.get(i)[1];
                // 将当前单元格加入集合
                uniqueLeft.add(grid[r][c]);
            }

            // 计算右下部分的不同值数量
            int[] bottomRight = new int[len];
            Set<Integer> uniqueRight = new HashSet<>();
            for (int i = len - 1; i >= 0; i--) {
                // 当前单元格右下方的不同值数量
                bottomRight[i] = uniqueRight.size();
                int r = cells.get(i)[0];
                int c = cells.get(i)[1];
                // 将当前单元格加入集合
                uniqueRight.add(grid[r][c]);
            }

            // 根据差值更新结果矩阵
            for (int i = 0; i < len; i++) {
                int r = cells.get(i)[0];
                int c = cells.get(i)[1];
                answer[r][c] = Math.abs(topLeft[i] - bottomRight[i]);
            }

        }
        return answer;

    }
}
