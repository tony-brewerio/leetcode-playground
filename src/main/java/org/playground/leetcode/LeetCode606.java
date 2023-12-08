package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/construct-string-from-binary-tree/submissions/1115268043/">Submission</a>
 * <p>
 * Pretty simple one.
 * Used StringBuilder instead of concatenating strings with +, since it should be much more efficient.
 */
public class LeetCode606 {
    private final Logger log = LoggerFactory.getLogger(LeetCode606.class);

    public String tree2str(TreeNode root) {
        var sb = new StringBuilder();
        tree2str(root, sb);
        return sb.toString();
    }

    private void tree2str(TreeNode node, StringBuilder sb) {
        sb.append(node.val);
        if (node.left != null) {
            sb.append('(');
            tree2str(node.left, sb);
            sb.append(')');
        }
        if (node.right != null) {
            if (node.left == null) {
                sb.append('(');
                sb.append(')');
            }
            sb.append('(');
            tree2str(node.right, sb);
            sb.append(')');
        }
    }

    public static class TreeNode {
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
