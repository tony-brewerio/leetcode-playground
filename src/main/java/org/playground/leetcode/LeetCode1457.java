package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/submissions/1155898953/">Submission</a>
 * Runtime: 12 ms, Beats 72.66% of users with Java
 * Memory: 70.14 MB, Beats 49.06% of users with Java ( varies greatly between runs )
 * <p>
 * Not that fast, but fast enough.
 */
public class LeetCode1457 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1457.class);

    public int pseudoPalindromicPaths(TreeNode root) {
        return pseudoPalindromicPaths(root, new boolean[10]);
    }

    private int pseudoPalindromicPaths(TreeNode node, boolean[] odds) {
        odds[node.val] = !odds[node.val];
        if (node.left == null && node.right == null) {
            boolean found = false;
            for (boolean odd : odds) {
                if (odd) {
                    if (found) {
                        odds[node.val] = !odds[node.val];
                        return 0;
                    } else {
                        found = true;
                    }
                }
            }
            odds[node.val] = !odds[node.val];
            return 1;
        }
        int result = 0;
        if (node.left != null) {
            result += pseudoPalindromicPaths(node.left, odds);
        }
        if (node.right != null) {
            result += pseudoPalindromicPaths(node.right, odds);
        }
        odds[node.val] = !odds[node.val];
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
