package T2506;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: 栈 + 贪心
 * @Author: iniwym
 * @Date: 2025-06-06
 * @Link: https://leetcode.cn/problems/using-a-robot-to-print-the-lexicographically-smallest-string/
 */
public class Code2434_UsingARobotToPrintTheLexicographicallySmallestString {

    /**
     * 根据字符串s模拟机器人排序字符串的过程
     * 机器人会从左到右读取字符串s的字符，并按照特定规则排序这些字符
     *
     * @param s 输入的字符串
     * @return 排序后的字符串
     */
    public String robotWithString(String s) {
        // 字符串长度
        int n = s.length();
        // 创建一个字符数组，用于存储到当前位置的最小字符
        char[] chars = new char[n + 1];
        // 设置最后一个位置为最大字符值，作为边界条件
        chars[n] = Character.MAX_VALUE;

        // 从后向前遍历字符串s，更新chars数组中的最小字符值
        for (int i = n - 1; i >= 0; i--) {
            // 当前位置的字符值取当前字符和后一个字符的较小值
            chars[i] = (char) (Math.min(chars[i + 1], s.charAt(i)));
        }

        // 创建StringBuilder用于构建最终的排序字符串
        StringBuilder ans = new StringBuilder(n);
        // 使用Deque作为辅助数据结构，模拟机器人的排序过程
        Deque<Character> queue = new ArrayDeque<>();
        // 遍历字符串s的每个字符
        for (int i = 0; i < n; i++) {
            // 将当前字符压入队列
            queue.push(s.charAt(i));
            // 当队列不为空且队列顶部元素不大于当前最小字符时，将顶部元素弹出并添加到结果字符串中
            while (!queue.isEmpty() && queue.peek() <= chars[i + 1]) {
                ans.append(queue.pop());
            }
        }

        // 返回构建好的排序字符串
        return ans.toString();
    }

}
