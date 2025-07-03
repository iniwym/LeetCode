package T2507;

/**
 * @Description: 暴力枚举
 * @Author: iniwym
 * @Date: 2025-07-03
 * @Link: https://leetcode.cn/problems/find-the-k-th-character-in-string-game-i/
 */
public class Code3304_FindTheKThCharacterInStringGameI {

    public char kthCharacter(int k) {
        String str = "abbcbccdbccdcddebccdcddecddedeefbccdcddecddedeefcddedeefdeefeffgbccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfgghbccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfgghcddedeefdeefeffgdeefeffgeffgfgghdeefeffgeffgfggheffgfgghfgghghhibccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfgghcddedeefdeefeffgdeefeffgeffgfgghdeefeffgeffgfggheffgfgghfgghghhicddedeefdeefeffgdeefeffgeffgfgghdeefeffgeffgfggheffgfgghfgghghhideefeffgeffgfggheffgfgghfgghghhieffgfgghfgghghhifgghghhighhihiij";
        return str.charAt(k - 1);
    }

    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "ab";
        String str4 = "abbc";
        String str8 = "abbcbccd";
        String str16 = "abbcbccdbccdcdde";
        String str32 = "abbcbccdbccdcddebccdcddecddedeef";
        String str64 = "abbcbccdbccdcddebccdcddecddedeefbccdcddecddedeefcddedeefdeefeffg";
        String str128 = "abbcbccdbccdcddebccdcddecddedeefbccdcddecddedeefcddedeefdeefeffgbccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfggh";
        String str256 = "abbcbccdbccdcddebccdcddecddedeefbccdcddecddedeefcddedeefdeefeffgbccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfgghbccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfgghcddedeefdeefeffgdeefeffgeffgfgghdeefeffgeffgfggheffgfgghfgghghhi";
        String str = "abbcbccdbccdcddebccdcddecddedeefbccdcddecddedeefcddedeefdeefeffgbccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfgghbccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfgghcddedeefdeefeffgdeefeffgeffgfgghdeefeffgeffgfggheffgfgghfgghghhibccdcddecddedeefcddedeefdeefeffgcddedeefdeefeffgdeefeffgeffgfgghcddedeefdeefeffgdeefeffgeffgfgghdeefeffgeffgfggheffgfgghfgghghhicddedeefdeefeffgdeefeffgeffgfgghdeefeffgeffgfggheffgfgghfgghghhideefeffgeffgfggheffgfgghfgghghhieffgfgghfgghghhifgghghhighhihiij";

        char[] chars0 = str.toCharArray();
        int length = chars0.length;
        char[] chars1 = new char[length];

        for (int i = 0; i < length; i++) {
            chars1[i] = (char) ('a' + (chars0[i] - 'a' + 1) % 26);
        }

        System.out.println(String.valueOf(chars0) + String.valueOf(chars1));

    }
}
