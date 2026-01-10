package T2601;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2026-01-10
 * @Link: https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings/
 */
public class Code0712_MinimumAsciiDeleteSumForTwoStrings {

    /**
     * 计算使两个字符串相等所需删除字符的ASCII值的最小和
     * 通过动态规划找到最长公共子序列的ASCII值总和，然后用总ASCII值减去两倍的公共部分得到结果
     *
     * @param s1 第一个字符串
     * @param s2 第二个字符串
     * @return 删除字符使两字符串相等所需的最小ASCII值和
     */
    public int minimumDeleteSum(String s1, String s2) {
        // 计算两个字符串所有字符的ASCII值总和
        int total = s1.chars().sum() + s2.chars().sum();

        char[] s = s1.toCharArray();
        char[] t = s2.toCharArray();
        int n = s.length;
        int m = t.length;

        // 动态规划数组：f[i][j] 表示s1前i个字符和s2前j个字符的最长公共子序列的ASCII值总和
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s[i] == t[j]) {
                    f[i + 1][j + 1] = f[i][j] + s[i];
                } else {
                    f[i + 1][j + 1] = Math.max(f[i][j + 1], f[i + 1][j]);
                }
            }
        }
        // 总ASCII值减去两倍的最长公共子序列ASCII值，即为需要删除的字符ASCII值总和
        return total - f[n][m] * 2;
    }

}
