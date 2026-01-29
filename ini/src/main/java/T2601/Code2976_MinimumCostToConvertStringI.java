package T2601;

import java.util.Arrays;

/**
 * @Description: Floyd-Warshall算法
 * @Author: iniwym
 * @Date: 2026-01-29
 * @Link: https://leetcode.cn/problems/minimum-cost-to-convert-string-i/
 */
public class Code2976_MinimumCostToConvertStringI {

    /**
     * 计算将源字符串转换为目标字符串的最小成本
     * 使用Floyd-Warshall算法预计算所有字符间的最短转换路径成本
     *
     * @param source   源字符串，需要被转换的字符串
     * @param target   目标字符串，期望转换到的字符串
     * @param original 字符转换的起始字符数组，original[i]可以转换为changed[i]
     * @param changed  字符转换的目标字符数组，original[i]可以转换为changed[i]
     * @param cost     对应转换的成本数组，从original[i]转换到changed[i]的成本为cost[i]
     * @return 返回完成转换所需的最小总成本，如果无法完成转换则返回-1
     */
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        final int INF = Integer.MAX_VALUE / 2;

        // 初始化距离矩阵，dis[i][j]表示从字符i到字符j的最短转换成本
        int[][] dis = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dis[i], INF);
            dis[i][i] = 0;
        }

        // 根据给定的转换规则和成本构建初始距离矩阵
        for (int i = 0; i < cost.length; i++) {
            int x = original[i] - 'a';
            int y = changed[i] - 'a';
            dis[x][y] = Math.min(dis[x][y], cost[i]);
        }

        // Floyd-Warshall算法计算所有字符对之间的最短转换路径
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (dis[i][k] == INF) {
                    continue; // 巨大优化！
                }
                for (int j = 0; j < 26; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

        // 计算总的转换成本
        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            int d = dis[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
            if (d == INF) {
                return -1;
            }
            ans += d;
        }
        return ans;
    }

}

