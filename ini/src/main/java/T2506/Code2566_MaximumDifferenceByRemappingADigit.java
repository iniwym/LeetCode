package T2506;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-06-14
 * @Link: https://leetcode.cn/problems/maximum-difference-by-remapping-a-digit/
 */
public class Code2566_MaximumDifferenceByRemappingADigit {

    /**
     * 计算一个整数在改变一个数字的情况下能得到的最大值与最小值的差
     *
     * @param num 输入的整数
     * @return 最大值与最小值的差
     */
    public int minMaxDifference(int num) {
        // 将整数转换为字符串，以便进行字符操作
        String str = String.valueOf(num);
        // 获取字符串长度
        int n = str.length();
        // 初始化两个字符数组，用于存储计算最大值和最小值的中间结果
        char[] chars1 = new char[n];
        char[] chars2 = new char[n];
        // 初始化最小字符和最大字符为字符串的第一个字符
        char minChar = str.charAt(0);
        char maxChar = str.charAt(0);

        // 寻找字符串中第一个不为'9'的字符，以确定可以替换为'9'来获取最大值的字符
        for (int i = 1; maxChar == '9' && i < n; i++) {
            maxChar = str.charAt(i);
        }

        // 根据找到的最大字符和最小字符，生成两个新的字符串
        for (int i = 0; i < n; i++) {
            char current = str.charAt(i);
            // 如果当前字符与最大字符相同，则在chars1中替换为'9'，以计算最大值
            chars1[i] = (current == maxChar) ? '9' : current;
            // 如果当前字符与最小字符相同，则在chars2中替换为'0'，以计算最小值
            chars2[i] = (current == minChar) ? '0' : current;
        }

        // 将生成的字符数组转换回整数，并计算两者的差值
        return Integer.parseInt(new String(chars1)) - Integer.parseInt(new String(chars2));
    }

}
