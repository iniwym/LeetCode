package T2505;

/**
 * @Description: 贪心策略 奇偶性
 * @Author: iniwym
 * @Date: 2025-05-23
 * @Link: https://leetcode.cn/problems/find-the-maximum-sum-of-node-values/
 */
public class Code3068_FindTheMaximumSumOfNodeValues {

    /**
     * 计算通过最多改变一个元素的值，数组元素和的最大可能值
     *
     * @param nums  原始数组
     * @param k     可以与数组元素进行异或操作的值
     * @param edges 边缘条件，本题中未使用
     * @return 数组元素和的最大可能值
     */
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        // 计算原始数组元素的和
        long sumOriginal = 0;
        for (int num : nums) {
            sumOriginal += num;
        }

        // 初始化变量以计算正增量的总和、正增量的数量、最小正增量和最大负增量
        long sumPos = 0;
        int cntPos = 0;
        long minPos = Long.MAX_VALUE;
        long maxNeg = Long.MIN_VALUE;

        // 遍历数组，计算每个元素变化后的增量
        for (int num : nums) {
            long delta = (num ^ k) - num;
            // 如果增量为正，累加到正增量总和中，并更新最小正增量
            if (delta > 0) {
                sumPos += delta;
                cntPos++;
                if (delta < minPos) {
                    minPos = delta;
                }
            } else {
                // 如果增量为负，更新最大负增量
                if (delta > maxNeg) {
                    maxNeg = delta;
                }
            }
        }

        // 根据正增益数量的奇偶性决定如何处理
        if (cntPos % 2 == 0) {
            // 偶数个正增益：直接全部采用，总和最大
            return sumOriginal + sumPos;
        } else {
            // 奇数个正增益：需要调整成偶数，两种方案选最优
            // 方案1：舍弃最小的正增益，总和减少minPos
            long option1 = sumPos - minPos;
            // 方案2：若存在负增益，尝试用最大的负增益替换一个正增益（总和增加maxNeg）
            // 若没有负增益，option2无效（设为Long.MIN_VALUE）
            long option2 = (maxNeg != Long.MIN_VALUE) ? (sumPos + maxNeg) : Long.MIN_VALUE;
            // 选择两种方案中的较大值
            long maxDelta = Math.max(option1, option2);
            // 返回初始总和加上调整后的增益
            return sumOriginal + maxDelta;
        }
    }

}
