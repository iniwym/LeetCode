package T2602;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2026-02-05
 * @Link: https://leetcode.cn/problems/transformed-array/
 */
public class Code3379_TransformedArray {

    /**
     * 构造一个变换后的数组，其中每个元素根据原数组中对应位置的值进行循环偏移计算。
     *
     * @param nums 输入的整数数组，用于构造变换后的数组
     * @return 变换后的新数组，其长度与输入数组相同
     */
    public int[] constructTransformedArray(int[] nums) {
        int length = nums.length;
        int[] ans = new int[length];

        // 遍历输入数组，计算每个位置在变换后数组中的值
        for (int i = 0; i < length; i++) {
            // 计算目标索引，通过当前索引加上数组值并取模处理负数情况
            int temp = (i + nums[i]) % length;
            temp = temp < 0 ? temp + length : temp;
            ans[i] = nums[temp];
        }

        return ans;
    }

}
