class Solution {
    private int n;
    private int m;
    
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n x m) + O(n x m) ~ O(n x m)
     * SC : O(n x m) + O(n)
     * - O(n x m) - memoization memory
     * - O(n) - recursion stack
     * 
     * Accepted
     */
    public int count(int n, int m) {
        this.n = n;
        this.m = m;
        int[][] memo = new int[n][m + 2]; // SC : O(n x m)
        for (int[] mem : memo) { // TC : O(n x m)
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, -1, memo); // TC : O(n x m), SC : O(n)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n x m)
     * SC : O(n)
     */
    private int solveMemoization(int idx, int prev, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 1;
        }
        // Memoization Check
        if (memo[idx][prev + 1] != -1) {
            return memo[idx][prev + 1];
        }
        // Recursion Calls
        int count = 0;
        for (int i = 1; i <= m; i++) { // TC : O(m)
            if (prev == -1 || (i % prev == 0 || prev % i == 0)) {
                // we can include 'i'
                count += solveMemoization(idx + 1, i, memo);
            }
        }
        return memo[idx][prev + 1] = count;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(mⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded
     */
    public int countRecursion(int n, int m) {
        this.n = n;
        this.m = m;
        return solveRecursion(0, -1); // TC : O(mⁿ), SC : O(n)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(mⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int idx, int prev) {
        // Base Case
        if (idx == n) {
            return 1;
        }
        // Recursion Calls
        int count = 0;
        for (int i = 1; i <= m; i++) { // TC : O(m)
            if (prev == -1 || (i % prev == 0 || prev % i == 0)) {
                // we can include 'i'
                count += solveRecursion(idx + 1, i);
            }
        }
        return count;
    }
}
