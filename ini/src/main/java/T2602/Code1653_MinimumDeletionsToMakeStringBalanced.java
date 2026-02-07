package T2602;

/**
 * @Description: 字符串分割
 * @Author: iniwym
 * @Date: 2026-02-07
 * @Link: https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced/
 */
public class Code1653_MinimumDeletionsToMakeStringBalanced {

    /**
     * 计算将字符串 S 转换为平衡字符串所需的最少删除次数。
     * 平衡字符串定义为：不存在下标 i < j 使得 s[i] = 'b' 且 s[j] = 'a'。
     *
     * @param S 输入字符串，仅由字符 'a' 和 'b' 组成
     * @return 返回将字符串转换为平衡字符串所需的最少删除次数
     */
    public int minimumDeletions(String S) {
        char[] s = S.toCharArray();
        int del = 0;

        // 初始统计所有 'a' 的个数，作为初始删除次数
        for (char c : s) {
            del += 'b' - c; // 统计 'a' 的个数
        }

        int ans = del;

        // 遍历字符串，动态调整删除次数并记录最小值
        for (char c : s) {
            // 根据当前字符更新删除次数：
            // 'a' 对应 -1，'b' 对应 +1
            del += (c - 'a') * 2 - 1;
            ans = Math.min(ans, del);
        }

        return ans;
    }


}
