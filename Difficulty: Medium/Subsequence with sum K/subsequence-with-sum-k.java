class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC : O(n x k)
     * SC : O(n) + O(n x k)
     * 
     * - O(n) - recursion stack
     * - O(n x k) - memoization memory
     */
    public boolean checkSubsequenceSum(int[] arr, int k) {
        int n = arr.length;
        int[][] memo = new int[n][k + 1]; // SC : O(n x k)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, arr, k, memo) == 1; // TC : O(n x k), SC : O(n)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n x k)
     * SC : O(n)
     */
    private int solveMemoization(int idx, int n,
        int[] arr, int k, int[][] memo) {
        // Base Case
        if (idx == n) {
            return k == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[idx][k] != -1) {
            return memo[idx][k];
        }
        // Recursion Calls
        // skip
        int skip = solveMemoization(idx + 1, n, arr, k, memo);
        int pick = 0;
        if (k >= arr[idx]) {
            pick = solveMemoization(idx + 1, n, arr, k - arr[idx], memo);
        }
        return memo[idx][k] = (skip == 1 || pick == 1) ? 1 : 0;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     * 
     * - O(n) - recursion stack
     */
    public boolean checkSubsequenceSumRecursion(int[] arr, int k) {
        int n = arr.length;
        return solveRecursion(0, n, arr, k); // TC : O(2ⁿ), SC : O(n)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private boolean solveRecursion(int idx, int n, int[] arr, int k) {
        // Base Case
        if (idx == n) {
            return k == 0;
        }
        // Recursion Calls
        // skip
        boolean skip = solveRecursion(idx + 1, n, arr, k);
        boolean pick = false;
        if (k >= arr[idx]) {
            pick = solveRecursion(idx + 1, n, arr, k - arr[idx]);
        }
        return skip || pick;
    }
}
