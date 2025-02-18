package T2502;

/**
 * @Description: 双指针
 * @Author: iniwym
 * @Date: 2025-02-17
 * @Link: https://leetcode.cn/problems/element-appearing-more-than-25-in-sorted-array/
 */
public class Code1287_ElementAppearingMoreThan25InSortedArray {

    /**
     * 寻找数组中出现次数超过数组长度四分之一的特殊整数
     * 如果存在这样的整数，则返回该整数；否则返回-1
     *
     * @param arr 给定的整数数组
     * @return 出现次数超过数组长度四分之一的整数，如果不存在，则返回-1
     */
    public int findSpecialInteger(int[] arr) {
        // 检查数组是否为空或为空数组，如果是，则返回-1
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // 如果数组只有一个元素，则直接返回该元素
        if (arr.length == 1) {
            return arr[0];
        }

        int n = arr.length;
        int count = 1;

        // 从第二个元素开始遍历数组
        for (int i = 1; i < n; i++) {
            // 如果当前元素与前一个元素相同，则计数器加一
            if (arr[i] == arr[i - 1]) {
                count++;
            } else {
                // 如果当前元素与前一个元素不同，则重置计数器
                count = 1;
            }

            // 如果计数器超过数组长度的四分之一，则返回当前元素
            if (count > n / 4) {
                return arr[i];
            }
        }

        // 如果没有找到满足条件的元素，则返回-1
        return -1;
    }
}
