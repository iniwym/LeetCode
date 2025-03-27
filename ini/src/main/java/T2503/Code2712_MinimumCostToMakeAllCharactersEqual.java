package T2503;

/**
 * @Description: 反转技巧/动态规划
 * @Author: iniwym
 * @Date: 2025-03-27
 * @Link: https://leetcode.cn/problems/minimum-cost-to-make-all-characters-equal/
 */
public class Code2712_MinimumCostToMakeAllCharactersEqual {
    /**
     * 计算字符串中相邻字符差异导致的最小成本。
     *
     * @param s 输入的字符串，用于计算成本
     * @return 根据字符差异规则计算得到的最小成本值
     */
    public long minimumCost(String s) {
        int n = s.length();
        long res = 0;
        for (int i = 1; i < n; i++) {
            // 遍历每个字符，当相邻字符不同时计算对应的成本贡献
            if (s.charAt(i) != s.charAt(i - 1)) {
                res += Math.min(i, n - i);
            }
        }
        return res;
    }


    /**
     * 计算将字符串分割为两个子串的最小成本。
     *
     * @param s 输入的二进制字符串
     * @return 分割后的最小成本值，若无法分割则返回对应结果
     */
    public long minimumCost2(String s) {
        int n = s.length();
        long[][] suf = new long[n + 1][2];
        // 初始化后缀数组，从后向前计算每个位置的后缀成本，基于字符类型选择不同的成本路径
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                suf[i][1] = suf[i + 1][1];
                suf[i][0] = suf[i + 1][1] + (n - i);
            } else {
                suf[i][1] = suf[i + 1][0] + (n - i);
                suf[i][0] = suf[i + 1][0];
            }
        }

        long[] pre = new long[2];
        long res = Long.MAX_VALUE;
        // 遍历字符串，计算前缀成本并结合后缀结果，更新最小总成本
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                pre[0] = pre[1] + i + 1;
            } else {
                pre[1] = pre[0] + i + 1;
            }
            res = Math.min(res, Math.min(pre[0] + suf[i + 1][0], pre[1] + suf[i + 1][1]));
        }
        return res;
    }


}
