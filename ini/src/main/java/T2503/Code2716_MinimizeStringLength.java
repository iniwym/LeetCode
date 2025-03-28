package T2503;

import java.util.HashSet;

/**
 * @Description: HashSet/位运算
 * @Author: iniwym
 * @Date: 2025-03-28
 * @Link: https://leetcode.cn/problems/minimize-string-length/
 */
public class Code2716_MinimizeStringLength {
    public int minimizedStringLength(String s) {
        HashSet set = new HashSet();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set.size();
    }

    public int minimizedStringLength2(String s) {
        int mask = 0;
        for (char c : s.toCharArray()) {
            mask |= 1 << (c - 'a');
        }
        return Integer.bitCount(mask);
    }

}
