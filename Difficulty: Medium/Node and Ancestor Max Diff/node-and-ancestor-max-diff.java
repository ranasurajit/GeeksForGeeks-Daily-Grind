/* Structure of binary tree node
class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}
*/

class Solution {
    private int maxDiff = Integer.MIN_VALUE;

    /**
     * Approach : Using DFS (Root to Leaf Accumulation) Approach
     * 
     * TC : O(n)
     * SC : O(h) ~ O(n)
     */
    int maxDiff(Node root) {
        dfsTree(root, -1);
        return maxDiff;
    }
    
    /**
     * Using DFS (Root to Leaf Accumulation) Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private void dfsTree(Node node, int parentMax) {
        // Base Case
        if (node == null) {
            return;
        }
        // Recursion Calls
        /**
         * at a node we will compute the difference 
         * of parentMax and current node value
         */
        if (parentMax != -1) {
            // compare and store if node is not root node
            int currentDiff = parentMax - node.data;
            maxDiff = Math.max(maxDiff, currentDiff);
        }
        int currentMax = Math.max(parentMax, node.data);
        dfsTree(node.left, currentMax);
        dfsTree(node.right, currentMax);
    }
}
