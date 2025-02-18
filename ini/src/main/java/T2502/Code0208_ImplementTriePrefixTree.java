package T2502;

/**
 * @Description: 前缀树
 * @Author: iniwym
 * @Date: 2025-02-10
 * @Link: https://leetcode.cn/problems/implement-trie-prefix-tree/
 */
public class Code0208_ImplementTriePrefixTree {

    /**
     * 用this替换了root节点
     */
    class Trie {

        // 成员变量说明
        int path; // 经过该节点的路径数
        int end; // 以该节点为结尾的单词数
        Trie[] next; // 下一个节点数组，用于存储指向子节点的引用

        /**
         * 构造方法，初始化Trie节点
         */
        public Trie() {
            path = 0;
            end = 0;
            next = new Trie[26]; // 初始化next数组，大小为26，对应英文字母a-z
        }

        /**
         * 向Trie树中插入一个单词
         *
         * @param word 要插入的单词
         */
        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return; // 如果单词为空或长度为0，则不进行插入操作
            }
            Trie node = this; // 从根节点开始插入
            char[] chars = word.toCharArray(); // 将单词转换为字符数组
            node.path++; // 经过根节点的路径数增加
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a'; // 计算字符在next数组中的索引
                if (node.next[index] == null) {
                    node.next[index] = new Trie(); // 如果当前字符对应的节点不存在，则创建新节点
                }
                node = node.next[index]; // 移动到下一个节点
                node.path++; // 经过当前节点的路径数增加
            }
            node.end++; // 单词插入完成，结束节点的end值增加
        }

        /**
         * 在Trie树中搜索一个单词
         *
         * @param word 要搜索的单词
         * @return 如果找到单词且它是完整单词（即end!=0），则返回true，否则返回false
         */
        public boolean search(String word) {
            if (word == null || word.length() == 0) {
                return false; // 如果单词为空或长度为0，则返回false
            }
            Trie node = this; // 从根节点开始搜索
            char[] chars = word.toCharArray(); // 将单词转换为字符数组
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a'; // 计算字符在next数组中的索引
                if (node.next[index] == null) {
                    return false; // 如果当前字符对应的节点不存在，则单词不存在于Trie树中
                }
                node = node.next[index]; // 移动到下一个节点
            }
            return node.end != 0; // 判断单词是否为完整单词
        }

        /**
         * 检查Trie树中是否有以给定前缀开头的单词
         *
         * @param prefix 前缀字符串
         * @return 如果有以给定前缀开头的单词，则返回true，否则返回false
         */
        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.length() == 0) {
                return false; // 如果前缀为空或长度为0，则返回false
            }
            Trie node = this; // 从根节点开始检查
            char[] chars = prefix.toCharArray(); // 将前缀转换为字符数组
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a'; // 计算字符在next数组中的索引
                if (node.next[index] == null) {
                    return false; // 如果当前字符对应的节点不存在，则前缀不存在于Trie树中
                }
                node = node.next[index]; // 移动到下一个节点
            }
            return node.path > 0; // 判断是否有以给定前缀开头的单词
        }
    }

}

