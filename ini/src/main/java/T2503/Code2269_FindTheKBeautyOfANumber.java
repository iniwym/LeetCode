package T2503;

/**
 * @Description: 字符串
 * @Author: iniwym
 * @Date: 2025-03-10
 * @Link: https://leetcode.cn/problems/find-the-k-beauty-of-a-number/
 */
public class Code2269_FindTheKBeautyOfANumber {

    /**
     * 计算数字num中所有长度为k的连续子字符串所构成的整数中，能被num整除的数量。
     *
     * @param num 输入的整数
     * @param k   子字符串的长度
     * @return 满足条件的子字符串的数量
     */
    public static int divisorSubstrings(int num, int k) {
        int ans = 0;
        String s = String.valueOf(num);

        // 遍历所有可能的起始位置，生成长度为k的子字符串
        for (int i = 0; i < s.length() - k + 1; i++) {
            int cur = Integer.parseInt(s.substring(i, i + k));

            // 检查当前子字符串转换的整数是否非零且能整除num，若满足则计数增加
            if (cur != 0 && num % cur == 0) {
                ans++;
            }
        }
        return ans;
    }

}
