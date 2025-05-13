package T2505;

/**
 * @Description: 递推
 * @Author: iniwym
 * @Date: 2025-05-13
 * @Link: https://leetcode.cn/problems/total-characters-in-string-after-transformations-i/
 */
public class Code3335_TotalCharactersInStringAfterTransformationsI {

    public static final int MOD = 1000000007;

    /**
     * 计算字符串s经过t次转换后的长度
     * 每次转换将字符串中的每个字母替换为字母表中它后面的那个字母，'z'替换为'a'
     *
     * @param s 输入的字符串
     * @param t 转换次数
     * @return 经过t次转换后的字符串长度
     */
    public static int lengthAfterTransformations(String s, int t) {
        // 计数数组，用于统计字符串s中每个字母的出现次数
        int[] cnt = new int[26];
        // 遍历字符串s，统计每个字母的出现次数
        for (char ch : s.toCharArray()) {
            cnt[ch - 'a']++;
        }

        // 执行t次转换
        for (int i = 0; i < t; i++) {
            // 临时数组，用于存储下一次转换后的字母计数
            int[] next = new int[26];
            // 'a'的转换结果是'z'，所以先将'z'的计数赋值给'a'
            next[0] = cnt[25];
            // 'b'的转换结果是'a'加上'z'的计数，因为'a'会从'z'转换来
            next[1] = (cnt[25] + cnt[0]) % MOD;
            // 其他字母的转换结果是它们前一个字母的计数
            for (int j = 2; j < 26; j++) {
                next[j] = cnt[j - 1];
            }
            // 将临时数组next的值赋给cnt，为下一次转换做准备
            cnt = next;
        }

        // 初始化答案变量
        int ans = 0;
        // 遍历计数数组，累加所有字母转换后的计数
        for (int i = 0; i < 26; i++) {
            ans = (ans + cnt[i]) % MOD;
        }

        // 返回最终的长度
        return ans;
    }

    public static void main(String[] args) {
        String s = "aabaacc";
        int t = 2;
        System.out.println(lengthAfterTransformations(s, t));
    }

}
