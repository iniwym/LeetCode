package T2510;

/**
 * @Description: 位运算
 * @Author: iniwym
 * @Date: 2025-10-31
 * @Link: https://leetcode.cn/problems/the-two-sneaky-numbers-of-digitville/1
 */
public class Code3289_TheTwoSneakyNumbersOfDigitville {
    /**
     * 找到数组中重复出现的两个数字
     *
     * @param nums 包含n+2个元素的数组，其中包含从0到n的所有整数，但恰好有两个数字出现了两次，其余数字各出现一次
     * @return 包含两个重复数字的数组
     */
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length - 2;
        // 计算0到n+1所有数字的异或结果，由于有两个数字重复出现，这个值等于两个重复数字的异或
        int xorAll = n ^ (n + 1); // n 和 n+1 多异或了
        for (int i = 0; i < nums.length; i++) {
            xorAll ^= i ^ nums[i];
        }
        // 找到xorAll中最右边的1的位置，用于区分两个重复的数字
        int shift = Integer.numberOfTrailingZeros(xorAll);

        int[] ans = new int[2];
        // 根据shift位将数字分组，分别计算两组的异或值，从而得到两个重复的数字
        for (int i = 0; i < nums.length; i++) {
            if (i < n) {
                ans[i >> shift & 1] ^= i;
            }
            ans[nums[i] >> shift & 1] ^= nums[i];
        }
        return ans;
    }

}

