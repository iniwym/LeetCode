package T2510;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 深度优先搜索
 * @Author: iniwym
 * @Date: 2025-10-17
 * @Link: https://leetcode.cn/problems/maximize-the-number-of-partitions-after-operations/
 */
public class Code3003_MaximizeTheNumberOfPartitionsAfterOperations {

    public int maxPartitionsAfterOperations(String s, int k) {
        Map<Long, Integer> memo = new HashMap<>();
        return dfs(0, 0, 0, memo, s.toCharArray(), k);
    }

    /**
     * 使用深度优先搜索计算将字符串分割成满足条件的子串的最多数量。
     * 每个子串中不同字符的数量不能超过 k。
     * 可以选择更改字符串中的一个字符（可不改）。
     *
     * @param i       当前处理到字符串的位置
     * @param mask    用位掩码表示当前子串中已出现的字符集合
     * @param changed 标记是否已经修改过字符，0 表示未修改，1 表示已修改
     * @param memo    用于记忆化搜索的哈希表，避免重复计算
     * @param s       输入的字符数组
     * @param k       每个子串中最多允许的不同字符数量
     * @return 从位置 i 开始能分割出的最多子串数量
     */
    private int dfs(int i, int mask, int changed, Map<Long, Integer> memo, char[] s, int k) {
        if (i == s.length) {
            return 1;
        }

        // 把参数压缩到一个 long 中，方便作为哈希表的 key
        long args = (long) i << 32 | mask << 1 | changed;
        if (memo.containsKey(args)) { // 之前计算过
            return memo.get(args);
        }

        int res;
        // 不改 s[i]
        int bit = 1 << (s[i] - 'a');
        int newMask = mask | bit;
        if (Integer.bitCount(newMask) > k) {
            // 分割出一个子串，这个子串的最后一个字母在 i-1
            // s[i] 作为下一段的第一个字母，也就是 bit 作为下一段的 mask 的初始值
            res = dfs(i + 1, bit, changed, memo, s, k) + 1;
        } else { // 不分割
            res = dfs(i + 1, newMask, changed, memo, s, k);
        }

        if (changed == 0) {
            // 枚举把 s[i] 改成 a,b,c,...,z
            for (int j = 0; j < 26; j++) {
                newMask = mask | (1 << j);
                if (Integer.bitCount(newMask) > k) {
                    // 分割出一个子串，这个子串的最后一个字母在 i-1
                    // j 作为下一段的第一个字母，也就是 1<<j 作为下一段的 mask 的初始值
                    res = Math.max(res, dfs(i + 1, 1 << j, 1, memo, s, k) + 1);
                } else { // 不分割
                    res = Math.max(res, dfs(i + 1, newMask, 1, memo, s, k));
                }
            }
        }

        memo.put(args, res); // 记忆化
        return res;
    }

}

