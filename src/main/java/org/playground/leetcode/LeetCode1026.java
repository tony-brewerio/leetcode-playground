package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.HashSet;

/**
 * @see <a href="https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/submissions/1143662089/">Submission</a>
 * Runtime: 0 ms, Beats 100.00% of users with Java
 * Memory: 42.11 MB, Beats 13.19% of users with Java ( varies greatly between runs )
 * <p>
 * Turned out easier than expected initially.
 * Just recurse down the tree, keeping max and min values of all parents on the path.
 */
public class LeetCode1026 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1026.class);

    public int maxAncestorDiff(TreeNode root) {
        return maxAncestorDiff(root, root.val, root.val);
    }

    public int maxAncestorDiff(TreeNode node, int parentMinVal, int parentMaxVal) {
        int diff = Math.max(Math.abs(parentMinVal - node.val), Math.abs(parentMaxVal - node.val));
        int newParentMinVal = Math.min(parentMinVal, node.val);
        int newParentMaxVal = Math.max(parentMaxVal, node.val);
        if (node.left != null) {
            diff = Math.max(diff, maxAncestorDiff(node.left, newParentMinVal, newParentMaxVal));
        }
        if (node.right != null) {
            diff = Math.max(diff, maxAncestorDiff(node.right, newParentMinVal, newParentMaxVal));
        }
        return diff;
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

        @Override
        public String toString() {
            return "[%s:%s,%s]".formatted(val, left, right);
        }
    }
}
