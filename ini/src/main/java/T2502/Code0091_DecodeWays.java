package T2502;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-02-24
 * @Link: https://leetcode.cn/problems/decode-ways/
 */
public class Code0091_DecodeWays {

    public static int numDecodings1(String s) {
        return f1(s.toCharArray(), 0);
    }

    private static int f1(char[] chars, int n) {
        if (n == chars.length) {
            return 1;
        }
        int ans;
        char c1 = chars[n];
        if (c1 == '0') {
            ans = 0;
        } else {
            ans = f1(chars, n + 1);
            if (n + 1 < chars.length) {
                char c2 = chars[n + 1];
                int num = (c1 - '0') * 10 + (c2 - '0');
                if (num <= 26 && num >= 10) {
                    ans += f1(chars, n + 2);
                }
            }
        }

        return ans;
    }

    /**
     * dp表
     */
    public static int numDecodings2(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        return f2(s.toCharArray(), 0, dp);
    }

    private static int f2(char[] chars, int n, int[] dp) {
        if (n == chars.length) {
            return 1;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int ans;
        char c1 = chars[n];
        if (c1 == '0') {
            ans = 0;
        } else {
            ans = f2(chars, n + 1, dp);
            if (n + 1 < chars.length) {
                char c2 = chars[n + 1];
                int num = (c1 - '0') * 10 + (c2 - '0');
                if (num <= 26 && num >= 10) {
                    ans += f2(chars, n + 2, dp);
                }
            }
        }

        dp[n] = ans;
        return ans;
    }

    /**
     * 自底向上
     */
    public static int numDecodings3(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        char[] chars = s.toCharArray();
        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            char c1 = chars[i];
            if (c1 == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if (i + 1 < chars.length) {
                    char c2 = chars[i + 1];
                    int num = (c1 - '0') * 10 + (c2 - '0');
                    if (num <= 26 && num >= 10) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }

    /**
     * 压缩空间
     */
    public static int numDecodings4(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int next = 1;
        int nextNext = 0;

        for (int i = n - 1; i >= 0; i--) {
            int cur;
            char c1 = chars[i];
            if (c1 == '0') {
                cur = 0;
            } else {
                cur = next;
                if (i + 1 < chars.length) {
                    char c2 = chars[i + 1];
                    int num = (c1 - '0') * 10 + (c2 - '0');
                    if (num <= 26 && num >= 10) {
                        cur += nextNext;
                    }
                }
            }
            nextNext = next;
            next = cur;
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "12";
        System.out.println(numDecodings4(s));
    }

}
