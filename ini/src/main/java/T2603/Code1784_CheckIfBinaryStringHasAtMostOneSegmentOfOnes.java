package T2603;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-03-06
 * @Link: https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones/
 */
public class Code1784_CheckIfBinaryStringHasAtMostOneSegmentOfOnes {

    /**
     * 检查二进制字符串是否最多只包含一个连续的 '1' 的段
     *
     * @param s 输入的二进制字符串，仅包含字符 '0' 和 '1'，不含前导零
     * @return 如果字符串中最多只有一个连续的 '1' 的段则返回 true，否则返回 false
     * 核心逻辑：如果存在 "01" 子串，说明在 '0' 之后又出现了 '1'，即存在多个 '1' 的段
     */
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }

}
