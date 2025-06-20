package T2506;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-06-20
 * @Link: https://leetcode.cn/problems/maximum-manhattan-distance-after-k-changes/
 */
public class Code3443_MaximumManhattanDistanceAfterKChanges {

    private int left;

    /**
     * 计算字符串s中字符组合的最大距离
     *
     * @param s 输入的字符串，包含方向字符
     * @param k 用于计算最大距离的参数，具体用途未知
     * @return 返回计算出的最大距离
     */
    public int maxDistance(String s, int k) {
        int ans = 0;
        // 初始化计数数组，用于统计每个方向字符出现的次数
        int[] cnt = new int['X']; // 'W' + 1 = 'X'
        // 遍历字符串中的每个字符，统计字符出现次数，并计算最大距离
        for (char ch : s.toCharArray()) {
            cnt[ch]++;
            left = k;
            // 更新最大距离，考虑南北和东西两个方向的字符组合
            ans = Math.max(ans, f(cnt['N'], cnt['S']) + f(cnt['E'], cnt['W']));
        }
        return ans;
    }

    /**
     * 计算两个方向字符之间的最大距离
     *
     * @param a 第一个方向字符出现的次数
     * @param b 第二个方向字符出现的次数
     * @return 返回两个方向字符之间的最大距离
     */
    private int f(int a, int b) {
        // 计算可以用于增加距离的字符数量，考虑剩余的k值
        int d = Math.min(Math.min(a, b), left);
        left -= d;
        // 计算并返回两个方向字符之间的距离
        return Math.abs(a - b) + d * 2;
    }
}

