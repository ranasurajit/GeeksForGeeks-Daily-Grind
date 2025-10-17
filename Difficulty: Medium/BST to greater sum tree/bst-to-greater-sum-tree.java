/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
} */

class Solution {
    /**
     * Approach II : Using DFS (Reverse In-Order Traversal) Approach (1 Pass Approach)
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(log(N)) + O(log(N)) + O(N) ~ O(N)
     */
    public static void transformTree(Node root) {
        int[] suffixSum = { 0 };
        dfsTreeReverseInorder(root, suffixSum);
    }
    
    /**
     * Using DFS (Reverse In-Order Traversal - Right Node Left Visits) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private static void dfsTreeReverseInorder(Node node, int[] suffixSum) {
        if (node == null) {
            return;
        }
        dfsTreeReverseInorder(node.right, suffixSum);
        int original = node.data;
        node.data = suffixSum[0];
        suffixSum[0] += original;
        dfsTreeReverseInorder(node.left, suffixSum);
    }
    
    /**
     * Approach I : Using DFS (In-Order Traversal) Approach (3 Pass Approach)
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(log(N)) + O(log(N)) + O(N) ~ O(N)
     */
    public static void transformTreeBruteForce(Node root) {
        List<Integer> inorder = new ArrayList<Integer>();
        dfsTree(root, inorder); // TC: O(N), SC: O(log(N))
        int n = inorder.size();
        int[] suffixSum = new int[n]; // SC: O(N)
        suffixSum[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            suffixSum[i] = suffixSum[i + 1] + inorder.get(i + 1);
        }
        dfsFillGreaterSum(root, suffixSum, new int[] { 0 }); // TC: O(N), SC: O(log(N))
    }
    
    /**
     * Using DFS (In-Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private static void dfsTree(Node node, List<Integer> inorder) {
        // Base Case
        if (node == null) {
            return;
        }
        // Recursion Calls
        dfsTree(node.left, inorder);
        inorder.add(node.data);
        dfsTree(node.right, inorder);
    }
    
    /**
     * Using DFS (In-Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private static void dfsFillGreaterSum(Node node, int[] suffixSum, int[] idx) {
        // Base Case
        if (node == null) {
            return;
        }
        // Recursion Calls
        dfsFillGreaterSum(node.left, suffixSum, idx);
        node.data = suffixSum[idx[0]++];
        dfsFillGreaterSum(node.right, suffixSum, idx);
    }
}
