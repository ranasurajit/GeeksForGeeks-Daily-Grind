class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x W)
     * SC: O(N x W) + O(N)
     * - O(N x W) - memoization memory
     * - O(N) - recursion stack
     * 
     * Accepted (1121 / 1121 testcases passed)
     */
    public int knapsack(int W, int val[], int wt[]) {
        int n = wt.length;
        int[][] memo = new int[n][W + 1]; // SC: O(N x W)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, W, val, wt, memo); // TC: O(N x W), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x W)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int W, int[] val, 
        int[] wt, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][W] != -1) {
            return memo[idx][W];
        }
        // Recursion Calls
        // we can pick or skip weight at index 'idx'
        // skip weight at index 'idx'
        int skip = solveMemoization(idx + 1, n, W, val, wt, memo);
        int pick = 0;
        if (wt[idx] <= W) {
            // we can pick weight at index 'idx'
            pick = val[idx] + solveMemoization(idx + 1, n, W - wt[idx], val, wt, memo);
        }
        return memo[idx][W] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (11 / 1121 testcases passed)
     */
    public int knapsackRecursion(int W, int val[], int wt[]) {
        int n = wt.length;
        return solveRecursion(0, n, W, val, wt); // TC: O(2 ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int W, int[] val, int[] wt) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        // we can pick or skip weight at index 'idx'
        // skip weight at index 'idx'
        int skip = solveRecursion(idx + 1, n, W, val, wt);
        int pick = 0;
        if (wt[idx] <= W) {
            // we can pick weight at index 'idx'
            pick = val[idx] + solveRecursion(idx + 1, n, W - wt[idx], val, wt);
        }
        return Math.max(pick, skip);
    }
}
