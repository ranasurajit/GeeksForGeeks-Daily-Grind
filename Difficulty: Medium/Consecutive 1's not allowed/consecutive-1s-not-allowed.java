class Solution {
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC: O(n)
     * SC: O(n) + O(n)
     * - O(n) - memoization array
     * - O(n) - recursion stack
     * 
     * Accepted (44 / 44 testcases passed)
     */
    int countStrings(int n) {
        int[] memo = new int[n + 1]; // SC: O(n)
        Arrays.fill(memo, -1);
        return solveMemoization(n, memo); // TC: O(n), SC: O(n)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(n)
     * SC: O(n)
     */
    private int solveMemoization(int n, int[] memo) {
        // Base Case
        if (n <= 2) {
            return n + 1;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        return memo[n] = solveMemoization(n - 1, memo) +
            solveMemoization(n - 2, memo);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (0 / 44 testcases passed)
     */
    int countStringsRecursion(int n) {
        return solveRecursion(n); // TC: O(2ⁿ), SC: O(n)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n)
     */
    private int solveRecursion(int n) {
        // Base Case
        if (n <= 2) {
            return n + 1;
        }
        // Recursion Calls
        return solveRecursion(n - 1) + solveRecursion(n - 2);
    }
}
