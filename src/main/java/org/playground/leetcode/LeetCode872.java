package org.playground.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @see <a href="https://leetcode.com/problems/leaf-similar-trees/submissions/1141757033/">Submission</a>
 */
public class LeetCode872 {
    private final Logger log = LoggerFactory.getLogger(LeetCode872.class);

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return treeToLeafValues(root1).equals(treeToLeafValues(root2));
    }

    private List<Integer> treeToLeafValues(TreeNode node) {
        var values = new ArrayList<Integer>();
        treeToLeafValues(node, values);
        return values;
    }

    private void treeToLeafValues(TreeNode node, List<Integer> values) {
        if (node.left == null && node.right == null) {
            values.add(node.val);
        } else {
            if (node.left != null) {
                treeToLeafValues(node.left, values);
            }
            if (node.right != null) {
                treeToLeafValues(node.right, values);
            }
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

        @Override
        public String toString() {
            return "[%s:%s,%s]".formatted(val, left, right);
        }
    }
}
