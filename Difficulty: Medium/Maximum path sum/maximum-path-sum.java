/*
class Node{
    int data;
    Node left, right;
    Node(int d){
        data=d;
        left=right=null;
    }
}
*/

class Solution {
    private int maxSum;

    /**
     * Approach : Using DFS Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    int findMaxSum(Node root) {
        this.maxSum = Integer.MIN_VALUE;
        findPathSum(root);
        return this.maxSum;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private int findPathSum(Node root) {
        // Base Case
        if (root == null) {
            return 0;
        }
        // Recursion Calls
        // ignoring negative sums
        int leftSum = Math.max(0, findPathSum(root.left));
        // ignoring negative sums
        int rightSum = Math.max(0, findPathSum(root.right));
        int sumPathThrough = root.data + leftSum + rightSum;
        maxSum = Math.max(maxSum, sumPathThrough);
        return root.data + Math.max(leftSum, rightSum);
    }
}
