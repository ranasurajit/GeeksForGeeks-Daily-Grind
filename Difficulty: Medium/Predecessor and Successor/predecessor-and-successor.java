/*
class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/

class Solution {
    private Node pre = null;
    private Node suc = null;

    /**
     * Approach II : Using DFS on BST Approach
     * 
     * TC: O(H) ~ O(log(N))
     * SC: O(H) ~ O(log(N))
     */
    public ArrayList<Node> findPreSuc(Node root, int key) {
        dfsBST(root, key); // TC: O(log(N)), SC: O(log(N))
        ArrayList<Node> result = new ArrayList<>();
        result.add(pre);
        result.add(suc);
        return result;
    }
    
    /**
     * Using DFS on BST Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private void dfsBST(Node node, int key) {
        if (node == null) {
            return;
        }
        if (key > node.data) {
            pre = node;
            dfsBST(node.right, key);
        } else if (key < node.data) {
            suc = node;
            dfsBST(node.left, key);
        } else {
            /**
             * in case the key matches with 
             * node value, we need to compute
             * both Predecessor and Successor
             */
            // candidate for Predecessor
            Node temp = node.left;
            while (temp != null) {
                pre = temp;
                /**
                 * compute maximum which
                 * is present in node's
                 * right
                 */
                temp = temp.right;
            }
            // candidate for Successor
            temp = node.right;
            while (temp != null) {
                suc = temp;
                /**
                 * compute minimum which
                 * is present in node's
                 * left
                 */
                temp = temp.left;
            }
        }
    }

    /**
     * Approach I : Using DFS (Inorder Traversal) + Binary Search Approach
     * 
     * TC: O(N) + O(2 x log(N)) ~ O(N)
     * SC: O(N) + O(H) ~ O(N) in worst case
     */
    public ArrayList<Node> findPreSucBruteForce(Node root, int key) {
        List<Node> sorted = new ArrayList<>(); // SC: O(N)
        /**
         * Inorder traversal of BST is sorted
         */
        dfsTree(root, sorted); // TC: O(N), SC: O(H)
        /**
         * Now we can do Binary Search on sorted ArrayList
         * 
         */
        int n = sorted.size();
        int lb = lowerBound(sorted, n, key); // TC: O(log(N))
        int ub = upperBound(sorted, n, key); // TC: O(log(N))
        Node pre = lb > 0 ? sorted.get(lb - 1) : null;
        Node suc = ub < n ? sorted.get(ub) : null;
        ArrayList<Node> result = new ArrayList<>();
        result.add(pre);
        result.add(suc);
        return result;
    }
    
    /**
     * Using Binary Search (Lower Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(List<Node> sorted, int n, int key) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (sorted.get(mid).data >= key) {
                // move towards left
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    /**
     * Using Binary Search (Upper Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(List<Node> sorted, int n, int key) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (sorted.get(mid).data > key) {
                // move towards left
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    /**
     * Using DFS (Inorder Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private void dfsTree(Node node, List<Node> sorted) {
        // Base Case
        if (node == null) {
            return;
        }
        // Recursion Calls
        dfsTree(node.left, sorted);
        sorted.add(node);
        dfsTree(node.right, sorted);
    }
}
