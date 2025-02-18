package T2502;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @Description: bfs
 * @Author: iniwym
 * @Date: 2025-02-12
 * @Link: https://leetcode.cn/problems/stickers-to-spell-word/
 */
public class Code0691_StickersToSpellWord {

    public static int MAX = 500;

    // 创建一个队列
    public static String[] queue = new String[MAX];

    public static int l, r;

    public static HashSet<String> visited = new HashSet<>();

    public static ArrayList<ArrayList<String>> graph = new ArrayList<>();

    static {
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<String>());
        }
    }

    /**
     * 计算组成目标字符串所需的最小贴纸数量
     *
     * @param stickers 贴纸字符串数组，每个字符串代表一个贴纸，贴纸上可能有重复的字符
     * @param target   目标字符串，需要使用贴纸上的字符来组成这个字符串
     * @return 返回组成目标字符串所需的最小贴纸数量如果无法组成目标字符串，则返回-1
     */
    public static int minStickers(String[] stickers, String target) {
        // 初始化贴纸图，清空上一次使用后的数据
        for (int i = 0; i < 26; i++) {
            graph.get(i).clear();
        }
        visited.clear();

        // 将贴纸添加到图中，以便快速查找
        for (String str : stickers) {
            str = sort(str);
            for (int i = 0; i < str.length(); i++) {
                // 跳过重复的字符
                if (i == 0 || str.charAt(i) != str.charAt(i - 1)) {
                    graph.get(str.charAt(i) - 'a').add(str);
                }
            }
        }

        // 初始化队列和访问记录
        l = r = 0;
        target = sort(target);
        visited.add(target);
        queue[r++] = target;
        int level = 1;

        // 使用广度优先搜索遍历图
        while (l < r) {
            int num = r - l;
            for (int i = 0; i < num; i++) {
                String cur = queue[l++];
                for (String s : graph.get(cur.charAt(0) - 'a')) {
                    // 如果next为空字符串，则说明已经找到一种组合，返回当前层数作为结果
                    String next = next(cur, s);
                    if ("".equals(next)) {
                        return level;
                    } else if (!visited.contains(next)) {
                        visited.add(next);
                        queue[r++] = next;
                    }
                }
            }
            level++;
        }
        return -1;
    }

    /**
     * 根据字符串s对字符串t进行特殊排序
     * 该方法会按照字符串s中的字符顺序，对字符串t中的字符进行排序如果t中某个字符在s中不存在，
     * 则该字符在结果字符串中的位置保持不变
     *
     * @param t 输入的待排序字符串
     * @param s 用于定义排序规则的字符串
     * @return 根据s的顺序特殊排序后的t字符串
     */
    public static String next(String t, String s) {
        // 使用StringBuilder来构建最终的排序字符串
        StringBuilder builder = new StringBuilder();
        // 初始化两个指针i和j分别遍历字符串t和s
        for (int i = 0, j = 0; i < t.length(); ) {
            // 如果j达到s的长度，说明s中所有字符都已经处理完毕，直接将t中剩余字符添加到结果中
            if (j == s.length()) {
                builder.append(t.charAt(i++));
            } else {
                // 比较t和s当前指针位置的字符
                if (t.charAt(i) < s.charAt(j)) {
                    // 如果t中的字符小于s中的字符，将t中的字符添加到结果中
                    builder.append(t.charAt(i++));
                } else if (t.charAt(i) > s.charAt(j)) {
                    // 如果t中的字符大于s中的字符，继续遍历s中的下一个字符
                    j++;
                } else {
                    // 如果t中的字符等于s中的字符，说明找到了匹配的排序规则，同时移动两个指针
                    i++;
                    j++;
                }
            }
        }
        // 返回构建好的排序字符串
        return builder.toString();
    }

    /**
     * 实现字符串排序
     */
    public static String sort(String str) {
        char[] s = str.toCharArray();
        Arrays.sort(s);
        return String.valueOf(s);
    }
}
