package T2506;

/**
 * @Description: 枚举+前缀和+滑动窗口
 * @Author: iniwym
 * @Date: 2025-06-11
 * @Link: https://leetcode.cn/problems/maximum-difference-between-even-and-odd-frequency-ii/
 */
public class Code3445_MaximumDifferenceBetweenEvenAndOddFrequencyIi {
    /**
     * 计算字符串S中长度为k的子串的最大差值
     * 差值定义为子串中字符x出现的次数减去字符y出现的次数
     * 该方法通过枚举字符x和y来找到最大差值
     *
     * @param S 字符串，表示待分析的字符串
     * @param k 整数，表示子串的长度
     * @return 返回最大差值，如果不存在这样的子串，则返回-INF
     */
    public int maxDifference(String S, int k) {
        // 定义无穷大的值，用于初始化和比较操作
        final int INF = Integer.MAX_VALUE / 2;
        // 将字符串转换为字符数组，便于后续处理
        char[] s = S.toCharArray();
        // 初始化最大差值为负无穷大
        int ans = -INF;
        // 枚举字符x，范围是0到4，对应字符'0'到'4'
        for (int x = 0; x < 5; x++) {
            // 枚举字符y，范围是0到4，且y不能等于x
            for (int y = 0; y < 5; y++) {
                if (y == x) {
                    continue;
                }
                // 初始化当前字符计数数组
                int[] curS = new int[5];
                // 初始化前一个字符计数数组
                int[] preS = new int[5];
                // 初始化最小差值数组，用于记录不同奇偶性组合下的最小差值
                int[][] minS = {{INF, INF}, {INF, INF}};
                // 初始化左指针
                int left = 0;
                // 遍历字符数组
                for (int i = 0; i < s.length; i++) {
                    // 计数当前字符
                    curS[s[i] - '0']++;
                    // 右指针
                    int r = i + 1;
                    // 当子串长度大于等于k且当前计数数组中字符x和y的出现次数都大于前一个计数数组时，更新最小差值
                    while (r - left >= k && curS[x] > preS[x] && curS[y] > preS[y]) {
                        // p和q分别表示前一个计数数组中字符x和y出现次数的奇偶性
                        int p = preS[x] & 1;
                        int q = preS[y] & 1;
                        // 更新最小差值
                        minS[p][q] = Math.min(minS[p][q], preS[x] - preS[y]);
                        // 更新前一个计数数组
                        preS[s[left] - '0']++;
                        // 左指针右移
                        left++;
                    }
                    // 更新最大差值
                    ans = Math.max(ans, curS[x] - curS[y] - minS[curS[x] & 1 ^ 1][curS[y] & 1]);
                }
            }
        }
        // 返回最大差值
        return ans;
    }

}
