package T2601;

/**
 * @Description: 二叉树 递归
 * @Author: iniwym
 * @Date: 2026-01-09
 * @Link: https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes/
 */
public class Code0865_SmallestSubtreeWithAllTheDeepestNodes {


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
     * 结果类，用于封装节点的深度信息和节点对象
     */
    private class Result {
        int depth;
        TreeNode node;

        /**
         * 构造函数，初始化结果对象
         *
         * @param depth 节点的深度值
         * @param node  树节点对象
         */
        Result(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }


    /**
     * 查找包含所有最深节点的最小子树
     *
     * @param root 二叉树的根节点
     * @return 包含所有最深节点的最小子树的根节点
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    /**
     * 深度优先搜索查找最近公共祖先
     * 该方法通过递归计算子树的深度，并返回子树中最深层的公共祖先节点
     *
     * @param node 当前遍历的树节点
     * @return Result对象，包含当前子树的最大深度和对应的最近公共祖先节点
     */
    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(0, null);
        }

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        // 如果左右深度相等，当前节点就是LCA
        if (left.depth == right.depth) {
            return new Result(left.depth + 1, node);
        }
        // 左子树更深，返回左子树的结果
        else if (left.depth > right.depth) {
            return new Result(left.depth + 1, left.node);
        }
        // 右子树更深，返回右子树的结果
        else {
            return new Result(right.depth + 1, right.node);
        }
    }

}
