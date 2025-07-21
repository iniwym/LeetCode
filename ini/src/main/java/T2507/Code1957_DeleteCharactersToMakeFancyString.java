package T2507;

/**
 * @Description: 字符串遍历
 * @Author: iniwym
 * @Date: 2025-07-21
 * @Link: https://leetcode.cn/problems/delete-characters-to-make-fancy-string/
 */
public class Code1957_DeleteCharactersToMakeFancyString {

    /**
     * 生成一个"fancy"字符串，即去除字符串中连续出现3次及以上的相同字符
     *
     * @param s 输入字符串，可能包含任意字符
     * @return 处理后的字符串，保证不会出现连续3个及以上相同字符
     */
    public String makeFancyString(String s) {
        int n = s.length();

        // 长度小于3直接返回，无需处理
        if (n < 3) {
            return s;
        }

        // 将字符串转为字符数组处理
        char[] chars = s.toCharArray();
        char[] ans = new char[n];
        int temp = 0; // 记录结果数组的当前位置

        // 遍历输入字符串的每个字符
        for (int i = 0; i < n; i++) {
            // 前两个字符直接放入结果数组
            if (i < 2) {
                ans[temp++] = chars[i];
            }
            // 检查当前字符是否与前两个字符不同（避免连续3个相同）
            else if (ans[temp - 2] != chars[i] || ans[temp - 1] != chars[i]) {
                ans[temp++] = chars[i];
            }
        }

        // 将有效部分的字符数组转为字符串返回
        return String.valueOf(ans, 0, temp);
    }


}
