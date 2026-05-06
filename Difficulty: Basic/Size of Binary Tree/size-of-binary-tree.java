/*
Definition for Node
class Node
{
    int data;
    Node left;
    Node right;

    Node(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }
}
*/
class Solution {
    /**
     * Approach : Using DFS Post Order Traversal Approach
     * 
     * TC : O(n)
     * SC : O(h) ~ O(n) (in worst case)
     */
    public int getSize(Node root) {
        return dfsTree(root).count;
    }
    
    /**
     * Using DFS Post Order Traversal Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private TreePair dfsTree(Node root) {
        // Base Case
        if (root == null) {
            return new TreePair(null, 0);
        }
        // Recursion Calls - DFS Post Order
        TreePair left = dfsTree(root.left);
        TreePair right = dfsTree(root.right);
        int count = 1 + left.count + right.count;
        return new TreePair(root, count);
    }
    
    class TreePair {
        Node node;
        int count;
        
        public TreePair (Node node, int count) {
            this.node = node;
            this.count = count;
        }
    }
}
