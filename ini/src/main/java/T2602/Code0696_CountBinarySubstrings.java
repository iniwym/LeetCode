package T2602;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-19
 * @Link: https://leetcode.cn/problems/count-binary-substrings/
 */
public class Code0696_CountBinarySubstrings {

    /**
     * 计算字符串中满足条件的二进制子串数量。
     * 条件：子串由连续的0和1组成，且0和1的数量相等，并且所有0和所有1都是连续的。
     *
     * @param S 输入的二进制字符串
     * @return 满足条件的二进制子串的数量
     */
    public int countBinarySubstrings(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int pre = 0; // 记录前一组连续字符的数量
        int cur = 0; // 记录当前组连续字符的数量
        int ans = 0; // 存储满足条件的子串总数

        for (int i = 0; i < n; i++) {
            cur++; // 当前字符数量加一

            // 如果遍历到字符串末尾，或者当前字符与下一个字符不同，则说明到达了当前组的末尾
            if (i == n - 1 || s[i] != s[i + 1]) {
                // 将当前组和前一组的最小值累加到结果中
                ans += Math.min(pre, cur);
                // 更新前一组为当前组，重置当前组
                pre = cur;
                cur = 0;
            }
        }

        return ans; // 返回满足条件的子串总数
    }

}
