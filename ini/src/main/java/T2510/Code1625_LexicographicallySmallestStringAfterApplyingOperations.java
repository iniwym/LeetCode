package T2510;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-10-19
 * @Link: https://leetcode.cn/problems/lexicographically-smallest-string-after-applying-operations/
 */
public class Code1625_LexicographicallySmallestStringAfterApplyingOperations {

    /**
     * 找到通过特定操作能得到的字典序最小的字符串
     * 操作1：将所有奇数位置的字符加上a（模10）
     * 操作2：将字符串向右轮转b位
     *
     * @param S 输入的字符串，只包含数字字符
     * @param a 累加操作的增量值
     * @param b 轮转操作的位移值
     * @return 能够得到的字典序最小的字符串
     */
    public String findLexSmallestString(String S, int a, int b) {
        char[] s = S.toCharArray();
        int n = s.length;
        char[] t = new char[n];
        int step = gcd(b, n);
        int g = gcd(a, 10);
        String ans = null;

        // 枚举所有可能的轮转位置
        for (int i = 0; i < n; i += step) {
            // t = s[i,n) + s[0,i)
            System.arraycopy(s, i, t, 0, n - i);
            System.arraycopy(s, 0, t, n - i, i);

            modify(t, 1, g); // 累加操作（所有奇数下标）
            if (step % 2 > 0) { // 能对偶数下标执行累加操作
                modify(t, 0, g); // 累加操作（所有偶数下标）
            }

            String str = new String(t);
            if (ans == null || str.compareTo(ans) < 0) {
                ans = str;
            }
        }

        return ans;
    }

    /**
     * 修改字符数组中每隔一个位置的数字字符，使它们按照模g的规则减小到最小可能值
     *
     * @param t     待修改的字符数组，数组元素为数字字符('0'-'9')
     * @param start 起始修改位置的索引
     * @param g     用于计算模运算的基数
     */
    private void modify(char[] t, int start, int g) {
        int ch = t[start] - '0'; // 最靠前的数字，越小越好
        // ch 可以变成的最小值为 ch%g
        // 例如 ch=5，g=2，那么 ch+2+2+2（模 10）后变成 1，不可能变得更小
        // 从 ch 到 ch%g，需要增加 inc，其中 +10 保证 inc 非负（循环中会 %10 保证结果在 [0,9] 中）
        int inc = ch % g - ch + 10;
        // 从起始位置开始，每隔一个位置进行修改，将数字字符按照inc增量进行模10加法运算
        for (int j = start; j < t.length; j += 2) {
            t[j] = (char) ('0' + (t[j] - '0' + inc) % 10);
        }
    }

    /**
     * 计算两个整数的最大公约数
     * 使用欧几里得算法（辗转相除法）来计算最大公约数
     *
     * @param a 第一个整数
     * @param b 第二个整数
     * @return 两个数的最大公约数
     */
    private int gcd(int a, int b) {
        // 使用欧几里得算法计算最大公约数
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}

