class Solution {
    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     * 
     * - O(n) - recursion stack space
     */
    public boolean isSorted(int[] arr) {
        int n = arr.length;
        return solve(0, n, arr);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private boolean solve(int idx, int n, int[] arr) {
        // Base Case
        if (idx == n - 1) {
            // single element is sorted
            return true;
        }
        // Recursion Calls
        boolean isCurrentSorted = arr[idx] <= arr[idx + 1];
        if (!isCurrentSorted) {
            return false;
        }
        boolean isNextSorted = solve(idx + 1, n, arr);
        return isNextSorted;
    }
}
