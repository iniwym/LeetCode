package T2602;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 枚举右维护左
 * @Author: iniwym
 * @Date: 2026-02-13
 * @Link: https://leetcode.cn/problems/longest-balanced-substring-ii/
 */
public class Code3714_LongestBalancedSubstringIi {

    /**
     * 计算字符串中最长的平衡子串的长度。
     * 平衡子串定义为：子串中每种字符的数量相等。
     *
     * @param S 输入字符串，仅包含小写字母 'a'、'b'、'c'
     * @return 返回最长平衡子串的长度
     */
    public int longestBalanced(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int ans = 0;

        // 处理只有一种字母的情况：统计连续相同字符的最大长度
        for (int i = 0; i < n; ) {
            int start = i;
            for (i++; i < n && s[i] == s[i - 1]; i++) ;
            ans = Math.max(ans, i - start);
        }

        // 处理有两种字母的情况：分别检查 'a' 和 'b'、'a' 和 'c'、'b' 和 'c' 的组合
        ans = Math.max(ans, f(s, 'a', 'b'));
        ans = Math.max(ans, f(s, 'a', 'c'));
        ans = Math.max(ans, f(s, 'b', 'c'));

        // 处理有三种字母的情况：
        // 使用前缀和与哈希表记录状态，通过差值压缩技术优化存储
        // 将 (x, y) 压缩为一个 long 类型值，便于存入哈希表
        // 公式：(x + n) << 20 | (y + n)，其中 +n 避免负数问题
        Map<Long, Integer> pos = new HashMap<>();
        pos.put((long) n << 20 | n, -1); // 初始状态：前缀和为 0，位置设为 -1
        int[] cnt = new int[3]; // 统计 'a'、'b'、'c' 的数量
        for (int i = 0; i < n; i++) {
            cnt[s[i] - 'a']++; // 更新当前字符的计数
            long p = (long) (cnt[0] - cnt[1] + n) << 20 | (cnt[1] - cnt[2] + n); // 计算压缩后的状态值
            if (pos.containsKey(p)) {
                // 如果该状态已存在，则更新最大长度
                ans = Math.max(ans, i - pos.get(p));
            } else {
                // 否则将该状态及其位置存入哈希表
                pos.put(p, i);
            }
        }
        return ans;
    }

    /**
     * 计算字符串中由字符 x 和 y 组成的子串的最大长度，
     * 其中该子串满足：x 的出现次数与 y 的出现次数之差为某个固定值。
     *
     * @param s 输入的字符数组
     * @param x 目标字符之一
     * @param y 目标字符之二
     * @return 满足条件的最长子串的长度
     */
    private int f(char[] s, char x, char y) {
        int n = s.length;
        int ans = 0;

        // 遍历字符数组，寻找由 x 和 y 构成的连续子串
        for (int i = 0; i < n; i++) {
            // 使用哈希表记录前缀差值第一次出现的位置
            Map<Integer, Integer> pos = new HashMap<>();
            pos.put(0, i - 1); // 初始差值为 0，位置设为 i-1

            int d = 0; // 当前 x 的个数减去 y 的个数的差值

            // 找到以当前 i 开始的、仅包含 x 和 y 的最长连续子串
            for (; i < n && (s[i] == x || s[i] == y); i++) {
                // 更新差值 d
                d += s[i] == x ? 1 : -1;

                // 如果当前差值已存在，则计算子串长度并更新最大值
                if (pos.containsKey(d)) {
                    ans = Math.max(ans, i - pos.get(d));
                } else {
                    // 否则记录当前差值首次出现的位置
                    pos.put(d, i);
                }
            }
        }

        return ans;
    }

}
