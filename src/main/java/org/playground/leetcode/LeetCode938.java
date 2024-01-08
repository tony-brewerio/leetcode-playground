package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://leetcode.com/problems/range-sum-of-bst/submissions/1140802617/">Submission</a>
 * Runtime: 0 ms, Beats 100.00% of users with Java
 * Memory: 51.08 MB, Beats 21.43% of users with Java ( varies greatly between runs )
 */
public class LeetCode938 {
    private final Logger log = LoggerFactory.getLogger(LeetCode938.class);

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root.val < low) {
            return root.right != null ? rangeSumBST(root.right, low, high) : 0;
        }
        if (root.val > high) {
            return root.left != null ? rangeSumBST(root.left, low, high) : 0;
        }
        return root.val +
                (root.right != null ? rangeSumBST(root.right, low, high) : 0) +
                (root.left != null ? rangeSumBST(root.left, low, high) : 0);
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
