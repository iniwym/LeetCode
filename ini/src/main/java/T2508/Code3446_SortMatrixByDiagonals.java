package T2508;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 对角线
 * @Author: iniwym
 * @Date: 2025-08-28
 * @Link: https://leetcode.cn/problems/sort-matrix-by-diagonals/
 */
public class Code3446_SortMatrixByDiagonals {

    /**
     * 对矩阵进行排序处理
     * 该函数将矩阵中的元素按照对角线方向进行分组排序：
     * 1. 对于每个从上到下的对角线（左上到右下），将元素按降序排列
     * 2. 对于每个从左到右的对角线（左上到右下），将元素按升序排列
     *
     * @param grid 输入的二维整数矩阵
     * @return 排序后的二维整数矩阵
     */
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        // 处理从上到下的对角线（左上到右下），按降序排序
        for (int i = 0; i < n; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; i + j < n; j++) {
                tmp.add(grid[i + j][j]);
            }
            tmp.sort(Collections.reverseOrder());
            for (int j = 0; i + j < n; j++) {
                grid[i + j][j] = tmp.get(j);
            }
        }

        // 处理从左到右的对角线（左上到右下），按升序排序
        for (int j = 1; j < n; j++) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; j + i < n; i++) {
                tmp.add(grid[i][j + i]);
            }
            Collections.sort(tmp);
            for (int i = 0; j + i < n; i++) {
                grid[i][j + i] = tmp.get(i);
            }
        }

        return grid;
    }


}

