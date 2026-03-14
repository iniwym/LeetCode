package T2603;

/**
 * @Description: 排列
 * @Author: iniwym
 * @Date: 2026-03-14
 * @Link: https://leetcode.cn/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
 */
public class Code1415_TheKThLexicographicalStringOfAllHappyStringsOfLengthN {


    /**
     * 获取长度为 n 的第 k 个字典序快乐字符串
     * 快乐字符串定义：只包含 'a', 'b', 'c'，且相邻字符不相同的字符串
     *
     * @param n 字符串的长度
     * @param k 要获取的快乐字符串的字典序序号（从 1 开始）
     * @return 第 k 个快乐字符串，如果不存在则返回空字符串
     */
    public String getHappyString(int n, int k) {
        // 检查 k 是否超出可能的快乐字符串总数范围（总共 3 * 2^(n-1) 个）
        if (k > 3 << (n - 1)) {
            return "";
        }

        // 将 k 转换为从 0 开始的索引，便于位运算计算
        k--;

        char[] ans = new char[n];

        // 根据 k 的高位确定第一个字符（'a', 'b', 或 'c'）
        ans[0] = (char) ('a' + (k >> (n - 1)));

        // 依次确定后续每个字符的位置
        for (int i = 1; i < n; i++) {
            // 通过位运算计算当前位的值（0 或 1），并映射到对应的字符
            ans[i] = (char) ('a' + (k >> (n - 1 - i) & 1));

            // 如果当前字符大于等于前一个字符，需要向后调整一位以避免相邻字符相同
            if (ans[i] >= ans[i - 1]) {
                ans[i]++;
            }
        }

        return new String(ans);
    }


}
