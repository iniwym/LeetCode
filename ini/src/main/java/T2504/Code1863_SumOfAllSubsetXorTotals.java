package T2504;

/**
 * @Description: 异或运算
 * @Author: iniwym
 * @Date: 2025-04-05
 * @Link: https://leetcode.cn/problems/sum-of-all-subset-xor-totals/
 */
public class Code1863_SumOfAllSubsetXorTotals {

    /**
     * 计算数组所有非空子集的异或结果之和。
     *
     * @param nums 输入的整数数组
     * @return 所有非空子集的异或结果之和
     */
    public int subsetXORSum(int[] nums) {
        int sum = 0;
        int n = nums.length;
        // 遍历每一位（0到31位），计算该位对总和的贡献
        for (int i = 0; i < 32; i++) {
            int count = 0;
            // 统计当前位在nums中设置为1的元素数量
            for (int num : nums) {
                if (((1 << i) & num) != 0) {
                    count++;
                }
            }

            // 如果存在至少一个元素在该位为1，则该位的总贡献为（1<<i）乘以2^(n-1)
            if (count > 0) {
                sum += (1 << i) * (1 << (n - 1));
            }
        }

        return sum;
    }

}
