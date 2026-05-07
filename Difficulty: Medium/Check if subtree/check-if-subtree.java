/*
Definition for Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/
class Solution {
    /**
     * Approach : Using DFS Approach
     * 
     * TC : O(m) + O(n)
     * SC : O(m) + O(n)
     * 
     * where m = nodesCount(root1), n = nodesCount(root2)
     */
    public boolean isSubTree(Node root1, Node root2) {
        // Base Case
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        // Recursion Calls
        if (isSameTree(root1, root2)) { // TC : O(n), SC : O(n) 
            return true;
        }
        return isSubTree(root1.left, root2) || isSubTree(root1.right, root2);
    }
    
    /**
     * Using DFS Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private boolean isSameTree(Node root1, Node root2) {
        // Base Case
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        // Recursion Calls
        return root1.data == root2.data && 
            isSameTree(root1.left, root2.left) &&
            isSameTree(root1.right, root2.right);
    }
}
