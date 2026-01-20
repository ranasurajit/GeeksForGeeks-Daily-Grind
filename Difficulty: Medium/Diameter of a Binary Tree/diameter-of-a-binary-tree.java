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
} */

class Solution {
    /**
     * Approach : Using DFS Approach
     *
     * Intuition - Return Type is TreePair so node 
     * returns { depth, diameter } to its parent
     * 
     * TC: O(N) - all nodes are visited exactly once
     * SC: O(H) ~ O(log(N)) (O(N) in worst case) 
     *
     * height of Tree (in case of Binary Tree, 
     * it is log(N) and in case of skewed Tree, it is N) 
     */
    public int diameter(Node root) {
        return dfsTree(root).diameter;
    }
    
    /**
     * Using DFS Approach
     *
     * Intuition - Return Type is TreePair so node 
     * returns { depth, diameter } to its parent
     * 
     * TC: O(N) - all nodes are visited exactly once
     * SC: O(H) - height of Tree (in case of Binary Tree, 
     * it is log(N) and in case of skewed Tree, it is N) 
     */
    private TreePair dfsTree(Node node) {
        // Base Case
        if (node == null) {
            // when node is null we can return a Default value here
            return new TreePair(0, 0);
        }
        // Recursion Calls
        TreePair leftPair = dfsTree(node.left);
        TreePair rightPair = dfsTree(node.right);
        // combine/merge left and right pair children with current node
        /**
         * case 1 : if diameter passes through current node then,
         * diameter = leftPair.height + rightPair.height
         */
        int diameterOption1 = leftPair.height + rightPair.height;
        /**
         * case 2 : if diameter does not pass through current node then,
         * diameter will be the maximum from either of its children
         */
        int diameterOption2 = Math.max(leftPair.diameter, rightPair.diameter);
        int diameter = Math.max(diameterOption1, diameterOption2);
        int height = 1 + Math.max(leftPair.height, rightPair.height);
        return new TreePair(height, diameter);
    }
    
    private class TreePair {
        int height;
        int diameter;
        
        public TreePair (int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }
}
