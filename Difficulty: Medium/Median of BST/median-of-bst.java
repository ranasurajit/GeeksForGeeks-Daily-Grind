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
     * Approach : Using DFS (InOrder Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N)) + O(N)
     */
    public int findMedian(Node root) {
        // inorder traversal of BST is always sorted
        List<Integer> inorder = new ArrayList<Integer>(); // SC: O(N)
        dfsTree(root, inorder); // TC: O(N), SC: O(log(N))
        int n = inorder.size();
        return (n & 1) == 0 ? inorder.get((n / 2) - 1) : inorder.get(n / 2);
    }
    
    /**
     * Using DFS (InOrder Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private void dfsTree(Node node, List<Integer> inorder) {
        // Base Case
        if (node == null) {
            return;
        }
        dfsTree(node.left, inorder);
        inorder.add(node.data);
        dfsTree(node.right, inorder);
    }
}
