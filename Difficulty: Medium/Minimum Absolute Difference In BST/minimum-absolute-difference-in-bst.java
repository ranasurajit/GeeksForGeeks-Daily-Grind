/* The Node structure is defined as
 class Node {
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    private Node prev = null;
    private int minDiff = Integer.MAX_VALUE;

    /**
     * Approach II : Using DFS Approach
     * 
     * TC : O(n)
     * SC : O(h) ~ O(n)
     */
    public int absDiff(Node root) {
        /**
         * the In-Order Traversal of
         * BST returns a sorted array
         */
        dfsOptTree(root); // TC : O(n), SC : O(h)
        return minDiff;
    }
    
    /**
     * Using DFS (In-Order Traversal) Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private void dfsOptTree(Node node) {
        if (node == null) {
            return;
        }
        dfsOptTree(node.left);
        if (prev != null) {
            minDiff = Math.min(minDiff,
                Math.abs(node.data - prev.data));
        }
        prev = node;
        dfsOptTree(node.right);
    }

    /**
     * Approach I : Using DFS (In-Order Traversal) Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(h) + O(n) ~ O(n)
     */
    public int absDiffDFSInOrderTraversal(Node root) {
        ArrayList<Integer> sorted = new ArrayList<>(); // SC : O(n)
        /**
         * the In-Order Traversal of
         * BST returns a sorted array
         */
        dfsTree(root, sorted); // TC : O(n), SC : O(h)
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < sorted.size(); i++) { // TC : O(n)
            minDiff = Math.min(minDiff, sorted.get(i) - sorted.get(i - 1));
        }
        return minDiff;
    }
    
    /**
     * Using DFS (In-Order Traversal) Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private void dfsTree(Node node, ArrayList<Integer> sorted) {
        if (node == null) {
            return;
        }
        dfsTree(node.left, sorted);
        sorted.add(node.data);
        dfsTree(node.right, sorted);
    }
}
