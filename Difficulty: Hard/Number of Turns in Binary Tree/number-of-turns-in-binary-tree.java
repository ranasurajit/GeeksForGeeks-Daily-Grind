/* Structure of Binary Tree Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int val) {
        data = val;
        left = right = null;
    }
} */

class Solution {
    private Node lca = null;

    public int numberOfTurns(Node root, int p, int q) {
        lca = null;

        /*
         * Step 1: Find LCA of p and q.
         */
        lcaDFSTree(root, p, q);

        if (lca == null) {
            return -1;
        }

        /*
         * Step 2: Find directions from LCA -> p
         * and LCA -> q.
         *
         * 0 -> LEFT
         * 1 -> RIGHT
         */
        List<Integer> pathToP = new ArrayList<>();
        List<Integer> pathToQ = new ArrayList<>();

        findPath(lca, p, pathToP);
        findPath(lca, q, pathToQ);

        /*
         * If p or q itself is the LCA, its corresponding
         * path will be empty.
         */
        if (pathToP.isEmpty() && pathToQ.isEmpty()) {
            return -1;
        }

        /*
         * Step 3:
         *
         * Actual path is:
         *
         * p -> LCA -> q
         *
         * Therefore, reverse LCA -> p.
         */
        Collections.reverse(pathToP);

        /*
         * Step 4:
         * Count direction changes.
         */
        int turns = 0;

        Integer previousDirection = null;

        for (int direction : pathToP) {
            if (previousDirection != null &&
                previousDirection != direction) {
                turns++;
            }

            previousDirection = direction;
        }

        for (int direction : pathToQ) {
            if (previousDirection != null &&
                previousDirection != direction) {
                turns++;
            }

            previousDirection = direction;
        }

        /*
         * If there was no direction change,
         * the path is a straight line.
         */
        return turns == 0 ? -1 : turns;
    }

    /**
     * Finds the path from 'node' to 'target'.
     *
     * Stores:
     * 0 -> LEFT
     * 1 -> RIGHT
     *
     * Example:
     *
     *        1
     *       /
     *      2
     *       \
     *        5
     *
     * findPath(1, 5, path)
     *
     * path = [LEFT, RIGHT]
     *
     * TC : O(n)
     * SC : O(h)
     */
    private boolean findPath(
        Node node,
        int target,
        List<Integer> path
    ) {
        // Base Case
        if (node == null) {
            return false;
        }

        if (node.data == target) {
            return true;
        }

        /*
         * Try LEFT.
         */
        path.add(0);

        if (findPath(node.left, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);

        /*
         * Try RIGHT.
         */
        path.add(1);

        if (findPath(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);

        return false;
    }

    /**
     * Finds LCA of p and q using postorder DFS.
     *
     * Returns:
     * 0 -> neither p nor q found
     * 1 -> one of p/q found
     * 2 -> both p and q found
     *
     * TC : O(n)
     * SC : O(h)
     */
    private int lcaDFSTree(
        Node node,
        int p,
        int q
    ) {
        // Base Case
        if (node == null) {
            return 0;
        }

        /*
         * Recursion Calls
         */
        int left = lcaDFSTree(node.left, p, q);
        int right = lcaDFSTree(node.right, p, q);

        /*
         * Current node contains p or q.
         */
        int self = 0;

        if (node.data == p || node.data == q) {
            self = 1;
        }

        int total = self + left + right;

        /*
         * First node in postorder having both
         * p and q is the lowest common ancestor.
         */
        if (total == 2 && lca == null) {
            lca = node;
        }

        return total;
    }
}
