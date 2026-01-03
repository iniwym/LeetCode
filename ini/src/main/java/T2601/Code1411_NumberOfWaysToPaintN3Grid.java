package T2601;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 记忆化搜索
 * @Author: iniwym
 * @Date: 2026-01-03
 * @Link: https://leetcode.cn/problems/number-of-ways-to-paint-n-3-grid/
 */
public class Code1411_NumberOfWaysToPaintN3Grid {

    private static final int MOD = 1_000_000_007;

    // 全局 memo，记忆化的内容可以在不同测试数据间共享
    private static Map<Integer, Integer> memo = new HashMap<>();

    public int numOfWays(int n) {
        return dfs(n, 0, 0, 0);
    }

    // (i, j)：当前位置
    // preRow：上一行（i+1 行）的颜色
    // curRow：当前这一行已填入的颜色
    private int dfs(int i, int j, int preRow, int curRow) {
        if (i == 0) { // 所有格子都已涂色
            return 1; // 找到一个合法方案
        }

        if (j == 3) { // i 行已涂色
            // 开始对 i-1 行涂色，curRow 变成 preRow
            return dfs(i - 1, 0, curRow, 0);
        }

        // 参数压缩到一个 int 中
        int key = (i << 14) | (j << 12) | (preRow << 6) | curRow;
        if (memo.containsKey(key)) { // 之前计算过
            return memo.get(key);
        }

        int res = 0;
        for (int color = 0; color < 3; color++) { // 枚举 (i, j) 的颜色 color
            if (preRow > 0 && color == (preRow >> (j * 2) & 3) || // 不能和下面相邻格子 (i+1, j) 颜色相同
                    j > 0 && color == (curRow >> ((j - 1) * 2) & 3)) { // 不能和左侧相邻格子 (i, j-1) 颜色相同
                continue;
            }
            res = (res + dfs(i, j + 1, preRow, curRow | (color << (j * 2)))) % MOD;
        }

        memo.put(key, res); // 记忆化
        return res;
    }
}
