package T2506;

/**
 * @Description: 字典树
 * @Author: iniwym
 * @Date: 2025-06-09
 * @Link: https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/
 */
public class Code0440_KThSmallestInLexicographicalOrder {

    /**
     * 寻找按照字典序排列的第 k 小的数字
     *
     * @param n 表示范围的上限，即数字的最大值
     * @param k 表示目标是找到第 k 小的数字
     * @return 返回第 k 小的数字
     */
    public int findKthNumber(int n, int k) {
        int node = 1;
        k--; // 访问节点 node
        while (k > 0) {
            int size = countSubtreeSize(n, node);
            if (size <= k) { // 向右，跳过 node 子树
                node++; // 访问 node 右侧兄弟节点
                k -= size; // 访问子树中的每个节点，以及新的 node 节点
            } else { // 向下，深入 node 子树
                node *= 10; // 访问 node 的第一个儿子
                k--; // 访问新的 node 节点
            }
        }
        return node;
    }

    /**
     * 计算给定节点的子树大小。
     * 该方法通过遍历树的每一层，累加每一层的节点数来计算子树的总大小。
     * 由于子树的大小受到给定的n的限制，因此在计算过程中需要考虑边界条件。
     *
     * @param n    树的总大小，用于限制子树的边界。
     * @param node 子树的根节点，从该节点开始计算子树大小。
     * @return 返回子树的总大小。
     */
    private int countSubtreeSize(int n, int node) {
        // 子树大小不会超过 n，所以 size 用 int 类型
        // 但计算过程中的 left 和 right 会超过 int，所以用 long 类型
        int size = 0;
        long left = node;
        long right = node + 1;
        while (left <= n) {
            // 这一层的最小值是 left，最大值是 min(right, n + 1) - 1
            size += Math.min(right, n + 1) - left;
            left *= 10; // 继续，计算下一层
            right *= 10;
        }
        return size;
    }

}
