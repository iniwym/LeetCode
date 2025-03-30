package T2503;

/**
 * @Description: 字符串分割和拼接
 * @Author: iniwym
 * @Date: 2025-03-30
 * @Link: https://leetcode.cn/problems/adding-spaces-to-a-string/
 */
public class Code2109_AddingSpacesToAString {
    /**
     * 在字符串指定位置插入空格。
     *
     * @param s      输入的原始字符串
     * @param spaces 需要插入空格的位置数组，元素按升序排列且不超过字符串长度
     * @return 插入空格后的字符串
     */
    public static String addSpaces(String s, int[] spaces) {
        int n = spaces.length;
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, spaces[0])).append(" ");

        // 遍历每个间隔点，将对应子字符串与空格追加到结果中
        for (int i = 1; i < n; i++) {
            int start = spaces[i - 1];
            int end = spaces[i];
            sb.append(s.substring(start, end)).append(" ");
        }

        // 添加最后一个插入点之后的剩余字符串
        sb.append(s.substring(spaces[n - 1]));
        return sb.toString();
    }

}
