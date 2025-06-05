package T2506;

/**
 * @Description: 并查集
 * @Author: iniwym
 * @Date: 2025-06-05
 * @Link: https://leetcode.cn/problems/lexicographically-smallest-equivalent-string/
 */
public class Code1061_LexicographicallySmallestEquivalentString {
    /**
     * 根据两个字符串s1和s2以及一个基础字符串baseStr，找到baseStr中每个字符的最小等价字符
     * 等价字符的定义是：如果s1[i]和s2[i]是等价的，则它们可以互换
     * 此方法使用并查集来跟踪字符之间的等价关系，并构建最小等价字符串
     *
     * @param s1      第一个字符串，与s2配对定义字符间的等价关系
     * @param s2      第二个字符串，与s1配对定义字符间的等价关系
     * @param baseStr 基础字符串，需要找到其等价字符串
     * @return baseStr的最小等价字符串
     */
    public String smallestEquivalentString(String s1, String s2, String baseStr) {

        // 初始化并查集数组，代表26个英文字母
        int[] nums = new int[26];

        // 初始化每个字母的父节点为其自身
        for (int i = 0; i < 26; i++) {
            nums[i] = i;
        }

        // 遍历s1和s2，合并等价字符
        for (int i = 0; i < s1.length(); i++) {
            merge(nums, s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        // 将baseStr转换为字符数组，便于逐个字符处理
        char[] chars = baseStr.toCharArray();
        // 遍历字符数组，找到每个字符的最小等价字符
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (find(nums, chars[i] - 'a') + 'a');
        }

        // 根据处理后的字符数组构建并返回最小等价字符串
        return new String(chars);
    }

    /**
     * 并查集的合并操作，用于合并两个等价字符
     *
     * @param nums 并查集数组
     * @param x    第一个字符的索引
     * @param y    第二个字符的索引
     */
    private void merge(int[] nums, int x, int y) {
        int rootX = find(nums, x);
        int rootY = find(nums, y);
        if (rootX != rootY) {
            // 将较小的根节点作为新的根节点
            if (rootX < rootY) {
                nums[rootY] = rootX;
            } else {
                nums[rootX] = rootY;
            }
        }
    }

    /**
     * 并查集的查找操作，用于找到字符的最小等价字符
     *
     * @param nums 并查集数组
     * @param i    字符的索引
     * @return 字符的最小等价字符的索引
     */
    private int find(int[] nums, int i) {
        if (nums[i] != i) {
            nums[i] = find(nums, nums[i]);
        }
        return nums[i];
    }

}
