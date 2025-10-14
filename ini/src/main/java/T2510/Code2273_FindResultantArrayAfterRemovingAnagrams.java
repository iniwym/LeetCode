package T2510;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 去重
 * @Author: iniwym
 * @Date: 2025-10-14
 * @Link: https://leetcode.cn/problems/find-resultant-array-after-removing-anagrams/
 */
public class Code2273_FindResultantArrayAfterRemovingAnagrams {

    /**
     * 从字符串数组中移除相邻的字母异位词，只保留每组连续字母异位词中的第一个单词
     *
     * @param words 输入的字符串数组，每个元素都是由小写字母组成的单词
     * @return 移除相邻字母异位词后的字符串列表
     */
    public List<String> removeAnagrams(String[] words) {
        // 对第一个单词进行排序，作为基准比较对象
        char[] base = words[0].toCharArray();
        Arrays.sort(base);
        int k = 1;

        // 遍历剩余单词，检查是否与前一个非异位词相同
        for (int i = 1; i < words.length; i++) {
            char[] s = words[i].toCharArray();
            Arrays.sort(s);

            // 如果当前单词排序后与基准不同，则保留该单词并更新基准
            if (!Arrays.equals(s, base)) {
                base = s;
                words[k++] = words[i]; // 保留 words[i]
            }
        }

        // 返回处理后的结果列表
        return Arrays.asList(Arrays.copyOf(words, k));
    }

}
