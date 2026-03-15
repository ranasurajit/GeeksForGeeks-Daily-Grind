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
     * Approach : Using DFS Approach
     * 
     * TC: O(N²)
     * SC: O(H) ~ O(N)
     */
    public int countAllPaths(Node root, int k) {
        // Base Case
        if (root == null) {
            return 0;
        }
        // count paths that include Node 'root'
        int countIncludingCurrent = countFromNode(root, k); // TC: O(N), SC: O(H)
        // Recursion Calls
        int countExcludingCurrent = 
            countAllPaths(root.left, k) +
            countAllPaths(root.right, k);
        return countIncludingCurrent + countExcludingCurrent;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private int countFromNode(Node node, int remainingSum) {
        // Base Case
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.data == remainingSum) {
            // we got a path here
            count++;
        }
        // Recursion Calls
        count += countFromNode(node.left, remainingSum - node.data);
        count += countFromNode(node.right, remainingSum - node.data);
        return count;
    }
}
