package T2506;

/**
 * @Description: 贪心
 * @Author: iniwym
 * @Date: 2025-06-15
 * @Link: https://leetcode.cn/problems/max-difference-you-can-get-from-changing-an-integer/
 */
public class Code1432_MaxDifferenceYouCanGetFromChangingAnInteger {

    /**
     * 计算一个整数num的最大可能值和最小可能值之间的差
     * 最大可能值是通过将num中的一个数字替换为'9'来获得的
     * 最小可能值是通过将num中的一个数字替换为'0'或'1'来获得的，具体取决于num的首位数字是否为1
     *
     * @param num 输入的整数
     * @return 最大可能值与最小可能值之间的差
     */
    public int maxDiff(int num) {
        // 将整数转换为字符串，以便进行字符操作
        String str = String.valueOf(num);
        // 获取数字的长度
        int n = str.length();
        // 初始化用于存储最大值和最小值的字符数组
        char[] maxChars = new char[n];
        char[] minChars = new char[n];
        // 初始化需要替换的最小字符和最大字符为数字的首位字符
        char replaceMinChar = str.charAt(0);
        char replaceMaxChar = str.charAt(0);

        // 寻找需要替换的最大字符，从第二位开始直到找到不是'9'的字符
        for (int i = 1; replaceMaxChar == '9' && i < n; i++) {
            replaceMaxChar = str.charAt(i);
        }

        // 判断数字是否以1开头，以便决定最小值的替换规则
        boolean startsWithOne = false;
        boolean canReplaceWithZero = true;

        if (replaceMinChar == '1') {
            startsWithOne = true;
            // 如果首位是1，寻找可以替换的最小字符，优先替换为0
            for (int i = 1; (replaceMinChar <= '1') && i < n; i++) {
                replaceMinChar = str.charAt(i);
            }
        }

        // 如果找到的最小字符仍然是1，那么不能替换为0
        if (replaceMinChar == '1') {
            canReplaceWithZero = false;
        }

        // 根据数字是否以1开头和是否可以替换为0，决定最小值替换的字符
        char minReplacement = startsWithOne && canReplaceWithZero ? '0' : '1';

        // 遍历每一位字符，构建最大值和最小值的字符数组
        for (int i = 0; i < n; i++) {
            char current = str.charAt(i);
            // 如果当前字符与需要替换的最大字符相同，则替换为'9'
            maxChars[i] = (current == replaceMaxChar) ? '9' : current;
            // 如果当前字符与需要替换的最小字符相同，则替换为minReplacement
            minChars[i] = (current == replaceMinChar) ? minReplacement : current;
        }

        // 将字符数组转换回整数，并计算最大值与最小值的差
        return Integer.parseInt(new String(maxChars)) - Integer.parseInt(new String(minChars));
    }


}
