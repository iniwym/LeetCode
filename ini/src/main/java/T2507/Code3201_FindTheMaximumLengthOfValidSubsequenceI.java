package T2507;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-07-16
 * @Link: https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-i/
 */
public class Code3201_FindTheMaximumLengthOfValidSubsequenceI {

    /**
     * 计算满足特定奇偶模式的最长子序列长度。
     * 子序列需要满足交替的奇偶性，模式包括四种可能的组合：[0,0], [0,1], [1,0], [1,1]。
     *
     * @param nums 输入的整数数组，包含需要检查的数字
     * @return 返回满足任一模式的最长子序列长度
     */
    public int maximumLength(int[] nums) {
        int res = 0;
        // 定义四种可能的奇偶模式：偶数/偶数、偶数/奇数、奇数/偶数、奇数/奇数
        int[][] patterns = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

        // 遍历每种模式，计算满足该模式的最长子序列长度
        for (int[] pattern : patterns) {
            int cnt = 0;
            for (int num : nums) {
                // 检查当前数字是否符合模式的奇偶性要求（模式按循环交替）
                if (num % 2 == pattern[cnt % 2]) {
                    cnt++;
                }
            }
            // 更新全局最大长度
            res = Math.max(res, cnt);
        }
        return res;
    }


}
