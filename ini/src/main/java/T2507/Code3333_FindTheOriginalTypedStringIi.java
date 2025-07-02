package T2507;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 多重背包
 * @Author: iniwym
 * @Date: 2025-07-02
 * @Link: https://leetcode.cn/problems/find-the-original-typed-string-ii/
 */
public class Code3333_FindTheOriginalTypedStringIi {
    /**
     * 计算满足特定条件的字符串组合数量
     *
     * @param word 输入字符串，包含需要处理的字符序列
     * @param k    需要选择的连续字符组数限制
     * @return 返回满足条件的字符串组合数量，结果对1_000_000_007取模
     */
    public int possibleStringCount(String word, int k) {
        int n = word.length();
        // 字符串长度不足时直接返回0
        if (n < k) {
            return 0;
        }

        final int MOD = 1_000_000_007;
        List<Integer> cnts = new ArrayList<>();
        long ans = 1;
        int cnt = 0;

        // 遍历字符串，统计连续相同字符的组数
        for (int i = 0; i < n; i++) {
            cnt++;
            // 遇到字符变化或到达字符串末尾时处理当前连续组
            if (i == n - 1 || word.charAt(i) != word.charAt(i + 1)) {
                // 只处理长度大于1的连续组
                if (cnt > 1) {
                    if (k > 0) {
                        cnts.add(cnt - 1);
                    }
                    ans = ans * cnt % MOD;
                }
                k--; // 每处理完一组就减少k值
                cnt = 0;
            }
        }

        // 如果k已经用完，直接返回当前计算结果
        if (k <= 0) {
            return (int) ans;
        }

        // 动态规划处理剩余情况
        int m = cnts.size();
        int[][] f = new int[m + 1][k];
        Arrays.fill(f[0], 1);
        int[] s = new int[k + 1];

        // 计算前缀和数组和动态规划转移
        for (int i = 0; i < m; i++) {
            // 计算前缀和数组
            for (int j = 0; j < k; j++) {
                s[j + 1] = (s[j] + f[i][j]) % MOD;
            }
            int c = cnts.get(i);
            // 通过前缀和数组计算当前状态
            for (int j = 0; j < k; j++) {
                f[i + 1][j] = (s[j + 1] - s[Math.max(j - c, 0)]) % MOD;
            }
        }

        // 最终结果需要减去不满足条件的组合数，并保证非负
        return (int) ((ans - f[m][k - 1] + MOD) % MOD);
    }

}
