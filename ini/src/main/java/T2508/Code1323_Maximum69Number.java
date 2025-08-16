package T2508;

/**
 * @Description: 遍历
 * @Author: iniwym
 * @Date: 2025-08-16
 * @Link: https://leetcode.cn/problems/maximum-69-number/
 */
public class Code1323_Maximum69Number {

    /**
     * 将数字中的第一个6替换为9，以获得最大可能的数字
     *
     * @param num 输入的数字，只包含数字6和9
     * @return 将第一个6替换为9后的最大数字，如果没有6则返回原数字
     */
    public int maximum69Number(int num) {
        String s = String.valueOf(num);
        char[] chars = s.toCharArray();
        // 遍历字符数组，找到第一个'6'并将其替换为'9'
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '6') {
                chars[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(chars));

    }

}
