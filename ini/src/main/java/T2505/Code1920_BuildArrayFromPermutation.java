package T2505;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2025-05-06
 * @Link: https://leetcode.cn/problems/build-array-from-permutation/
 */
public class Code1920_BuildArrayFromPermutation {

    /**
     * 根据给定的数组构建一个新的数组
     * 新数组的每个元素都是原数组中对应位置元素所指向的元素
     * 这种映射关系是通过原数组的元素值作为索引来确定的
     *
     * @param nums 原始数组，其元素值将用作索引以构建新数组
     * @return 新构建的数组，其元素是根据原数组元素所指向的值确定的
     */
    public int[] buildArray(int[] nums) {
        // 获取原始数组的长度
        int n = nums.length;
        // 初始化新数组，用于存放最终结果
        int[] ans = new int[n];

        // 遍历原数组，构建新数组
        for (int i = 0; i < n; i++) {
            // 根据原数组元素的值作为索引，获取新数组中对应位置的元素值
            ans[i] = nums[nums[i]];
        }

        // 返回构建完成的新数组
        return ans;
    }

}
