/* Structure of Binary Tree Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
}*/
class Solution {
    private int maxPathLength = 0;
    
    /**
     * Approach : Using DFS (Path Accumulation) Approach
     * 
     * TC : O(n)
     * SC : O(h) ~ O(n) in worst case
     */
    public int longestConsecutive(Node root) {
        dfsTree(root, root.data - 1, 0);
        return maxPathLength == 1 ? -1 : maxPathLength;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private void dfsTree(Node node, int parent, int pathLength) {
        // Base Case
        if (node == null) {
            return;
        }
        // Recursion Calls
        int current = node.data;
        if (current == 1 + parent) {
            pathLength = 1 + pathLength;
        } else {
            pathLength = 1;
        }
        maxPathLength = Math.max(maxPathLength, pathLength);
        dfsTree(node.left, current, pathLength);
        dfsTree(node.right, current, pathLength);
    }
}
