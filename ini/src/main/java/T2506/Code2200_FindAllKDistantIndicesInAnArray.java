package T2506;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 遍历数组
 * @Author: iniwym
 * @Date: 2025-06-24
 * @Link: https://leetcode.cn/problems/find-all-k-distant-indices-in-an-array/
 */
public class Code2200_FindAllKDistantIndicesInAnArray {
    /**
     * 查找k距离内的索引
     * 当数组nums中的某个元素等于key时，该元素的索引及其前后k个元素的索引都会被添加到结果列表中
     *
     * @param nums 整数数组
     * @param key  关键值，用于判断是否将索引添加到结果列表
     * @param k    距离参数，定义了关键值周围多远的索引也会被添加到结果列表
     * @return 包含所有符合条件的索引的列表
     */
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        // 初始化两个布尔数组，用于标记符合条件的索引
        boolean[] flag1 = new boolean[n];
        boolean[] flag2 = new boolean[n];

        // 从左向右遍历数组，标记符合条件的索引
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                index = i;
                flag1[i] = true;
            }
            if (index >= 0 && i <= index + k) {
                flag1[i] = true;
            }
        }

        // 从右向左遍历数组，标记符合条件的索引
        index = n;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == key) {
                index = i;
                flag2[i] = true;
            }
            if (index < n && i >= index - k) {
                flag2[i] = true;
            }
        }

        // 创建结果列表，将所有在至少一个方向上符合条件的索引添加到列表中
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (flag1[i] || flag2[i]) {
                list.add(i);
            }
        }

        return list;
    }
}
