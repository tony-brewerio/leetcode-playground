package org.playground.leetcode;

import com.sun.source.tree.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @see <a href="https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/submissions/1142760875/">Submission</a>
 * Runtime: 53 ms, Beats 77.84% of users with Java
 * Memory: 83.15 MB, Beats 45.79% of users with Java ( varies greatly between runs )
 */
public class LeetCode2385 {
    private final Logger log = LoggerFactory.getLogger(LeetCode2385.class);

    public int amountOfTime(TreeNode root, int start) {
        var startNodeContainer = new TreeNodeWithParent[1];
        rebuildTreeWithParentConnections(root, null, start, startNodeContainer);
        var visited = new HashSet<Integer>();
        visited.add(startNodeContainer[0].val);
        var tasks = new ArrayDeque<Task>();
        tasks.offerLast(new Task(startNodeContainer[0], 0));
        int result = 0;
        while (!tasks.isEmpty()) {
            var task = tasks.pollFirst();
            if (task.depth > result) {
                result = task.depth;
            }
            if (task.node.parent != null && visited.add(task.node.parent.val)) {
                tasks.offerLast(new Task(task.node.parent, task.depth + 1));
            }
            if (task.node.left != null && visited.add(task.node.left.val)) {
                tasks.offerLast(new Task(task.node.left, task.depth + 1));
            }
            if (task.node.right != null && visited.add(task.node.right.val)) {
                tasks.offerLast(new Task(task.node.right, task.depth + 1));
            }
        }
        return result;
    }

    private TreeNodeWithParent rebuildTreeWithParentConnections(
            TreeNode node,
            TreeNodeWithParent parent,
            int start,
            TreeNodeWithParent[] startNode
    ) {
        var nodeWithParent = new TreeNodeWithParent(node.val, parent);
        if (node.left != null) {
            nodeWithParent.left = rebuildTreeWithParentConnections(node.left, nodeWithParent, start, startNode);
        }
        if (node.right != null) {
            nodeWithParent.right = rebuildTreeWithParentConnections(node.right, nodeWithParent, start, startNode);
        }
        if (node.val == start) {
            startNode[0] = nodeWithParent;
        }
        return nodeWithParent;
    }

    private static class Task {
        private final TreeNodeWithParent node;
        private final int depth;

        private Task(TreeNodeWithParent node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    private static class TreeNodeWithParent {
        private final int val;
        private final TreeNodeWithParent parent;
        private TreeNodeWithParent left;
        private TreeNodeWithParent right;

        public TreeNodeWithParent(int val, TreeNodeWithParent parent) {
            this.val = val;
            this.parent = parent;
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
