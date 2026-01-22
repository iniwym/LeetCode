package T2601;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 暴力枚举
 * @Author: iniwym
 * @Date: 2026-01-22
 * @Link: https://leetcode.cn/problems/minimum-pair-removal-to-sort-array-i/
 */
public class Code3507_MinimumPairRemovalToSortArrayI {

    /**
     * 计算将数组转换为非递减序列所需的最少相邻元素合并操作次数
     *
     * @param nums 输入的整数数组
     * @return 返回需要执行的最少合并操作次数
     */
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // 转换为列表，方便合并操作
        List<Long> list = new ArrayList<>();
        for (int num : nums) {
            list.add((long) num);
        }

        int operations = 0;

        while (true) {
            // 检查是否已满足非递减
            if (isNonDecreasing(list)) {
                break;
            }

            // 找到最小和的相邻对
            int minIdx = 0;
            long minSum = Long.MAX_VALUE;

            for (int i = 0; i < list.size() - 1; i++) {
                long sum = list.get(i) + list.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    minIdx = i;
                }
            }

            // 合并这一对
            list.set(minIdx, minSum);
            list.remove(minIdx + 1);
            operations++;
        }

        return operations;
    }

    /**
     * 判断列表是否为非递减序列
     *
     * @param list 待检查的Long类型列表
     * @return 如果列表中的元素按非递减顺序排列则返回true，否则返回false
     */
    private boolean isNonDecreasing(List<Long> list) {
        // 遍历列表，检查相邻元素是否满足非递减关系
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

}
