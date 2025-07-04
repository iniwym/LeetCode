package T2507;

/**
 * @Description: 递归
 * @Author: iniwym
 * @Date: 2025-07-04
 * @Link: https://leetcode.cn/problems/find-the-k-th-character-in-string-game-ii/
 */
public class Code3307_FindTheKThCharacterInStringGameIi {

    public char kthCharacter(long k, int[] operations) {
        return f(k, operations, 63 - Long.numberOfLeadingZeros(k - 1));
    }

    /**
     * 递归计算字符序列中的第k个字符
     *
     * @param k          目标字符的位置（1-based）
     * @param operations 操作序列数组，每个元素表示对右半段字符的循环位移量
     * @param i          当前处理的operations数组索引
     * @return 返回位置k对应的字符
     */
    private char f(long k, int[] operations, int i) {
        // 递归基：当处理完所有操作时返回基础字符'a'
        if (i < 0) {
            return 'a';
        }

        int op = operations[i];

        // 如果k在左半段，直接递归处理左半段
        if (k <= (1L << i)) {
            return f(k, operations, i - 1);
        }

        // 处理右半段：先递归计算相对位置，然后应用循环位移操作
        char ans = f(k - (1L << i), operations, i - 1);
        return (char) ('a' + (ans - 'a' + op) % 26);
    }

}
