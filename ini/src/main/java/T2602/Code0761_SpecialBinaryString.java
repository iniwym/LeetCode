package T2602;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 递归排序
 * @Author: iniwym
 * @Date: 2026-02-20
 * @Link: https://leetcode.cn/problems/special-binary-string/
 */
public class Code0761_SpecialBinaryString {

    /**
     * 将给定的二进制字符串转换为字典序最大的特殊字符串。
     * 特殊字符串定义为：只包含字符 '1' 和 '0'，并且满足以下条件：
     * 1. 字符串中 '1' 的数量等于 '0' 的数量；
     * 2. 对于任意前缀，'1' 的数量大于等于 '0' 的数量。
     *
     * @param s 输入的二进制字符串，保证是一个有效的特殊字符串
     * @return 返回字典序最大的特殊字符串
     */
    public String makeLargestSpecial(String s) {
        // 如果字符串长度小于等于2，直接返回原字符串（因为无法进一步分割）
        if (s.length() <= 2) {
            return s;
        }

        // 把 s 划分成若干段合法括号字符串，记录在 substrings 中
        List<String> substrings = new ArrayList<>();
        int diff = 0; // 左括号个数 - 右括号个数
        int start = 0; // 子串开始下标

        // 遍历字符串，将字符串划分为多个不可再分的合法子串
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '1') { // 左括号
                diff++;
            } else if (--diff == 0) { // 右括号
                // 子串 [start, i] 是合法括号字符串，且无法继续划分
                // 这意味着子串 [start, i] 只能是嵌套的括号，那么去掉外层的括号，递归解决 [start+1, i-1]
                substrings.add("1" + makeLargestSpecial(s.substring(start + 1, i)) + "0");
                start = i + 1; // 下一个子串从 i+1 开始
            }
        }

        // 按照字典序降序排列所有子串
        substrings.sort((a, b) -> b.compareTo(a));

        // 将排序后的子串拼接成最终结果并返回
        return String.join("", substrings);
    }


}
