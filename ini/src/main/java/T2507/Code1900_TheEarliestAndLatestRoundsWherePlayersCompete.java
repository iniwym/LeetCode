package T2507;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-07-12
 * @Link: https://leetcode.cn/problems/the-earliest-and-latest-rounds-where-players-compete/
 */
public class Code1900_TheEarliestAndLatestRoundsWherePlayersCompete {

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int[][][][] memo = new int[n + 1][n + 1][n + 1][2];
        return dfs(n, firstPlayer, secondPlayer, memo);
    }

    /**
     * 深度优先搜索计算两个人A和B在特定排列中相遇所需的最早和最晚回合数
     *
     * @param n      当前回合的总人数
     * @param first  A的位置（1-based）
     * @param second B的位置（1-based）
     * @param memo   记忆化数组，用于存储已经计算过的结果
     * @return 包含两个元素的数组：[最早相遇回合数, 最晚相遇回合数]
     */
    private int[] dfs(int n, int first, int second, int[][][][] memo) {
        // 如果A和B已经相邻，则下一回合就会相遇
        if (first + second == n + 1) {
            return new int[]{1, 1};
        }

        // 确保A在左半部分，B在右半部分，以简化后续计算
        if (first + second > n + 1) {
            int tmp = first;
            first = n + 1 - second;
            second = n + 1 - tmp;
        }

        // 检查是否已经计算过当前状态
        int[] mem = memo[n][first][second];
        if (mem[0] > 0) {
            return mem;
        }

        // 计算下一回合的人数
        int m = (n + 1) / 2;
        // 计算AB之间可能保留的人数范围
        int minMid = second <= m ? 0 : second - n / 2 - 1;
        int maxMid = second <= m ? second - first : m - first;
        int earliest = Integer.MAX_VALUE;
        int latest = 0;

        // 枚举所有可能的保留方案
        for (int left = 0; left < first; left++) {
            for (int mid = minMid; mid < maxMid; mid++) {
                // 递归计算子问题的结果
                int[] res = dfs(m, left + 1, left + mid + 2, memo);
                earliest = Math.min(earliest, res[0]);
                latest = Math.max(latest, res[1]);
            }
        }

        // 将当前回合的结果存入记忆化数组并返回
        mem[0] = earliest + 1;
        mem[1] = latest + 1;
        return mem;
    }

}
