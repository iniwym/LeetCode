package T2502;

import java.util.Arrays;

/**
 * @Description: 动态规划
 * @Author: iniwym
 * @Date: 2025-02-24
 * @Link: https://leetcode.cn/problems/fibonacci-number/
 */
public class Code0509_FibonacciNumber {
    public int fib1(int n) {
        return f1(n);
    }

    public int f1(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return f1(n - 1) + f1(n - 2);
    }

    /**
     * dp表
     */
    public int fib2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return f2(n, dp);
    }

    public int f2(int i, int[] dp) {
        if (i == 0) {
            return 0;
        }

        if (i == 1) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans = f2(i - 1, dp) + f2(i - 2, dp);
        dp[i] = ans;
        return ans;
    }

    /**
     * 从底到顶
     */
    public int fib3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 有限变量
     */
    public int fib4(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prepre = 0;
        int pre = 1;
        for (int i = 2; i <= n; i++) {
            int ans = prepre + pre;
            prepre = pre;
            pre = ans;

        }
        return pre;
    }
}
