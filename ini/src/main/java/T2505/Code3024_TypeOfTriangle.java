package T2505;

/**
 * @Description: 数学运算
 * @Author: iniwym
 * @Date: 2025-05-19
 * @Link: https://leetcode.cn/problems/type-of-triangle/
 */
public class Code3024_TypeOfTriangle {

    /**
     * 判断三角形的类型
     *
     * @param nums 一个包含三个整数的数组，代表三角形的三条边
     * @return 返回一个字符串，表示三角形的类型："equilateral"（等边三角形），"isosceles"（等腰三角形），"scalene"（不等边三角形），或 "none"（不是三角形）
     */
    public String triangleType(int[] nums) {

        // 提取数组中的三个边长
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];
        // 初始化答案字符串
        String ans = "";

        // 如果任意两边之和小于等于第三边，则构不成三角形
        if (a + b <= c || a + c <= b || b + c <= a) {
            ans = "none";
        }
        // 如果三边相等，则是等边三角形
        else if (a == b && a == c && b == c) {
            ans = "equilateral";
        }
        // 如果两边相等，则是等腰三角形
        else if (a == b || a == c || b == c) {
            ans = "isosceles";
        }
        // 如果三边都不相等，则是不等边三角形
        else {
            ans = "scalene";
        }

        // 返回判断结果
        return ans;

    }

}
