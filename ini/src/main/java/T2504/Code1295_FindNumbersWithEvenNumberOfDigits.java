package T2504;

/**
 * @Description: 数字转化为字符串
 * @Author: iniwym
 * @Date: 2025-04-30
 * @Link: https://leetcode.cn/problems/find-numbers-with-even-number-of-digits/
 */
public class Code1295_FindNumbersWithEvenNumberOfDigits {

    /**
     * 这个方法用于统计一个整数数组中，位数为偶数的数字的数量
     * 例如，数字12有两位，所以它会被统计在内；而数字123有三位，不会被统计
     *
     * @param nums 这是一个整数数组，包含待检查的数字
     * @return 返回位数为偶数的数字的数量
     */
    public int findNumbers(int[] nums) {
        // 初始化计数器为0，用于统计位数为偶数的数字数量
        int count = 0;

        // 遍历数组中的每个数字
        for (int i = 0; i < nums.length; i++) {
            // 将数字转换为字符串，以便通过字符串长度判断数字的位数
            String s = String.valueOf(nums[i]);
            // 检查字符串长度是否为偶数，如果是，则增加计数器
            if (s.length() % 2 == 0) {
                count++;
            }
        }

        // 返回统计结果
        return count;
    }

}
