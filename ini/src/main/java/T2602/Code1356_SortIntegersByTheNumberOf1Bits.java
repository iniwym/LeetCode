package T2602;

import java.util.Arrays;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-02-25
 * @Link: https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits/
 */
public class Code1356_SortIntegersByTheNumberOf1Bits {

    /**
     * 对数组按照每个元素的二进制表示中1的个数进行排序。
     * 如果两个元素的1的个数相同，则按数值大小排序。
     *
     * @param arr 输入的整数数组
     * @return 排序后的数组
     */
    public int[] sortByBits(int[] arr) {
        // 将每个元素的二进制中1的个数左移16位，并与原值合并存储到数组中
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.bitCount(arr[i]) << 16 | arr[i];
        }

        // 对数组进行排序，此时会先比较高位（1的个数），再比较低位（原值）
        Arrays.sort(arr);

        // 恢复原始值，清除高16位的1的个数信息
        for (int i = 0; i < arr.length; i++) {
            arr[i] &= 0xffff;
        }

        return arr;
    }

}
