package T2603;

/**
 * @Description: 模拟
 * @Author: iniwym
 * @Date: 2026-03-05
 * @Link: https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string/
 */
public class Code1758_MinimumChangesToMakeAlternatingBinaryString {


    /**
     * 计算将二进制字符串转换为交替字符串所需的最小操作次数
     *
     * @param s 输入的二进制字符串，仅包含字符 '0' 和 '1'
     * @return 最小操作次数，使得字符串变为交替形式（如 "0101..." 或 "1010..."）
     */
    public int minOperations(String s) {

        char[] chars = s.toCharArray();
        // ans0: 转换为以 '0' 开头的交替字符串所需的操作数 (模式：010101...)
        // ans1: 转换为以 '1' 开头的交替字符串所需的操作数 (模式：101010...)
        int ans0 = 0;
        int ans1 = 0;
        // 遍历字符串的每个字符，统计两种交替模式各自需要的变更次数
        for (int i = 0; i < chars.length; i++) {
            // 偶数位置的处理逻辑
            if (i % 2 == 0) {
                // 对于以 '1' 开头的模式，偶数位应为 '1'，如果是 '0' 则需要变更
                if (chars[i] == '0') {
                    ans1++;
                } else {
                    // 对于以 '0' 开头的模式，偶数位应为 '0'，如果是 '1' 则需要变更
                    ans0++;
                }
            }
            // 奇数位置的处理逻辑
            if (i % 2 != 0) {
                // 对于以 '0' 开头的模式，奇数位应为 '1'，如果是 '0' 则需要变更
                if (chars[i] == '0') {
                    ans0++;
                } else {
                    // 对于以 '1' 开头的模式，奇数位应为 '0'，如果是 '1' 则需要变更
                    ans1++;
                }
            }
        }
        // 返回两种交替模式中操作次数较少的那个
        return Math.min(ans0, ans1);
    }

}
