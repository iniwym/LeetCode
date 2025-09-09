package T2509;

/**
 * @Description: 差分数组
 * @Author: iniwym
 * @Date: 2025-09-09
 * @Link: https://leetcode.cn/problems/number-of-people-aware-of-a-secret/
 */
public class Code2327_NumberOfPeopleAwareOfASecret {

    /**
     * 计算第 n 天结束时知道秘密的人数
     * <p>
     * 问题描述：在第 1 天，有一个人知道秘密。每个人会在发现秘密后的 delay 天开始分享秘密，
     * 并且在发现秘密后的 forget 天后忘记秘密。只有在还没忘记秘密且已经过了延迟天数的情况下
     * 才能分享秘密。每天可以分享给一个人。
     *
     * @param n      第 n 天
     * @param delay  延迟分享天数，发现秘密后需要等待 delay 天才能开始分享
     * @param forget 忘记天数，发现秘密后经过 forget 天会忘记秘密
     * @return 第 n 天结束时知道秘密的人数（对 1000000007 取模）
     */
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = 1_000_000_007;
        // known[i] 表示恰好在第 i 天得知秘密的人数
        int[] known = new int[n + 1];
        known[1] = 1;
        long ans = 0;

        for (int i = 1; i <= n; i++) {
            // 统计在第 n 天仍然记得秘密的人数
            // 这些人是那些在第 (n-forget+1) 天或之后得知秘密的人
            if (i >= n - forget + 1) {
                ans += known[i];
            }
            // 当前知道秘密的人会在其能够分享秘密的时间段内每天分享给一个人
            // 分享时间段为 [第 i+delay 天, 第 i+forget-1 天]
            for (int j = i + delay; j <= Math.min(i + forget - 1, n); j++) {
                known[j] = (known[j] + known[i]) % MOD; // known[j] += known[i]
            }
        }

        return (int) (ans % MOD);
    }

}
