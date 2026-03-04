package T2603;

/**
 * @Description: 矩阵模拟
 * @Author: iniwym
 * @Date: 2026-03-04
 * @Link: https://leetcode.cn/problems/special-positions-in-a-binary-matrix/
 */
public class Code1582_SpecialPositionsInABinaryMatrix {

    /**
     * 计算二进制矩阵中特殊位置的数量
     * 特殊位置定义：该位置为 1，且其所在行和列的其他元素都为 0
     *
     * @param mat 输入的二进制矩阵，只包含 0 和 1
     * @return 特殊位置的数量
     */
    public int numSpecial(int[][] mat) {
        int ans = 0;
        next:
        for (int[] row : mat) {
            // 遍历每一行，查找该行是否只有一个 1
            int col = -1;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 0) {
                    continue;
                }
                if (col >= 0) {
                    continue next;
                }
                col = j;
            }
            if (col < 0) {
                continue;
            }

            // 检查该 1 所在的列是否也只有一个 1
            boolean seen1 = false;
            for (int[] r : mat) {
                if (r[col] == 0) {
                    continue;
                }
                if (seen1) {
                    continue next;
                }
                seen1 = true;
            }
            ans++;
        }
        return ans;
    }


}
