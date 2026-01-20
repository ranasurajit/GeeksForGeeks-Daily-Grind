/*
class Node {
    int data;
    Node left, right;

    Node(int val)
    {
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    /**
     * Approach : Using DFS Approach
     * 
     * Intuition - Return Type is just Depth so we return int 
     * (node returns it's depth to its parent)
     * 
     * TC: O(N) - all nodes are visited exactly once
     * SC: O(H) ~ O(log(N)) (O(N) in worst case) 
     *
     * height of Tree (in case of Binary Tree, 
     * it is log(N) and in case of skewed Tree, it is N) 
     */
    public int height(Node root) {
        return dfsTree(root) - 1;
    }
    
    /**
     * Using DFS Approach
     * Intuition - Return Type is just Depth so we return int 
     * (node returns it's depth to its parent)
     * 
     * TC: O(N) - all nodes are visited exactly once
     * SC: O(H) ~ O(log(N)) (O(N) in worst case) 
     *
     * height of Tree (in case of Binary Tree, 
     * it is log(N) and in case of skewed Tree, it is N) 
     */
    private int dfsTree(Node node) {
        // Base Case
        if (node == null) {
            // height at null node is 0
            return 0;
        }
        int leftHeight = dfsTree(node.left);
        int rightHeight = dfsTree(node.right);
        // merge height from left and right children with current node
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
