package T2603;

/**
 * @Description: 递归
 * @Author: iniwym
 * @Date: 2026-03-03
 * @Link: https://leetcode.cn/problems/find-kth-bit-in-nth-binary-string/
 */
public class Code1545_FindKthBitInNthBinaryString {


    /**
     * 在第 n 个二进制字符串中查找第 k 位的字符
     * 使用递归方法，基于以下规律：
     * 1. S1 = "0"
     * 2. Sn = Sn-1 + "1" + reverse(invert(Sn-1))
     *
     * @param n 二进制字符串的序号（从 1 开始）
     * @param k 要查找的位的位置（从 1 开始）
     * @return 第 n 个二进制字符串中第 k 位的字符（'0' 或 '1'）
     */
    public char findKthBit(int n, int k) {
        // 基础情况：S1 = "0"
        if (n == 1) {
            return '0';
        }
        // 如果 k 恰好是中间位置，返回 '1'
        if (k == 1 << (n - 1)) {
            return '1';
        }
        // 如果 k 在前半部分，递归查找 Sn-1
        if (k < 1 << (n - 1)) {
            return findKthBit(n - 1, k);
        }
        // 如果 k 在后半部分，递归查找对称位置并翻转结果
        char res = findKthBit(n - 1, (1 << n) - k);
        return (char) (res ^ 1);
    }


}
