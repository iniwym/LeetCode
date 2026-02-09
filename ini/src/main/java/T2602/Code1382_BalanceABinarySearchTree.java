package T2602;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 平衡二叉搜索树
 * @Author: iniwym
 * @Date: 2026-02-09
 * @Link: https://leetcode.cn/problems/balance-a-binary-search-tree/
 */
public class Code1382_BalanceABinarySearchTree {

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
     * 将给定的二叉搜索树转换为平衡二叉搜索树。
     *
     * @param root 原始二叉搜索树的根节点
     * @return 平衡后的二叉搜索树的根节点
     */
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> nums = inorderTraversal(root);
        return sortedArrayToBST(nums);
    }

    /**
     * 对二叉树进行中序遍历，返回节点值的有序列表。
     *
     * @param root 二叉树的根节点
     * @return 中序遍历结果的有序列表
     */
    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }

    /**
     * 使用深度优先搜索实现中序遍历。
     *
     * @param ans  存储遍历结果的列表
     * @param node 当前访问的节点
     */
    private void dfs(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(ans, node.left);  // 遍历左子树
        ans.add(node.val);    // 访问当前节点
        dfs(ans, node.right); // 遍历右子树
    }

    /**
     * 将有序数组转换为高度平衡的二叉搜索树。
     *
     * @param nums 有序数组
     * @return 构建的平衡二叉搜索树的根节点
     */
    private TreeNode sortedArrayToBST(List<Integer> nums) {
        return buildBST(nums, 0, nums.size());
    }

    /**
     * 递归地将数组的指定区间构造成平衡二叉搜索树。
     *
     * @param nums  有序数组
     * @param left  区间起始索引（包含）
     * @param right 区间结束索引（不包含）
     * @return 构建的子树根节点
     */
    private TreeNode buildBST(List<Integer> nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int m = (left + right) >>> 1; // 取中间位置作为根节点
        return new TreeNode(
                nums.get(m),
                buildBST(nums, left, m),     // 递归构建左子树
                buildBST(nums, m + 1, right) // 递归构建右子树
        );
    }

}
