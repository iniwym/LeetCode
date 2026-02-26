package T2602;

/**
 * @Description: 模拟
 * @Author: iniwym
 * @Date: 2026-02-26
 * @Link: https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 */
public class Code1404_NumberOfStepsToReduceANumberInBinaryRepresentationToOne {

    /**
     * 计算将二进制字符串表示的数字变为 1 所需的最少步骤数。
     * 每一步操作规则如下：
     * - 如果当前数字是偶数，则将其除以 2；
     * - 如果当前数字是奇数，则将其加 1。
     *
     * @param s 表示一个正整数的二进制字符串，不含前导零。
     * @return 将该二进制数变为 1 所需的最少步骤数。
     */
    public int numSteps(String s) {
        int n = s.length();
        int ans = n - 1; // 除了最高位，其余每一位都要执行一次「除以 2」
        int carry = 0;

        // 从最低位开始处理每一位，模拟二进制运算过程
        for (int i = n - 1; i > 0; i--) {
            int sum = s.charAt(i) - '0' + carry;
            ans += sum % 2; // 如果当前位为 1（奇数），需要先加 1 再除以 2，因此增加一步
            carry = (sum + sum % 2) / 2; // 更新进位值
        }

        // 如果最高位仍有进位，说明结果为 10...0 的形式，还需额外一步除以 2
        return ans + carry;
    }

}
