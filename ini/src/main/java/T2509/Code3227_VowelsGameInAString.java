package T2509;

/**
 * @Description: 字符串
 * @Author: iniwym
 * @Date: 2025-09-12
 * @Link: https://leetcode.cn/problems/vowels-game-in-a-string/
 */
public class Code3227_VowelsGameInAString {

    /**
     * 判断Alice是否能赢得游戏
     *
     * @param s 输入的字符串
     * @return 如果字符串中包含元音字母则返回true，否则返回false
     */
    public boolean doesAliceWin(String s) {
        // 遍历字符串中的每个字符，检查是否包含元音字母
        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }
        }
        return false;
    }


}
