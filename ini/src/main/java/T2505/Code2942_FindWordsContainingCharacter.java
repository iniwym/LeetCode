package T2505;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 字符串
 * @Author: iniwym
 * @Date: 2025-05-24
 * @Link: https://leetcode.cn/problems/find-words-containing-character/
 */
public class Code2942_FindWordsContainingCharacter {

    /**
     * 查找包含特定字符的单词索引
     * <p>
     * 此方法旨在接收一个字符串数组和一个字符作为参数，返回一个列表，其中包含所有包含该特定字符的单词的索引
     * 这在需要快速定位哪些单词包含某个字符时特别有用
     *
     * @param words 字符串数组，代表待检查的单词列表
     * @param x     需要检查的字符
     * @return 包含特定字符的单词的索引列表
     */
    public List<Integer> findWordsContaining(String[] words, char x) {

        // 创建一个列表，用于存储包含特定字符的单词的索引
        List<Integer> list = new ArrayList();

        // 遍历单词数组，检查每个单词是否包含特定字符
        for (int i = 0; i < words.length; i++) {
            // 如果当前单词包含特定字符，则将其索引添加到列表中
            if (words[i].contains(x + "")) {
                list.add(i);
            }
        }

        // 返回包含特定字符的单词的索引列表
        return list;
    }

}
