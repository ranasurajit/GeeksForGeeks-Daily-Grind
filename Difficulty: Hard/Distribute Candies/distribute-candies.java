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
    private int moves = 0;

    /**
     * Approach II : Using Optimal (DFS Post Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(H) ~ O(N) in worst case
     */
    public int distCandy(Node root) {
        /**
         * each child should return how many excess/deficit
         * candies it has/needs from its parent
         * 
         * so total moves = 
         * |left excess/deficit| + |right excess/deficit|
         */
        dfsTreeOptimal(root);
        return moves;
    }
    
    /**
     * Using DFS Post Order Traversal Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private int dfsTreeOptimal(Node root) {
        /**
         * This method will just return the 
         * excess/deficit candies from children
         * node to parent
         */
        // Base Case
        if (root == null) {
            return 0;
        }
        // Recursion Calls
        int leftExcess = dfsTreeOptimal(root.left);
        int rightExcess = dfsTreeOptimal(root.right);
        moves += Math.abs(leftExcess) + Math.abs(rightExcess);
        // this is the excess or deficit candies returned to parent node
        return root.data - 1 + leftExcess + rightExcess;
    }

    /**
     * Approach I : Using Brute-Force (DFS Post Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(H) ~ O(N) in worst case
     */
    public int distCandyBruteForce(Node root) {
        /**
         * each child should return how many excess/deficit
         * candies it has/needs from its parent
         * 
         * so total moves = 
         * |left excess/deficit| + |right excess/deficit|
         */
        return dfsTree(root).moves;
    }
    
    /**
     * Using DFS Post Order Traversal Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private Pair dfsTree(Node root) {
        // Base Case
        if (root == null) {
            return new Pair(0, 0);
        }
        // Recursion Calls
        Pair left = dfsTree(root.left);
        Pair right = dfsTree(root.right);
        int excess = root.data - 1 + left.excess + right.excess;
        int moves = left.moves + right.moves +
            Math.abs(left.excess) + Math.abs(right.excess);
        return new Pair(excess, moves);
    }
}

class Pair {
    int excess;
    int moves;
    
    public Pair(int excess, int moves) {
        this.excess = excess;
        this.moves = moves;
    }
}
