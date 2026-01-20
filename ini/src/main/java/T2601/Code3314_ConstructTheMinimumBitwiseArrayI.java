package T2601;

import java.util.List;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2026-01-20
 * @Link: https://leetcode.cn/problems/construct-the-minimum-bitwise-array-i/
 */
public class Code3314_ConstructTheMinimumBitwiseArrayI {

    /**
     * 计算每个数字对应的最小正整数，使得该正整数与其二进制表示中最低位的1进行异或运算后等于原数字
     *
     * @param nums 输入的整数列表
     * @return 返回一个数组，其中每个元素是对应输入数字的最小正整数，如果不存在则返回-1
     */
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            if (x == 2) {
                ans[i] = -1;
            } else {
                // 计算x+1的最低位1的位置，并右移一位得到对应的掩码
                int lowbit = (x + 1) & ~x;
                // 通过异或运算计算出最小的正整数
                ans[i] = x ^ (lowbit >> 1);
            }
        }
        return ans;
    }

}
