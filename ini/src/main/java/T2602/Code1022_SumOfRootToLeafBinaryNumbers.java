package T2602;

/**
 * @Description: 深度优先搜索
 * @Author: iniwym
 * @Date: 2026-02-24
 * @Link: https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers/
 */
public class Code1022_SumOfRootToLeafBinaryNumbers {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * 深度优先搜索（DFS）遍历二叉树，计算从根节点到叶节点路径所表示的二进制数的十进制值之和。
     *
     * @param node 当前访问的树节点
     * @param num  从根节点到当前节点路径上已构建的二进制数（以整数形式表示）
     * @return 从根节点到所有叶节点路径所表示的二进制数的十进制值之和
     */
    private int dfs(TreeNode node, int num) {
        // 如果当前节点为空，返回0（空路径不贡献值）
        if (node == null) {
            return 0;
        }

        // 将当前节点的值追加到路径的二进制数中
        num = num << 1 | node.val;

        // 如果当前节点是叶节点，返回当前路径对应的十进制值
        if (node.left == null && node.right == null) {
            return num;
        }

        // 递归处理左子树和右子树，并将结果相加
        return dfs(node.left, num) + dfs(node.right, num);
    }

}
