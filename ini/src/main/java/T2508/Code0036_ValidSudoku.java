package T2508;

/**
 * @Description: 数独
 * @Author: iniwym
 * @Date: 2025-08-30
 * @Link: https://leetcode.cn/problems/valid-sudoku/
 */
public class Code0036_ValidSudoku {

    /**
     * 验证一个9x9的数独是否有效
     *
     * @param board 9x9的字符数组，代表数独棋盘，其中'.'表示空格，'1'-'9'表示数字
     * @return 如果数独符合规则则返回true，否则返回false
     * <p>
     * 规则：
     * 1. 数字1-9在每一行只能出现一次
     * 2. 数字1-9在每一列只能出现一次
     * 3. 数字1-9在每一个3x3的子九宫格中只能出现一次
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowHas = new boolean[9][9]; // rowHas[i][x] 表示 i 行是否有数字 x
        boolean[][] colHas = new boolean[9][9]; // colHas[j][x] 表示 j 列是否有数字 x
        boolean[][][] subBoxHas = new boolean[3][3][9]; // subBoxHas[i'][j'][x] 表示 (i',j') 宫是否有数字 x

        // 遍历整个数独棋盘，检查每个位置的数字是否符合规则
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char b = board[i][j];
                if (b == '.') {
                    continue;
                }
                int x = b - '1'; // 字符 '1'~'9' 转成数字 0~8
                // 检查当前数字在行、列、子九宫格中是否已经存在
                if (rowHas[i][x] || colHas[j][x] || subBoxHas[i / 3][j / 3][x]) { // 重复遇到数字 x
                    return false;
                }
                // 标记行、列、宫包含数字 x
                rowHas[i][x] = colHas[j][x] = subBoxHas[i / 3][j / 3][x] = true;
            }
        }

        return true;
    }


}
