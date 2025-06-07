package T2506;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 栈+位运算
 * @Author: iniwym
 * @Date: 2025-06-07
 * @Link: https://leetcode.cn/problems/lexicographically-minimum-string-after-removing-stars/
 */
public class Code3170_LexicographicallyMinimumStringAfterRemovingStars {
    /**
     * 清除字符串中的星号字符，并返回清除后的字符串
     * 该方法通过维护一个字符栈来跟踪非星号字符，当遇到星号时，移除最近的非星号字符
     *
     * @param s 输入的字符串，可能包含星号(*)和其他小写字母
     * @return 清除星号后的字符串
     */
    public String clearStars(String s) {
        // 将输入字符串转换为字符数组，便于逐个处理字符
        char[] chars = s.toCharArray();

        // 创建一个包含26个列表的数组，用于分别存储每个字母的位置
        List<Integer>[] stack = new ArrayList[26];
        // 初始化数组中的每个列表
        Arrays.setAll(stack, i -> new ArrayList<>());

        // 遍历字符数组
        for (int i = 0; i < chars.length; i++) {
            // 如果当前字符不是星号，将其位置添加到对应的字符列表中
            if (chars[i] != '*') {
                stack[chars[i] - 'a'].add(i);
                continue;
            }

            // 如果当前字符是星号，移除最近的一个非星号字符的位置
            for (List<Integer> st : stack) {
                if (!st.isEmpty()) {
                    st.remove(st.size() - 1);
                    break;
                }
            }
        }

        // 创建一个列表，用于存储所有非星号字符的位置
        List<Integer> index = new ArrayList<>();
        // 将所有非星号字符的位置添加到列表中
        for (List<Integer> st : stack) {
            index.addAll(st);
        }

        // 对位置列表进行排序，以确保字符的顺序正确
        Collections.sort(index);

        // 创建一个字符串构建器，用于构造最终的字符串
        StringBuilder ans = new StringBuilder(index.size());
        // 根据位置列表中的位置，将对应的字符添加到字符串构建器中
        for (int i : index) {
            ans.append(chars[i]);
        }

        // 返回最终构造的字符串
        return ans.toString();
    }
}
