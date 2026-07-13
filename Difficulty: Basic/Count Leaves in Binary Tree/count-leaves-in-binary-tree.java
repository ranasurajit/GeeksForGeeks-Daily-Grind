/* A Binary Tree node
class Node
{
    int data;
    Node left, right;
}*/

class Solution {
    /**
     * Approach : Using DFS Pre-Order Traversal Approach
     * 
     * TC : O(n)
     * SC : O(h) ~ O(n) in worst case
     */
    int countLeaves(Node node) {
        int[] count = { 0 };
        dfsTree(node, count);
        return count[0];
    }
    
    /**
     * Using DFS Pre-Order Traversal Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private void dfsTree(Node node, int[] count) {
        // Base Case
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            count[0]++;
        }
        dfsTree(node.left, count);
        dfsTree(node.right, count);
    }
}
