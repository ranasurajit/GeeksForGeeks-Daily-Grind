/*
class Node {
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}
*/

class Solution {
    /**
     * Using DFS (Post-Order traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    Node removekeys(Node root, int l, int r) {
        /**
         * Perform DFS (Post-Order traversal) as we need to 
         * operate Left and Right node before keeping/removing 
         * parent / root node
         */
        return dfsPostOrder(root, l, r); // TC: O(N), SC: O(log(N)) 
    }
    
    /**
     * Using DFS (Post-Order traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private Node dfsPostOrder(Node node, int l, int r) {
        if (node == null) {
            return null;
        }
        node.left = dfsPostOrder(node.left, l, r);
        node.right = dfsPostOrder(node.right, l, r);
        if (node.data < l) {
            // all node in left of BST will be less than range [l, r]
            return node.right;
        }
        if (node.data > r) {
            // all node in right of BST will be greater than range [l, r]
            return node.left;
        }
        return node;
    }
}
