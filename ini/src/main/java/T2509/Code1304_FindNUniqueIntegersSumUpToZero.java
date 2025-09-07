package T2509;

/**
 * @Description: 对称数组
 * @Author: iniwym
 * @Date: 2025-09-07
 * @Link: https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero/
 */
public class Code1304_FindNUniqueIntegersSumUpToZero {
    /**
     * 生成一个长度为n的数组，使得数组中所有元素的和为0
     * 该方法通过将数组分为两部分，前半部分填充正数，后半部分填充对应的负数来实现
     *
     * @param n 数组的长度，必须为正整数
     * @return 一个长度为n且元素和为0的整数数组
     */
    public int[] sumZero(int n) {
        int[] a = new int[n];
        int m = n / 2;
        // 填充数组的前半部分为正数，后半部分为对应的负数
        for (int i = 0; i < m; i++) {
            a[i] = i + 1;
            a[i + m] = -i - 1;
        }
        return a;
    }

}
