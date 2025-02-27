package T2502;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: 双栈实现
 * @Author: iniwym
 * @Date: 2025-02-27
 * @Link: https://leetcode.cn/problems/design-a-text-editor/
 */
public class Code2296_DesignATextEditor {

    /**
     * 文本编辑器类，模拟文本编辑器的部分功能，如添加文本、删除文本和光标移动
     */
    public static class TextEditor {

        // 左侧栈，用于存储光标左侧的文本
        Deque<String> leftStack;
        // 右侧栈，用于存储光标右侧的文本
        Deque<String> rightStack;

        /**
         * 构造函数，初始化左右栈
         */
        public TextEditor() {
            leftStack = new ArrayDeque<>();
            rightStack = new ArrayDeque<>();
        }

        /**
         * 在光标所在位置添加文本
         *
         * @param text 要添加的文本
         */
        public void addText(String text) {
            char[] chars = text.toCharArray();
            for (char c : chars) {
                leftStack.push(String.valueOf(c));
            }
        }

        /**
         * 删除光标左侧的文本
         *
         * @param k 要删除的字符数
         * @return 实际删除的字符数
         */
        public int deleteText(int k) {
            int count = Math.min(leftStack.size(), k);
            for (int i = 0; i < count; i++) {
                leftStack.pop();
            }
            return count;
        }

        /**
         * 将光标向左移动，从左侧栈移动字符到右侧栈
         *
         * @param k 光标要移动的字符数
         * @return 移动后光标左侧的文本
         */
        public String cursorLeft(int k) {
            int count = Math.min(leftStack.size(), k);
            for (int i = 0; i < count; i++) {
                rightStack.push(leftStack.pop());
            }

            return getLeftString();
        }

        /**
         * 获取光标左侧的文本，用于显示
         *
         * @return 光标左侧的文本
         */
        private String getLeftString() {
            StringBuilder ssb = new StringBuilder("");
            int ans = Math.min(leftStack.size(), 10);

            for (int i = 0; i < ans; i++) {
                rightStack.push(leftStack.pop());
            }
            for (int i = 0; i < ans; i++) {
                ssb.append(rightStack.peek());
                leftStack.push(rightStack.pop());
            }

            return ssb.toString();
        }

        /**
         * 将光标向右移动，从右侧栈移动字符到左侧栈
         *
         * @param k 光标要移动的字符数
         * @return 移动后光标左侧的文本
         */
        public String cursorRight(int k) {
            int count = Math.min(rightStack.size(), k);
            for (int i = 0; i < count; i++) {
                leftStack.push(rightStack.pop());
            }
            return getLeftString();
        }


    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(); // 当前 text 为 "|" 。（'|' 字符表示光标）
        textEditor.addText("leetcode"); // 当前文本为 "leetcode|" 。
        System.out.println(textEditor.deleteText(4)); // 返回 4
        // 当前文本为 "leet|" 。
        // 删除了 4 个字符。
        textEditor.addText("practice"); // 当前文本为 "leetpractice|" 。
        System.out.println(textEditor.cursorRight(3)); // 返回 "etpractice"
        // 当前文本为 "leetpractice|".
        // 光标无法移动到文本以外，所以无法移动。
        // "etpractice" 是光标左边的 10 个字符。
        System.out.println(textEditor.cursorLeft(8)); // 返回 "leet"
        // 当前文本为 "leet|practice" 。
        // "leet" 是光标左边的 min(10, 4) = 4 个字符。
        System.out.println(textEditor.deleteText(10)); // 返回 4
        // 当前文本为 "|practice" 。
        // 只有 4 个字符被删除了。
        System.out.println(textEditor.cursorLeft(2)); // 返回 ""
        // 当前文本为 "|practice" 。
        // 光标无法移动到文本以外，所以无法移动。
        // "" 是光标左边的 min(10, 0) = 0 个字符。
        System.out.println(textEditor.cursorRight(6)); // 返回 "practi"
        // 当前文本为 "practi|ce" 。
        // "practi" 是光标左边的 min(10, 6) = 6 个字符。
    }
}
