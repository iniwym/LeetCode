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
 * @Link: https://leetcode.cn/problems/tJau2o/
 */
public class CodeByte006_BuyGoodsHaveDiscount {
    public static int MAXN = 501;

    public static int MAXX = 100001;

    // 对于"一定要买的商品"，直接买！
    // 只把"需要考虑的商品"放入cost、val数组
    public static int[] cost = new int[MAXN];

    public static long[] val = new long[MAXN];

    public static long[] dp = new long[MAXX];

    public static int n, m, x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            m = 1;
            in.nextToken();
            x = (int) in.nval;
            long ans = 0;
            long happy = 0;
            for (int i = 1, pre, cur, well; i <= n; i++) {
                // 原价
                in.nextToken();
                pre = (int) in.nval;
                // 现价
                in.nextToken();
                cur = (int) in.nval;
                // 快乐值
                in.nextToken();
                happy = (long) in.nval;
                well = pre - cur - cur;
                // 如下是一件"一定要买的商品"
                // 预算 = 100，商品原价 = 10，打折后 = 3
                // 那么好处(well) = (10 - 3) - 3 = 4
                // 所以，可以认为这件商品把预算增加到了104！一定要买！
                // 如下是一件"需要考虑的商品"
                // 预算 = 104，商品原价 = 10，打折后 = 8
                // 那么好处(well) = (10 - 8) - 8 = -6
                // 所以，可以认为这件商品就花掉6元！
                // 也就是说以后花的不是打折后的值，是"坏处"
                if (well >= 0) {
                    x += well;
                    ans += happy;
                } else {
                    cost[m] = -well;
                    val[m++] = happy;
                }
            }
            ans += compute();
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static long compute() {
        Arrays.fill(dp, 0, x + 1, 0);
        for (int i = 1; i <= m; i++) {
            for (int j = x; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
            }
        }
        return dp[x];
    }

}