package T2602;

import java.util.Arrays;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2026-02-12
 * @Link: https://leetcode.cn/problems/longest-balanced-substring-i/
 */
public class Code3713_LongestBalancedSubstringI {

    /**
     * 计算字符串中最长的“平衡子串”的长度。
     * 平衡子串定义为：子串中所有出现过的字符的出现次数都相同。
     *
     * @param s 输入字符串，仅包含小写字母
     * @return 返回最长平衡子串的长度
     */
    public int longestBalanced(String s) {
        int n = s.length();
        int res = 0;
        int[] cnt = new int[26];

        // 外层循环遍历所有可能的起始位置
        for (int i = 0; i < n; i++) {
            Arrays.fill(cnt, 0); // 初始化字符计数数组

            // 内层循环扩展子串的结束位置
            for (int j = i; j < n; j++) {
                boolean flag = true;
                int c = s.charAt(j) - 'a'; // 将字符转换为索引
                cnt[c]++; // 更新当前字符的计数

                // 检查当前子串是否满足平衡条件
                for (int x : cnt) {
                    if (x > 0 && x != cnt[c]) {
                        flag = false;
                        break;
                    }
                }

                // 如果满足平衡条件，更新最大长度
                if (flag) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

}
