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
     * Approach : Using DFS (In-Order Traversal) Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(K) ~ O(N x log(N))
     * SC: O(log(N)) + O(N) ~ O(N)
     */
    public ArrayList<Integer> getKClosest(Node root, int target, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<int[]> list = new ArrayList<int[]>(); // SC: O(N)
        dfsTree(root, target, list); // TC: O(N), SC: O(log(N))
        Collections.sort(list, (a, b) -> a[1] - b[1]); // TC: O(N x log(N))
        int index = 0;
        while (k-- > 0) { // TC: O(K)
            result.add(list.get(index++)[0]);
        }
        return result;
    }
    
    /**
     * Using DFS (In-Order Traversal) Approach
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private void dfsTree(Node node, int target, ArrayList<int[]> list) {
        if (node == null) {
            return;
        }
        dfsTree(node.left, target, list);
        list.add(new int[] { node.data, Math.abs(node.data - target) });
        dfsTree(node.right, target, list);
    }
}
