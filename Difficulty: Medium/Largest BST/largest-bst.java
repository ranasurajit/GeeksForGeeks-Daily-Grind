// class Node
// {
//     int data;
//     Node left, right;

//     public Node(int d)
//     {
//         data = d;
//         left = right = null;
//     }
// }

class Solution {
    // Return the size of the largest sub-tree which is also a BST
    /**
     * Approach : Using DFS (Post Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(H) ~ O(N)
     */
    static int largestBst(Node root) {
        int[] maxBST = { 0 };
        dfsTree(root, maxBST);
        return maxBST[0];
    }
    
    /**
     * Using DFS (Post Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private static Pair dfsTree(Node root, int[] maxBST) {
        // Base Case
        if (root == null) {
            return new Pair(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        // Recursion Calls
        Pair left = dfsTree(root.left, maxBST);
        Pair right = dfsTree(root.right, maxBST);
        boolean isBSTAtCurrent = left.isBST && right.isBST &&
            root.data > left.max && root.data < right.min;
        if (!isBSTAtCurrent) {
            return new Pair(false, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        int sizeAtCurrent = left.sizeBST + right.sizeBST + 1;
        maxBST[0] = Math.max(maxBST[0], sizeAtCurrent);
        int minAtCurrent = Math.min(left.min, root.data);
        int maxAtCurrent = Math.max(right.max, root.data);
        return new Pair(isBSTAtCurrent, sizeAtCurrent, minAtCurrent, maxAtCurrent);
    }
}

class Pair {
    boolean isBST;
    int sizeBST;
    int min;
    int max;
    
    public Pair(boolean isBST, int sizeBST, int min, int max) {
        this.isBST = isBST;
        this.sizeBST = sizeBST;
        this.min = min;
        this.max = max;
    }
}
