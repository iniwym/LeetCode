package T2506;

import java.math.BigInteger;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-06-26
 * @Link: https://leetcode.cn/problems/longest-binary-subsequence-less-than-or-equal-to-k/
 */
public class Code2311_LongestBinarySubsequenceLessThanOrEqualToK {
    /**
     * 计算给定字符串s中，二进制值小于等于k的最长子序列的长度
     * 子序列通过从s中移除字符形成，但不改变剩余字符的相对位置
     *
     * @param s 输入的二进制字符串
     * @param k 限制的二进制值
     * @return 最长子序列的长度
     */
    public int longestSubsequence(String s, int k) {
        // 初始化当前处理的字符串为输入字符串s
        String current = s;
        // 进入无限循环，直到满足某个条件后跳出
        while (true) {
            // 如果当前字符串为空，说明处理结束，跳出循环
            if (current.isEmpty()) {
                break;
            }
            // 将当前字符串视为二进制数，转换为BigInteger类型
            BigInteger value = new BigInteger(current, 2);
            // 将限制值k转换为BigInteger类型，以便进行比较
            BigInteger kBig = BigInteger.valueOf(k);
            // 如果当前二进制数小于等于k，说明找到了满足条件的子序列，跳出循环
            if (value.compareTo(kBig) <= 0) {
                break;
            }
            // 查找当前字符串中第一个'1'的位置，因为移除'1'可以减小二进制值
            int index = current.indexOf('1');
            // 如果找不到'1'，说明当前字符串已经是全'0'，跳出循环
            if (index == -1) {
                break;
            }
            // 移除找到的'1'，继续下一轮处理
            current = current.substring(0, index) + current.substring(index + 1);
        }
        // 返回处理后的字符串长度，即为所求的最长子序列长度
        return current.length();
    }

    /**
     * 计算给定字符串s中，满足其二进制表示对应的整数值不超过k的最长子序列的长度
     *
     * @param s 输入的字符串，仅包含 '0' 和 '1'
     * @param k 限制的整数值
     * @return 最长子序列的长度
     */
    public int longestSubsequence1(String s, int k) {
        int n = s.length();
        int value = 0;
        int length = 0;
        int pow = 0;

        // 从右往左构建子序列
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '0') {
                // 添加 '0' 不会影响数值大小，可以直接加
                length++;
            } else {
                // 尝试添加这个 '1'
                if (pow < 31) { // 防止溢出 Integer.MAX_VALUE
                    long newValue = (1 << pow) + value;
                    if (newValue <= k) {
                        value += (1 << pow);
                        length++;
                    }
                }
            }
            pow++;
        }

        return length;
    }

}
