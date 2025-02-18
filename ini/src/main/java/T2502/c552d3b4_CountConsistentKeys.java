package T2502;

import java.util.Arrays;

/**
 * @Description: 前缀树
 * @Author: iniwym
 * @Date: 2025-02-11
 * @Link: https://www.nowcoder.com/practice/c552d3b4dfda49ccb883a6371d9a6932/
 */
public class c552d3b4_CountConsistentKeys {

    // 定义字典树的最大容量
    public static int MAX = 100000;
    // 字典树的节点数组
    public int[][] tree = new int[MAX][12];
    // 记录通过每个节点的密钥数量
    public int[] pass = new int[MAX];
    // 记录当前字典树中密钥的数量
    public int cnt;

    /**
     * 计算一致密钥的数量
     * 该方法通过构建字典树（Trie）来统计在数组b中与数组a中的密钥序列一致的数量
     *
     * @param b 一个二维数组，代表需要检查的密钥集合
     * @param a 一个二维数组，代表参考密钥集合
     * @return 返回一个整数数组，表示每个密钥在b中与a中一致的数量
     */
    public int[] countConsistentKeys(int[][] b, int[][] a) {
        // 构建字典树
        build();
        // 初始化字符串缓冲区，用于构建密钥字符串
        StringBuffer stringBuffer = new StringBuffer();
        // 处理a密钥
        for (int[] str : a) {
            stringBuffer.setLength(0);
            for (int i = 1; i < str.length; i++) {
                stringBuffer.append(str[i] - str[i - 1] + "#");
            }
            // 将构建的密钥字符串插入字典树
            insert(stringBuffer.toString());
        }

        int[] ans = new int[b.length];
        // 处理b密钥，并查询在a中出现的次数
        for (int i = 0; i < b.length; i++) {
            stringBuffer.setLength(0);
            for (int j = 1; j < b[i].length; j++) {
                stringBuffer.append(b[i][j] - b[i][j - 1] + "#");
            }
            // 查询并记录一致密钥的数量
            ans[i] = query(stringBuffer.toString());
        }

        // 清空字典树
        clear();

        return ans;
    }

    /**
     * 构建字典树，初始化计数器
     */
    private void build() {
        cnt = 1;
    }

    /**
     * 清空字典树，为下一次使用做准备
     */
    private void clear() {
        for (int i = 0; i < MAX; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
        }
    }

    /**
     * 将字符转换为字典树中对应的索引
     *
     * @param aChar 需要转换的字符
     * @return 字符对应的索引
     */
    private int path(char aChar) {
        if (aChar == '#') {
            return 10;
        } else if (aChar == '-') {
            return 11;
        } else {
            return aChar - '0';
        }
    }

    /**
     * 向字典树中插入表示密钥的字符串
     *
     * @param str 表示密钥的字符串
     */
    private void insert(String str) {
        int cur = 1;
        pass[cur]++;
        char[] chars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            int index = path(chars[i]);
            if (tree[cur][index] == 0) {
                tree[cur][index] = ++cnt;
            }
            cur = tree[cur][index];
            pass[cur]++;
        }
    }

    /**
     * 查询字典树中与给定字符串匹配的密钥数量
     *
     * @param str 表示密钥的字符串
     * @return 匹配的密钥数量
     */
    private int query(String str) {
        int cur = 1;
        for (int i = 0; i < str.length(); i++) {
            int index = path(str.charAt(i));
            if (tree[cur][index] == 0) {
                return 0;
            }
            cur = tree[cur][index];
        }
        return pass[cur];
    }


}
