package T2509;

/**
 * @Description: 遍历数组
 * @Author: iniwym
 * @Date: 2025-09-30
 * @Link: https://leetcode.cn/problems/find-triangular-sum-of-an-array/
 */
public class Code2221_FindTriangularSumOfAnArray {

    /**
     * 计算数组的三角和
     * 该方法通过多轮迭代计算数组的三角和。每轮迭代中，相邻两个元素相加后对10取余，
     * 并将结果存储在原数组中，每轮结束后数组长度减一，直到只剩一个元素。
     *
     * @param nums 输入的整数数组
     * @return 返回计算得到的三角和（最后一个剩余的元素）
     */
    public int triangularSum(int[] nums) {
        // 每循环一轮，数组长度就减一
        for (int n = nums.length - 1; n > 0; n--) {
            // 对当前轮次中的相邻元素进行相加并取余运算
            for (int i = 0; i < n; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }

}
