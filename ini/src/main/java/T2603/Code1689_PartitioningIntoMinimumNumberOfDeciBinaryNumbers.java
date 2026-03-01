package T2603;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-03-01
 * @Link: https://leetcode.cn/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
 */
public class Code1689_PartitioningIntoMinimumNumberOfDeciBinaryNumbers {

    /**
     * 计算将字符串表示的数字分割成若干个单调递增子序列所需的最少分割次数。
     *
     * @param n 输入的字符串，表示一个非负整数
     * @return 返回最少分割次数
     */
    public int minPartitions(String n) {
        // 初始化最大字符值为0
        int mx = 0;

        // 遍历字符串中的每个字符，找到最大的数字字符
        for (char ch : n.toCharArray()) {
            mx = Math.max(mx, ch);
        }

        // 将最大字符转换为对应的数字并返回
        return mx - '0';
    }

}
