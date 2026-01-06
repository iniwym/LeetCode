package T2601;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 二叉树
 * @Author: iniwym
 * @Date: 2026-01-06
 * @Link: https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree/
 */
public class Code1161_MaximumLevelSumOfABinaryTree {

    /**
     * 找到二叉树中具有最大节点值之和的层级
     * 使用层序遍历（BFS）计算每一层的节点值之和，返回和最大的层级编号
     *
     * @param root 二叉树的根节点
     * @return 具有最大节点值之和的层级编号（从1开始计数）
     */
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0; // 或者根据需求抛出异常
        }

        int maxSum = Integer.MIN_VALUE;
        int currentLevel = 1;
        int maxLevel = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 层序遍历二叉树，计算每一层的节点值之和
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int tempSum = 0;

            // 遍历当前层的所有节点，计算该层节点值之和
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                tempSum += current.val;

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            // 更新最大和及其对应的层级
            if (tempSum > maxSum) {
                maxSum = tempSum;
                maxLevel = currentLevel;
            }

            currentLevel++;
        }

        return maxLevel;
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
