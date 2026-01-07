package T2601;

/**
 * @Description: 二叉树
 * @Author: iniwym
 * @Date: 2026-01-07
 * @Link: https://leetcode.cn/problems/maximum-product-of-splitted-binary-tree/
 */
public class Code1339_MaximumProductOfSplittedBinaryTree {

    private long total = 0;
    private long maxProduct = 0;
    private final int MOD = 1_000_000_007;

    /**
     * 计算二叉树分割后两个子树节点和的乘积的最大值
     *
     * @param root 二叉树的根节点
     * @return 分割后两个子树节点和乘积的最大值，对10^9+7取模
     */
    public int maxProduct(TreeNode root) {
        // 第一次遍历：计算整棵树的总和
        total = getSum(root);

        // 第二次遍历：计算每个子树的乘积并更新最大值
        calculateMaxProduct(root);

        return (int) (maxProduct % MOD);
    }

    // 计算整棵树的总和
    private long getSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getSum(node.left) + getSum(node.right);
    }

    // 计算子树和并更新最大乘积
    private long calculateMaxProduct(TreeNode node) {
        if (node == null) return 0;

        // 递归计算左右子树的和
        long leftSum = calculateMaxProduct(node.left);
        long rightSum = calculateMaxProduct(node.right);

        // 当前子树的总和
        long currentSum = leftSum + rightSum + node.val;

        // 考虑删除左子节点与当前节点之间的边
        if (node.left != null) {
            long product = leftSum * (total - leftSum);
            maxProduct = Math.max(maxProduct, product);
        }

        // 考虑删除右子节点与当前节点之间的边
        if (node.right != null) {
            long product = rightSum * (total - rightSum);
            maxProduct = Math.max(maxProduct, product);
        }

        return currentSum;
    }

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
}
