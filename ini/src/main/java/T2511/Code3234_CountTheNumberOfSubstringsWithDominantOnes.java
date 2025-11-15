package T2511;

/**
 * @Description: 枚举子串中的 0 的个数
 * @Author: iniwym
 * @Date: 2025-11-15
 * @Link: https://leetcode.cn/problems/count-the-number-of-substrings-with-dominant-ones/
 */
public class Code3234_CountTheNumberOfSubstringsWithDominantOnes {

    /**
     * 计算字符串中满足特定条件的子串数量
     * 条件：子串中字符'1'的个数大于等于字符'0'个数的平方
     *
     * @param s 输入的二进制字符串，只包含字符'0'和'1'
     * @return 满足条件的子串总数
     */
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] pos0 = new int[n + 1]; // 记录'0'在字符串中的下标位置
        pos0[0] = -1; // 添加哨兵节点，便于边界情况处理
        int size = 1;
        int total1 = 0; // 统计区间[0,r]中'1'的总个数
        int ans = 0;

        // 遍历字符串的每个位置作为右端点r
        for (int r = 0; r < n; r++) {
            if (s.charAt(r) == '0') {
                pos0[size++] = r; // 记录'0'的位置
            } else {
                total1++;
                // 计算以当前位置结尾且不包含任何'0'的子串个数
                ans += r - pos0[size - 1];
            }

            // 枚举所有可能的'0'的个数，计算对应的满足条件的子串数量
            // 从后往前遍历'0'的位置数组，cnt0表示当前考虑的'0'的个数
            for (int i = size - 1; i > 0 && (size - i) * (size - i) <= total1; i--) {
                int p = pos0[i - 1];
                int q = pos0[i];
                int cnt0 = size - i;
                int cnt1 = r - q + 1 - cnt0; // 计算区间[q,r]中'1'的个数
                // 根据数学推导计算满足条件的子串个数
                ans += Math.max(q - Math.max(cnt0 * cnt0 - cnt1, 0) - p, 0);
            }
        }

        return ans;
    }

}

