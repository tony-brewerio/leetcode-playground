package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @see <a href="https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/submissions/1155902468/">Submission</a>
 * Runtime: 9 ms, Beats 86.89% of users with Java
 * Memory: 70.64 MB, Beats 39.33% of users with Java ( varies greatly between runs )
 * <p>
 * Not that fast, but fast enough.
 * <p>
 * Optimized a bit by keeping odd item count separately, so there is no need to walk over odds on leaf nodes.
 */
public class LeetCode1457 {
    private final Logger log = LoggerFactory.getLogger(LeetCode1457.class);

    public int pseudoPalindromicPaths(TreeNode root) {
        return pseudoPalindromicPaths(root, new boolean[10], 0);
    }

    private int pseudoPalindromicPaths(TreeNode node, boolean[] odds, int oddItemCount) {
        if (odds[node.val] = !odds[node.val]) {
            oddItemCount++;
        } else {
            oddItemCount--;
        }
        if (node.left == null && node.right == null) {
            odds[node.val] = !odds[node.val];
            return oddItemCount <= 1 ? 1 : 0;
        }
        int result = 0;
        if (node.left != null) {
            result += pseudoPalindromicPaths(node.left, odds, oddItemCount);
        }
        if (node.right != null) {
            result += pseudoPalindromicPaths(node.right, odds, oddItemCount);
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
