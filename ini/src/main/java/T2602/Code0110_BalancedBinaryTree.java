package T2602;

/**
 * @Description: 平衡二叉树
 * @Author: iniwym
 * @Date: 2026-02-08
 * @Link: https://leetcode.cn/problems/balanced-binary-tree/
 */
public class Code0110_BalancedBinaryTree {

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

    /**
     * 判断给定的二叉树是否为平衡二叉树。
     * 平衡二叉树的定义是：任意节点的左右子树高度差不超过1。
     *
     * @param root 二叉树的根节点
     * @return 如果是平衡二叉树返回true，否则返回false
     */
    public boolean isBalanced(TreeNode root) {
        // 处理空树的情况
        if (root == null) {
            return true;
        }

        // 使用后序遍历递归判断是否为平衡二叉树
        return getHeight(root) != -1;
    }

    /**
     * 辅助方法：计算树的高度，若不平衡则返回 -1。
     * 通过递归方式计算每个节点的左右子树高度，并判断是否满足平衡条件。
     *
     * @param node 当前节点
     * @return 如果以当前节点为根的子树是平衡的，返回其高度；否则返回 -1
     */
    private int getHeight(TreeNode node) {
        // 空节点高度为 0
        if (node == null) {
            return 0;
        }

        // 递归获取左子树高度
        int leftHeight = getHeight(node.left);
        if (leftHeight == -1) {
            return -1; // 左子树不平衡，直接返回 -1
        }

        // 递归获取右子树高度
        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) {
            return -1; // 右子树不平衡，直接返回 -1
        }

        // 判断当前节点是否平衡
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // 当前节点不平衡
        }

        // 返回当前节点的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }


}
