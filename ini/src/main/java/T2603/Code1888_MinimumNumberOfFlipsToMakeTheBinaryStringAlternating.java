package T2603;

/**
 * @Description: 滑动窗口
 * @Author: iniwym
 * @Date: 2026-03-07
 * @Link: https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/
 */
public class Code1888_MinimumNumberOfFlipsToMakeTheBinaryStringAlternating {


    /**
     * 计算将二进制字符串转换为交替字符串所需的最小翻转次数
     *
     * @param S 输入的二进制字符串，仅包含字符 '0' 和 '1'
     * @return 最小翻转次数，使得字符串变为交替形式（如 "0101..." 或 "1010..."）
     * 允许的操作：将任意位置的 '0' 翻转为 '1'，或将 '1' 翻转为 '0'
     */
    public int minFlips(String S) {
        // 将字符串转换为字符数组便于处理
        char[] s = S.toCharArray();
        int n = s.length;
        // ans: 记录最小翻转次数，初始化为最大可能值 n
        int ans = n;
        // cnt: 当前窗口内与目标模式 (0101...) 不匹配的字符数
        int cnt = 0;
        // 使用滑动窗口遍历所有可能的旋转情况，窗口长度为 n
        // 遍历范围为 2n-1 是因为需要覆盖所有可能的起始位置
        for (int i = 0; i < n * 2 - 1; i++) {
            // 统计当前位置与目标模式 (偶数位为'0',奇数位为'1') 的不匹配数
            if (s[i % n] % 2 != i % 2) {
                cnt++;
            }
            // 计算窗口的左边界位置
            int left = i - n + 1;
            // 如果窗口还未形成 (长度不足 n)，继续扩展窗口
            if (left < 0) {
                continue;
            }
            // 更新最小翻转次数：cnt 是匹配模式 0101... 的翻转数，n-cnt 是匹配模式 1010... 的翻转数
            ans = Math.min(ans, Math.min(cnt, n - cnt));
            // 滑动窗口：移除左边界元素对计数的影响
            if (s[left] % 2 != left % 2) {
                cnt--;
            }
        }
        return ans;
    }


}
