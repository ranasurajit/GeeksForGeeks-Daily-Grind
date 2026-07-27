/* Structure of Binary Tree Node
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
} */

class Solution {
    /**
     * Approach : Using Recursion / DFS Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public Node constructBinaryTree(int[] pre, int[] preMirror) {
        int n = pre.length;
        Map<Integer, Integer> mirrorIndexMap = new HashMap<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            mirrorIndexMap.put(preMirror[i], i);
        }
        /**
         * we need to know the ranges of any subtree where it should
         * lookup for 'pre' and 'preMirror' arrays
         */
        int preStart = 0;
        int preEnd = n - 1;
        int mirrorStart = 0;
        int mirrorEnd = n - 1;
        return solve(preStart, 
            preEnd, mirrorStart,
            mirrorEnd , pre, 
            preMirror, mirrorIndexMap); // TC : O(n), SC : O(n)
    }
    
    /**
     * Using Recursion / DFS Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private Node solve(int preStart, int preEnd,
            int mirrorStart, int mirrorEnd,
            int[] pre, int[] preMirror,
            Map<Integer, Integer> mirrorIndexMap) {
        // Base Case
        if (preStart > preEnd) {
            // out of bound
            return null;
        }
        if (preStart == preEnd) {
            // this will be the leaf node
            return new Node(pre[preStart]);
        }
        // Recursion Calls
        Node root = new Node(pre[preStart]);
        int leftNodeVal = pre[preStart + 1];
        int idx = mirrorIndexMap.get(leftNodeVal);
        int leftSize = mirrorEnd - idx + 1;
        /**
         * we need to define the ranges of both 'pre' and 'preMirror'
         * for the next recursion calls
         * 
         * For 'pre':
         * next range left : [preStart + 1 ... preStart + leftSize]
         * next range right : [preStart + leftSize + 1 ... preEnd]
         * 
         * For 'preMirror':
         * next range left : [idx...mirrorEnd]
         * next range right: [mirrorStart + 1, idx - 1]
         */
        root.left = solve(
            preStart + 1,
            preStart + leftSize,
            idx,
            mirrorEnd,
            pre, preMirror, mirrorIndexMap);
        root.right = solve(
            preStart + leftSize + 1,
            preEnd,
            mirrorStart + 1,
            idx - 1,
            pre, preMirror, mirrorIndexMap);
        return root;
    }
}
