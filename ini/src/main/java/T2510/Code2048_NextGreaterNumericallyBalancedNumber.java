package T2510;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-10-24
 * @Link: https://leetcode.cn/problems/next-greater-numerically-balanced-number/
 */
public class Code2048_NextGreaterNumericallyBalancedNumber {

    /**
     * 查找下一个美丽的数字
     * 美丽数字的定义：对于数字中的每一位数字d，数字d在该数字中恰好出现d次
     * 例如：1是美丽的（数字1出现1次），22是美丽的（数字2出现2次）
     *
     * @param n 起始数字，查找大于n的下一个美丽数字
     * @return 大于n的下一个美丽数字
     */
    public int nextBeautifulNumber(int n) {
        // 循环查找下一个美丽数字
        next:
        while (true) {
            n++;

            // 统计当前数字中每个数位的出现次数
            int[] cnt = new int[10];
            for (int x = n; x > 0; x /= 10) {
                cnt[x % 10]++;
            }

            // 检查当前数字是否为美丽数字
            // 对于每个数位d，验证其出现次数是否等于d本身
            for (int x = n; x > 0; x /= 10) {
                if (cnt[x % 10] != x % 10) {
                    continue next;
                }
            }

            return n;
        }
    }

}

