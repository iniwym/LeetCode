package T2511;

/**
 * @Description: 数组遍历
 * @Author: iniwym
 * @Date: 2025-11-18
 * @Link: https://leetcode.cn/problems/1-bit-and-2-bit-characters/
 */
public class Code0717_1BitAnd2BitCharacters {

    /**
     * 判断一个以0结尾的二进制数组是否能被特殊规则解码，使得最后一个字符是单独的一位字符(0)
     * 解码规则：
     * - 数字0表示一位字符
     * - 数字1表示两位字符的开始(10或11)
     *
     * @param bits 输入的二进制数组，保证以0结尾
     * @return 如果最后一个0能够作为单独的一位字符存在则返回true，否则返回false
     */
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        // 从头开始遍历数组，按照解码规则跳过相应的位置数
        // 循环直到剩下至多一个数字
        while (i < n - 1) {
            i += bits[i] + 1; // 如果 bits[i] == 1 则跳过下一位
        }
        // 如果最终位置恰好停在最后一个元素上，说明最后的0是单独的一位字符
        return i == n - 1; // 注意题目保证 bits[n-1] == 0，无需判断
    }
}
