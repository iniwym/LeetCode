package T2504;

import java.util.Arrays;

/**
 * @Description: 逆序遍历+标记
 * @Author: iniwym
 * @Date: 2025-04-08
 * @Link: https://leetcode.cn/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/
 */
public class Code3396_MinimumNumberOfOperationsToMakeElementsInArrayDistinct {
    /**
     * 计算将数组转换为满足特定条件所需的最小操作次数。
     *
     * @param nums 输入的整数数组
     * @return 需要的操作次数，若无需操作则返回0
     */
    public static int minimumOperations(int[] nums) {
        int n = nums.length;
        // 初始化数组temp，用于标记元素是否已出现
        int[] temp = new int[101];
        Arrays.fill(temp, -1);
        int index = n - 1;
        boolean flag = false;
        // 从后向前遍历数组，记录首次出现重复元素的位置
        for (; index >= 0; index--) {
            if (temp[nums[index]] == -1) {
                temp[nums[index]] = nums[index];
            } else {
                flag = true;
                break;
            }
        }
        // 根据是否存在重复元素及位置计算操作次数
        return flag ? index / 3 + 1 : 0;
    }

}
