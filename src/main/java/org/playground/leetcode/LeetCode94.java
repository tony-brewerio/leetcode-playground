package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/submissions/1115682145/">Submission</a>
 */
public class LeetCode94 {
    private final Logger log = LoggerFactory.getLogger(LeetCode94.class);

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        var result = new ArrayList<Integer>();
        var stack = new ArrayDeque<TreeNode>();
        var node = root;
        while (node != null || !stack.isEmpty()) {
            for (var next = node; next != null; next = next.left) {
                stack.offerLast(next);
            }
            node = stack.pollLast();
            result.add(node.val);
            node = node.right;
        }
        return result;
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
