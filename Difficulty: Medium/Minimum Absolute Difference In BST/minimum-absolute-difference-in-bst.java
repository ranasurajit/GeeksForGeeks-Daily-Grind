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
    private ArrayList<Integer> sorted; // SC : O(n)

    /**
     * Approach : Using DFS (Pre-Order Traversal) Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(h) + O(n) ~ O(n)
     */
    public int absDiff(Node root) {
        sorted = new ArrayList<>();
        /**
         * the In-Order Traversal of
         * BST returns a sorted array
         */
        dfsTree(root); // TC : O(n), SC : O(h)
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < sorted.size(); i++) { // TC : O(n)
            minDiff = Math.min(minDiff, sorted.get(i) - sorted.get(i - 1));
        }
        return minDiff;
    }
    
    /**
     * Using DFS (Pre-Order Traversal) Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private void dfsTree(Node node) {
        if (node == null) {
            return;
        }
        dfsTree(node.left);
        sorted.add(node.data);
        dfsTree(node.right);
    }
}
