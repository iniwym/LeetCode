package T2602;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-19
 * @Link: https://leetcode.cn/problems/binary-number-with-alternating-bits/
 */
public class Code0693_BinaryNumberWithAlternatingBits {

    /**
     * 判断一个正整数的二进制表示是否具有交替位（即相邻两位永不相同）。
     *
     * @param n 待检查的正整数
     * @return 如果二进制表示中相邻位永不相同，返回 true；否则返回 false
     */
    public boolean hasAlternatingBits(int n) {
        // 计算 n 右移一位后与原数的异或结果，用于检测相邻位是否不同
        int x = (n >> 1) ^ n;

        // 检查 x 是否为全1的形式（即 x+1 与 x 的按位与结果为0）
        return ((x + 1) & x) == 0;
    }


}
