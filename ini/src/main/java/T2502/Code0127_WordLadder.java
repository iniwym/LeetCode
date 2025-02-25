package T2502;

import java.util.*;

/**
 * @Description: 双向广搜
 * @Author: iniwym
 * @Date: 2025-02-18
 * @Link: https://leetcode.cn/problems/word-ladder/
 */
public class Code0127_WordLadder {


    /**
     * 计算从 beginWord 到 endWord 的最短转换序列长度
     * 转换规则是每次变更一个字母，且变更后的单词必须在 wordList 中
     *
     * @param beginWord 起始单词
     * @param endWord   目标单词
     * @param wordList  单词列表
     * @return 如果可以找到这样的转换序列，则返回最短转换序列的长度；否则返回 0
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {

        // 将 wordList 转换为 HashSet 以提高查找效率
        Set<String> dict = new HashSet<>(wordList);

        // 检查 endWord 是否存在于 wordList 中
        if (!dict.contains(endWord)) {
            return 0;
        }

        // 初始化两个哈希集合，用于双向搜索
        HashSet<String> smallLevel = new HashSet<>();
        HashSet<String> bigLevel = new HashSet<>();
        HashSet<String> nextLevel = new HashSet<>();

        // 将起始单词和目标单词分别加入到两个集合中
        smallLevel.add(beginWord);
        bigLevel.add(endWord);

        // 从长度为 2 开始，因为起始单词和目标单词各占一位
        for (int len = 2; !smallLevel.isEmpty(); len++) {
            // 遍历当前层的所有单词
            for (String str : smallLevel) {
                char[] chars = str.toCharArray();
                // 遍历单词的每个字母
                for (int i = 0; i < chars.length; i++) {
                    char aChar = chars[i];
                    // 尝试用 'a' 到 'z' 的每个字母替换当前字母
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != aChar) {
                            chars[i] = c;
                            String next = String.valueOf(chars);
                            // 如果替换后的单词在另一层中找到，则说明找到了最短路径
                            if (bigLevel.contains(next)) {
                                return len;
                            }
                            // 如果替换后的单词在字典中找到，则将其添加到下一层，并从字典中移除
                            if (dict.contains(next)) {
                                dict.remove(next);
                                nextLevel.add(next);
                            }
                        }
                    }
                    // 恢复原始字母，以准备下一次替换
                    chars[i] = aChar;
                }
            }
            // 根据下一层的大小，决定是交换 smallLevel 还是 bigLevel，以优化搜索
            if (nextLevel.size() <= bigLevel.size()) {
                HashSet<String> tmp = smallLevel;
                smallLevel = nextLevel;
                nextLevel = tmp;
            } else {
                HashSet<String> tmp = smallLevel;
                smallLevel = bigLevel;
                bigLevel = nextLevel;
                nextLevel = tmp;
            }
            // 清空下一层，为下一轮迭代做准备
            nextLevel.clear();
        }

        // 如果没有找到转换序列，则返回 0
        return 0;
    }

    /**
     * 计算从 beginWord 到 endWord 的最短转换序列长度。
     *
     * @param beginWord 开始单词
     * @param endWord   结束单词
     * @param wordList  单词列表
     * @return 最短转换序列长度，如果无法转换则返回 0
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 输入参数有效性检查
        if (beginWord == null || endWord == null || wordList == null || beginWord.length() != endWord.length()) {
            return 0;
        }

        // 将 wordList 转换为 HashSet 以提高查找效率
        Set<String> dict = new HashSet<>(wordList);

        // 检查 endWord 是否存在于 wordList 中
        if (!dict.contains(endWord)) {
            return 0;
        }

        // 初始化双向 BFS 的起点和终点集合
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        // 初始化已访问集合
        Set<String> visited = new HashSet<>();

        startSet.add(beginWord);
        endSet.add(endWord);

        // 初始化转换序列长度
        int len = 1;

        // 当起点和终点集合都不为空时，执行 BFS
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            // 总是扩展较小的集合以减少搜索空间
            if (startSet.size() > endSet.size()) {
                Set<String> temp = startSet;
                startSet = endSet;
                endSet = temp;
            }

            // 用于存储下一层的单词集合
            Set<String> nextLevel = new HashSet<>();
            // 遍历当前层的所有单词
            for (String word : startSet) {
                char[] chars = word.toCharArray();
                // 尝试改变单词的每一个字符
                for (int i = 0; i < chars.length; i++) {
                    char originalChar = chars[i];
                    // 尝试所有可能的字符替换
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        chars[i] = c;
                        String nextWord = new String(chars);
                        // 如果替换后的单词在终点集合中，说明找到了最短路径
                        if (endSet.contains(nextWord)) {
                            return len + 1;
                        }
                        // 如果替换后的单词在字典中且未被访问过
                        if (dict.contains(nextWord) && !visited.contains(nextWord)) {
                            nextLevel.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                    // 恢复原始字符
                    chars[i] = originalChar;
                }
            }

            // 更新起点集合为下一层的单词集合
            startSet = nextLevel;
            // 增加转换序列长度
            len++;
        }

        // 如果没有找到路径，返回 0
        return 0;
    }
}
