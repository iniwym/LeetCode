package T2505;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 数组、集合
 * @Author: iniwym
 * @Date: 2025-05-15
 * @Link: https://leetcode.cn/problems/longest-unequal-adjacent-groups-subsequence-i/
 */
public class Code2900_LongestUnequalAdjacentGroupsSubsequenceI {

    /**
     * 根据一组单词和对应的组号数组，找出最长的不重复组号的子序列
     * 此方法的目的是为了演示如何通过一次遍历，使用临时变量来追踪当前组号，
     * 从而有效地筛选出不重复的组号对应的单词序列
     *
     * @param words  单词数组，每个单词对应一个字符串
     * @param groups 组号数组，每个元素代表对应单词的组号
     * @return 返回一个字符串列表，包含按顺序筛选出的不重复组号对应的单词
     */
    public List<String> getLongestSubsequence(String[] words, int[] groups) {

        // 初始化列表以存储筛选后的单词序列
        List<String> list = new ArrayList<>();

        // 将第一个单词添加到列表中，因为筛选是从第二个单词开始
        list.add(words[0]);
        // 用临时变量temp存储当前的组号，初始值为第一个组号
        int temp = groups[0];
        // 从第二个元素开始遍历组号数组
        for (int i = 1; i < groups.length; i++) {
            // 如果当前组号与前一个组号不同，则将对应的单词添加到列表中，并更新当前组号
            if (temp != groups[i]) {
                list.add(words[i]);
                temp = groups[i];
            }

        }

        // 返回筛选后的单词列表
        return list;

    }

}
