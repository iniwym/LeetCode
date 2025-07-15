package T2507;

/**
 * @Description: 遍历字符串
 * @Author: iniwym
 * @Date: 2025-07-15
 * @Link: https://leetcode.cn/problems/valid-word/
 */
public class Code3136_ValidWord {

    /**
     * 检查给定的字符串是否为有效单词。
     * 有效单词需要满足以下条件：
     * 1. 长度至少为3
     * 2. 不包含特殊字符（@、#、$）
     * 3. 至少包含一个元音字母（a,e,i,o,u，不区分大小写）
     * 4. 至少包含一个非数字的辅音字母（即字母但非元音）
     *
     * @param word 待检查的字符串
     * @return 如果字符串满足所有条件则返回true，否则返回false
     */
    public boolean isValid(String word) {
        char[] chars = word.toCharArray();
        int length = chars.length;
        boolean temp1 = false; // 标记是否包含元音字母
        boolean temp2 = false; // 标记是否包含非数字的辅音字母

        // 检查长度是否满足最低要求
        if (length < 3) {
            return false;
        }

        // 遍历每个字符进行检查
        for (int i = 0; i < length; i++) {
            char c = chars[i];

            // 检查是否包含不允许的特殊字符
            if ('@' == c || '#' == c || '$' == c) {
                return false;
            }

            // 检查是否为元音字母（大小写不敏感）
            if ('a' == c || 'e' == c || 'i' == c || 'o' == c || 'u' == c
                    || 'A' == c || 'E' == c || 'I' == c || 'O' == c || 'U' == c) {
                temp1 = true;
            }
            // 检查是否为非数字的辅音字母
            else if (c < '0' || c > '9') {
                temp2 = true;
            }
        }

        // 必须同时满足包含元音字母和辅音字母的条件
        return temp1 && temp2;
    }

}
