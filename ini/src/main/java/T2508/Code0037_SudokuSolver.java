package T2508;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description: 数独
 * @Author: iniwym
 * @Date: 2025-08-31
 * @Link: https://leetcode.cn/problems/sudoku-solver/
 */
public class Code0037_SudokuSolver {

    public void solveSudoku(char[][] board) {
        boolean[][] rowHas = new boolean[9][9]; // rowHas[i][x] 表示 i 行是否有数字 x
        boolean[][] colHas = new boolean[9][9]; // colHas[j][x] 表示 j 列是否有数字 x
        boolean[][][] subBoxHas = new boolean[3][3][9]; // subBoxHas[i'][j'][x] 表示 (i',j') 宫是否有数字 x
        List<int[]> emptyPos = new ArrayList<>(); // 空格子的位置

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    emptyPos.add(new int[]{i, j}); // 记录空格子的位置
                } else {
                    int x = board[i][j] - '1'; // 字符 '1'~'9' 转成数字 0~8
                    // 标记行、列、宫包含数字 x
                    rowHas[i][x] = colHas[j][x] = subBoxHas[i / 3][j / 3][x] = true;
                }
            }
        }

        PriorityQueue<int[]> emptyPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] pos : emptyPos) {
            int i = pos[0];
            int j = pos[1];
            int candidates = getCandidates(i, j, rowHas, colHas, subBoxHas);
            emptyPQ.offer(new int[]{candidates, i, j}); // 待定数字个数最少的在堆顶
        }

        dfs(board, rowHas, colHas, subBoxHas, emptyPQ);
    }

    // 计算 (i, j) 这个空格子的待定数字个数
    private int getCandidates(int i, int j, boolean[][] rowHas, boolean[][] colHas, boolean[][][] subBoxHas) {
        int candidates = 9;
        for (int x = 0; x < 9; x++) {
            if (rowHas[i][x] || colHas[j][x] || subBoxHas[i / 3][j / 3][x]) {
                candidates--;
            }
        }
        return candidates;
    }

    // 每次递归，选一个空格子，枚举填入的数字
    private boolean dfs(char[][] board, boolean[][] rowHas, boolean[][] colHas, boolean[][][] subBoxHas, PriorityQueue<int[]> emptyPQ) {
        if (emptyPQ.isEmpty()) { // 所有格子都已填入数字
            return true; // 完成数独
        }

        // 数独玩法：优先考虑待定数字个数最少的空格子
        int[] top = emptyPQ.poll();
        int i = top[1];
        int j = top[2];

        int candidates = 0; // 受之前填入的数字影响，实际待定数字个数可能比入堆时的少，需要重新计算
        // 枚举没填过的数字 x，填入 board[i][j]
        for (int x = 0; x < 9; x++) {
            if (rowHas[i][x] || colHas[j][x] || subBoxHas[i / 3][j / 3][x]) {
                continue; // x 填过了
            }

            // 填入 board[i][j]
            board[i][j] = (char) ('1' + x);
            // 标记行、列、宫包含数字 x
            rowHas[i][x] = colHas[j][x] = subBoxHas[i / 3][j / 3][x] = true;

            // 填下一个空格子
            if (dfs(board, rowHas, colHas, subBoxHas, emptyPQ)) {
                return true; // 完成数独
            }

            // 恢复现场（撤销）
            // 注意 board[i][j] 无需恢复现场，因为我们会直接覆盖掉之前填入的数字
            rowHas[i][x] = colHas[j][x] = subBoxHas[i / 3][j / 3][x] = false;

            // 统计待定数字个数
            candidates++;
        }

        // 恢复现场（撤销）
        emptyPQ.offer(new int[]{candidates, i, j}); // 重新入堆（更新待定数字个数）
        // 所有填法都不行，说明之前（祖先节点）的填法是错的
        return false;
    }

}
