/* Structure for Tree Node
class Node {
    int data;
    Node left, right;

    // Constructor
    Node(int val) {
        data = val;
        left = null;
        right = null;
    }
};
*/
class Solution {
    /**
     * Approach : Using DFS (Post-Order Traversal) Approach
     * 
     * TC : O(n)
     * SC : O(h) ~ (O(n) in worst case if tree is a skewed tree)
     */
    public void toSumTree(Node root) {
        dfsTree(root);
    }
    
    /**
     * Using DFS (Post-Order Traversal) Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private int dfsTree(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSum = dfsTree(root.left);
        int rightSum = dfsTree(root.right);
        int prevVal = root.data; // store previous data
        root.data = leftSum + rightSum;
        int totalSum = prevVal + root.data;
        return totalSum;
    }
}
