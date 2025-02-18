/**
 * @Description: 根据网站生成文件名
 * @Author: iniwym
 * @Date: 2025-02-12
 */
public class StringConverter {
    public static String convert(String code, String input) {
        // 补齐代码编号为 4 位
        code = String.format("%04d", Integer.parseInt(code));

        // 定位 "problems" 字符串的位置
        int problemsIndex = input.indexOf("/problems/");
        if (problemsIndex == -1) {
            // 如果未找到 "problems"，直接返回空字符串或进行其他错误处理
            return "";
        }
        // 提取 "problems" 之后的部分
        String problemPart = input.substring(problemsIndex + "/problems/".length());
        // 找到下一个斜杠的位置
        int nextSlashIndex = problemPart.indexOf("/");
        if (nextSlashIndex != -1) {
            // 截取到下一个斜杠之前的部分
            problemPart = problemPart.substring(0, nextSlashIndex);
        }

        StringBuilder result = new StringBuilder("Code").append(code).append("_");
        boolean capitalizeNext = true;

        for (int i = 0; i < problemPart.length(); i++) {
            char c = problemPart.charAt(i);
            if (c == '-') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String code = "2080";
        String input = "https://leetcode.cn/problems/range-frequency-queries/";
        String output = convert(code, input);
        System.out.println(output);
    }

}