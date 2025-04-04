package T2504;

/**
 * @Description: DFS深度优先遍历/最近公共祖先（LCA）算法
 * @Author: iniwym
 * @Date: 2025-04-04
 * @Link: https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves/
 */
public class Code1123_LowestCommonAncestorOfDeepestLeaves {
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

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).lca;
    }

    /**
     * 使用深度优先搜索（DFS）方法计算以指定节点为根的子树的最大深度，并确定对应的最大深度路径上的最近公共祖先（LCA）节点。
     *
     * @param node 当前处理的树节点
     * @return 包含最大深度值和对应LCA节点的Result对象
     */
    private Result dfs(TreeNode node) {
        if (node == null) {
            // 基础情况：空节点深度为0，无LCA节点
            return new Result(0, null);
        }

        // 递归获取左右子树的深度及LCA信息
        Result left = dfs(node.left);
        Result right = dfs(node.right);

        // 根据子树深度选择当前子树的LCA
        if (left.depth > right.depth) {
            return new Result(left.depth + 1, left.lca);
        } else if (right.depth > left.depth) {
            return new Result(right.depth + 1, right.lca);
        } else {
            // 深度相同时，当前节点成为新的LCA
            return new Result(left.depth + 1, node);
        }
    }


    private static class Result {
        int depth;
        TreeNode lca;

        Result(int depth, TreeNode lca) {
            this.depth = depth;
            this.lca = lca;
        }
    }
}
