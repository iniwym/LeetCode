package T2502;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2025-02-16
 * @Link: https://leetcode.cn/problems/replace-elements-with-greatest-element-on-right-side/
 */
public class Code1299_ReplaceElementsWithGreatestElementOnRightSide {
    public int[] replaceElements(int[] arr) {
        // 处理 null 或空数组的情况，返回空数组
        if (arr == null || arr.length == 0) {
            return new int[0]; // 返回空数组，避免调用方逻辑混乱
        }

        int arrLen = arr.length;

        // 如果数组长度为1，直接返回包含-1的数组
        if (arrLen == 1) {
            return new int[]{-1};
        }

        int max = -1;
        // 原地修改数组，减少额外空间开销
        for (int i = arrLen - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            if (temp > max) {
                max = temp;
            }
        }

        return arr;
    }

}
