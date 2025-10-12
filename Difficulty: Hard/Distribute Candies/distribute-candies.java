/*
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
*/

class Solution {
    /**
     * Approach : Using DFS (Post Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    public int distCandy(Node root) {
        if (root == null) {
            return 0;
        }
        int[] moves = { 0 };
        dfsTreeBalance(root, moves);
        return moves[0];
    }
    
    /**
     * Using DFS (Post Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private int dfsTreeBalance(Node root, int[] moves) {
        // Base Case
        if (root == null) {
            return 0;
        }
        // Recursion Calls
        int leftCandiesBalance = dfsTreeBalance(root.left, moves);
        int rightCandiesBalance = dfsTreeBalance(root.right, moves);
        moves[0] += Math.abs(leftCandiesBalance) + Math.abs(rightCandiesBalance);
        return root.data + leftCandiesBalance + rightCandiesBalance - 1;
    }
}
