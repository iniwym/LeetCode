package T2602;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-23
 * @Link: https://leetcode.cn/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
 */
public class Code1461_CheckIfAStringContainsAllBinaryCodesOfSizeK {

    /**
     * 检查字符串 s 是否包含所有长度为 k 的二进制子串。
     *
     * @param s 输入的二进制字符串
     * @param k 子串的长度
     * @return 如果字符串 s 包含所有可能的长度为 k 的二进制子串，则返回 true；否则返回 false
     */
    public boolean hasAllCodes(String s, int k) {
        // 使用 HashSet 存储所有长度为 k 的子串，自动去重
        Set<String> set = new HashSet<>();

        // 遍历字符串，提取所有长度为 k 的子串并加入集合
        for (int i = k; i <= s.length(); i++) {
            set.add(s.substring(i - k, i));
        }

        // 判断集合大小是否等于 2^k（即所有可能的 k 位二进制数的数量）
        return set.size() == (1 << k);
    }


}
