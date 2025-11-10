package T2511;

/**
 * @Description: 单调栈
 * @Author: iniwym
 * @Date: 2025-11-10
 * @Link: https://leetcode.cn/problems/minimum-operations-to-convert-all-elements-to-zero/
 */
public class Code3542_MinimumOperationsToConvertAllElementsToZero {

    /**
     * 计算使数组非递减所需的最小操作次数
     * <p>
     * 解题思路：
     * 将数组视为栈结构进行处理。遍历数组时，如果当前元素小于栈顶元素，
     * 则需要将栈顶元素出栈（相当于一次操作），直到当前元素大于等于栈顶元素。
     * 相同的元素可以在同一次操作中处理，因此相等时不需要入栈。
     * 最终结果为操作次数加上剩余栈中元素个数，以及首元素是否需要额外操作。
     *
     * @param nums 输入的整数数组
     * @return 使数组变为非递减所需的最小操作次数
     */
    public int minOperations(int[] nums) {
        int ans = 0;
        int top = -1; // 栈顶下标（把 nums 当作栈）

        // 遍历数组中的每个元素
        for (int x : nums) {
            // 当栈不为空且当前元素小于栈顶元素时，需要出栈操作
            while (top >= 0 && x < nums[top]) {
                top--; // 出栈
                ans++;
            }

            // 如果栈为空或当前元素与栈顶不同，则将当前元素入栈
            // 相同元素可在同一操作中处理，无需重复入栈
            if (top < 0 || x != nums[top]) {
                nums[++top] = x; // 入栈
            }
        }

        // 返回总操作次数：已执行的操作数 + 剩余栈中元素数 + 首元素是否需要额外操作
        return ans + top + (nums[0] > 0 ? 1 : 0);
    }

}
