package T2601;

import java.util.Arrays;

/**
 * @Description: 字典树+Floyd+记忆化搜索
 * @Author: iniwym
 * @Date: 2026-01-30
 * @Link: https://leetcode.cn/problems/minimum-cost-to-convert-string-ii/
 */
public class Code2977_MinimumCostToConvertStringIi {

    class Node {
        Node[] son = new Node[26];
        int sid = -1; // 字符串的编号
    }

    private Node root = new Node();
    private int sid = 0;
    private char[] s, t;
    private int[][] dis;
    private long[] memo;

    /**
     * 计算将字符串 source 转换为字符串 target 所需的最小成本。
     *
     * @param source   原始字符串
     * @param target   目标字符串
     * @param original 表示可以进行转换的原始字符数组
     * @param changed  表示可以转换到的目标字符数组
     * @param cost     表示每次转换所需的成本数组
     * @return 返回转换所需的最小成本，如果无法完成转换则返回 -1
     */
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        // 初始化距离矩阵
        int m = cost.length;
        dis = new int[m * 2][m * 2];
        for (int i = 0; i < dis.length; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE / 2);
            dis[i][i] = 0;
        }
        for (int i = 0; i < cost.length; i++) {
            int x = put(original[i]);
            int y = put(changed[i]);
            dis[x][y] = Math.min(dis[x][y], cost[i]);
        }

        // 使用 Floyd 算法计算任意两点之间的最短路径
        for (int k = 0; k < sid; k++) {
            for (int i = 0; i < sid; i++) {
                if (dis[i][k] == Integer.MAX_VALUE / 2) {
                    continue;
                }
                for (int j = 0; j < sid; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

        s = source.toCharArray();
        t = target.toCharArray();
        memo = new long[s.length];
        Arrays.fill(memo, -1);
        long ans = dfs(0);
        return ans < Long.MAX_VALUE / 2 ? ans : -1;
    }

    /**
     * 将字符串插入到字典树中，并返回该字符串对应的唯一标识符。
     *
     * @param s 要插入的字符串
     * @return 返回该字符串在字典树中的唯一标识符（sid）
     */
    private int put(String s) {
        // 从根节点开始遍历
        Node o = root;

        // 遍历字符串中的每个字符，构建或查找对应的子节点
        for (char b : s.toCharArray()) {
            int i = b - 'a'; // 计算字符相对于 'a' 的偏移量，作为子节点数组的索引
            if (o.son[i] == null) {
                // 如果当前字符对应的子节点不存在，则创建新节点
                o.son[i] = new Node();
            }
            // 移动到下一个节点
            o = o.son[i];
        }

        // 如果当前节点尚未分配唯一标识符，则为其分配一个新的 sid
        if (o.sid < 0) {
            o.sid = sid++;
        }

        // 返回该字符串对应的唯一标识符
        return o.sid;
    }


    /**
     * 使用深度优先搜索（DFS）计算从索引 i 开始到字符串末尾的最小修改代价。
     *
     * @param i 当前处理的起始索引
     * @return 从索引 i 到字符串末尾的最小修改代价
     */
    private long dfs(int i) {
        // 如果当前索引超出字符串长度，返回 0 表示无需修改
        if (i >= s.length) {
            return 0;
        }

        // 如果该位置已经计算过，直接返回记忆化的结果
        if (memo[i] != -1) {
            return memo[i];
        }

        // 初始化结果为一个较大的值，用于后续比较
        long res = Long.MAX_VALUE / 2;

        // 如果当前字符相同，则不需要修改，继续处理下一个字符
        if (s[i] == t[i]) {
            res = dfs(i + 1);
        }

        // 定义两个节点指针，分别遍历源字符串和目标字符串的字典树
        Node p = root, q = root;

        // 遍历从当前位置 i 开始的所有可能子串
        for (int j = i; j < s.length; j++) {
            // 在字典树中移动指针
            p = p.son[s[j] - 'a'];
            q = q.son[t[j] - 'a'];

            // 如果其中一个节点为空，说明无法继续匹配，跳出循环
            if (p == null || q == null) {
                break;
            }

            // 如果当前节点不是有效节点（sid < 0），跳过此次循环
            if (p.sid < 0 || q.sid < 0) {
                continue;
            }

            // 获取从当前子串起点 i 到终点 j 的修改代价
            int d = dis[p.sid][q.sid];

            // 如果代价有效，则更新最小代价
            if (d < Integer.MAX_VALUE / 2) {
                res = Math.min(res, d + dfs(j + 1));
            }
        }

        // 将当前索引的结果存入记忆化数组并返回
        return memo[i] = res;
    }

}
