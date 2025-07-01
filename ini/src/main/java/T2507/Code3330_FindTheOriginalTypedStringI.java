package T2507;

/**
 * @Description: 遍历字符串
 * @Author: iniwym
 * @Date: 2025-07-01
 * @Link: https://leetcode.cn/problems/find-the-original-typed-string-i/
 */
public class Code3330_FindTheOriginalTypedStringI {

    /**
     * 计算字符串中可能的连续相同字符子串数量。
     * 该函数遍历字符串，统计相邻相同字符的对数，最终返回对数加1的结果（因为每个对数代表一个可能的子串分割点）。
     *
     * @param word 输入的字符串，不能为null
     * @return 可能的连续相同字符子串数量。对于长度为1的字符串直接返回1。
     */
    public int possibleStringCount(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        int ans = 0;

        // 处理长度为1的特殊情况
        if (n == 1) {
            return 1;
        }

        // 遍历字符数组，统计相邻相同字符对数
        for (int i = 1; i < n; i++) {
            if (chars[i] == chars[i - 1]) {
                ans++;
            }
        }

        // 返回对数+1（n个分割点将字符串分为n+1段）
        return ans + 1;
    }

}
