package T2508;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 字母异位词分组
 * @Author: iniwym
 * @Date: 2025-08-10
 * @Link: https://leetcode.cn/problems/reordered-power-of-2/
 */
public class Code0869_ReorderedPowerOf2 {

    private static final Set<String> powTwoSortedStrSet = new HashSet<>();

    static {
        final int MAX_N = 1_000_000_000;
        for (int i = 1; i < MAX_N; i <<= 1) {
            String s = intToSortedStr(i);
            powTwoSortedStrSet.add(s);
        }
    }

    private static String intToSortedStr(int n) {
        char[] s = String.valueOf(n).toCharArray();
        Arrays.sort(s);
        return new String(s);
    }

    public boolean reorderedPowerOf2(int n) {
        String s = intToSortedStr(n);
        return powTwoSortedStrSet.contains(s);
    }

}
