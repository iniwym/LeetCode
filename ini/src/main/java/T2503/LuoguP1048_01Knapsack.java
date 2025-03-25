package T2503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * @Description: 01背包
 * @Author: iniwym
 * @Date: 2025-03-24
 * @Link: https://www.luogu.com.cn/problem/P1048
 */
public class LuoguP1048_01Knapsack {

    public static int MAXM = 101;
    public static int MAXT = 1001;

    public static int t, n;
    public static int[] cost = new int[MAXM];
    public static int[] val = new int[MAXM];
    public static int[] dp = new int[MAXT];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            t = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                cost[i] = (int) in.nval;
                in.nextToken();
                val[i] = (int) in.nval;
            }
            out.println(compute1());
        }
        out.flush();
        out.close();
        br.close();
    }

    /**
     * 针对每一个价值，都尝试选择要或选择不要，然后求最大值
     * n是物品数量，t是背包容量
     * dp[i][j]代表前i个物品，总花费不超过j
     */
    private static int compute1() {
        int[][] dp = new int[n + 1][t + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= t; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= cost[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + val[i]);
                }
            }

        }
        return dp[n][t];
    }

    /**
     * 对于任意位置的dp[i][j]，依赖dp[i-1][j]和dp[i-1][j-cost[i]]
     * 每一行从右往左进行遍历
     */
    private static int compute2() {
        Arrays.fill(dp, 0, t + 1, 0);
        for (int i = 1; i <= n; i++) {
            for (int j = t; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
            }
        }
        return dp[t];
    }

}
