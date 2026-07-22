class Solution {
    private int index;
    
    /**
     * Approach : Using DFS (Pre-Order Traversal) Approach
     * 
     * TC : O(n)
     * SC : O(h) ~ O(n)
     * where h = log(n) in worst case h ~ n
     */
    public boolean canRepresentBST(List<Integer> arr) {
        dfsTree(arr, Long.MIN_VALUE, Long.MAX_VALUE);
        return index == arr.size();
    }
    
    /**
     * Using DFS (Pre-Order Traversal) Approach
     * 
     * TC : O(n)
     * SC : O(h)
     */
    private void dfsTree(List<Integer> arr, long min, long max) {
        // Base Case
        if (index >= arr.size()) {
            // out of bound index
            return;
        }
        int node = arr.get(index);
        if (node <= min || node >= max) {
            // invalid and does not belong to group [min, max]
            return;
        }
        // Recursion Calls
        index++;
        dfsTree(arr, min, node);
        dfsTree(arr, node, max);
    }
}
