package T2511;

/**
 * @Description: 枚举子串右端点
 * @Author: iniwym
 * @Date: 2025-11-16
 * @Link: https://leetcode.cn/problems/number-of-substrings-with-only-1s/
 */
public class Code1513_NumberOfSubstringsWithOnly1s {

    /**
     * 计算字符串中所有只包含字符'1'的子字符串的数量
     * 对于每个字符'1'，统计以该字符为结尾的连续'1'子字符串数量
     * 结果对1000000007取模
     *
     * @param s 输入的二进制字符串，仅包含字符'0'和'1'
     * @return 只包含字符'1'的子字符串的数量，对1000000007取模后的结果
     */
    public int numSub(String s) {
        final int MOD = 1_000_000_007;
        long ans = 0;
        int last0 = -1;
        // 遍历字符串，统计每个位置作为右端点时能形成的全1子串数量
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '0') {
                last0 = i;
            } else {
                // 当前字符为'1'时，从上一个'0'位置之后开始到当前位置i
                // 形成的子串都是合法的全1子串，数量为 i - last0
                ans += i - last0;
            }
        }
        return (int) (ans % MOD);
    }

}

