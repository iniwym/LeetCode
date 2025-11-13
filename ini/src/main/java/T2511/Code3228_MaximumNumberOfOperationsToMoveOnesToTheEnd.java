package T2511;

/**
 * @Description: 字符串
 * @Author: iniwym
 * @Date: 2025-11-13
 * @Link: https://leetcode.cn/problems/maximum-number-of-operations-to-move-ones-to-the-end/
 */
public class Code3228_MaximumNumberOfOperationsToMoveOnesToTheEnd {

    /**
     * 计算字符串中特定操作的最大次数
     * 该函数遍历字符串，统计字符'1'的连续段，并根据特定规则计算最大操作数
     *
     * @param S 输入的字符串，只包含字符'0'和'1'
     * @return 返回根据规则计算得到的最大操作次数
     */
    public int maxOperations(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        int cnt1 = 0;

        // 遍历字符数组，统计连续'1'的个数，并在遇到'0'且前一个字符为'1'时累加操作数
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '1') {
                cnt1++;
            } else if (i > 0 && s[i - 1] == '1') {
                ans += cnt1;
            }
        }
        return ans;
    }

}
