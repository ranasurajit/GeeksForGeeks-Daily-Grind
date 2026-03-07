class Solution {
    /**
     * Approach II : Using Better (Memoization) Approach
     * 
     * TC: O(n x m x t)
     * SC: O(n) + O(n x t)
     * 
     * - O(n) - recursion stack
     * - O(n x t) - memoization array
     */
    static int noOfWays(int m, int n, int x) {
        int[][] memo = new int[n][x + 1]; // SC: O(n x t)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, x, m , n, memo); // TC: O(n x m x t), SC: O(n)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(n x m x t)
     * SC: O(n)
     * 
     * - O(n) - recursion stack
     */
    private static int solveMemoization(int idx, int x, int m, int n, int[][] memo) {
        // Base Case
        if (idx == n) {
            // if sum remaining is 0 then we got 1 way
            return x == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[idx][x] != -1) {
            return memo[idx][x];
        }
        // Recursion Calls
        int ways = 0;
        for (int f = 1; f <= m; f++) { // TC: O(m)
            if (f <= x) {
                // we can count the dice with index 'idx' if face value <= x
                ways += solveMemoization(idx + 1, x - f, m , n, memo);
            }
        }
        return memo[idx][x] = ways;
    }

    /**
     * Approach I : Using Brute-Force (Recursion) Approach
     * 
     * TC: O(mⁿ)
     * SC: O(n)
     * 
     * - O(n) - recursion stack
     */
    static int noOfWaysRecursion(int m, int n, int x) {
        return solveRecursion(0, x, m , n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(mⁿ)
     * SC: O(n)
     * 
     * - O(n) - recursion stack
     */
    private static int solveRecursion(int idx, int x, int m, int n) {
        // Base Case
        if (idx == n) {
            // if sum remaining is 0 then we got 1 way
            return x == 0 ? 1 : 0;
        }
        // Recursion Calls
        int ways = 0;
        for (int f = 1; f <= m; f++) { // TC: O(m)
            if (f <= x) {
                // we can count the dice with index 'idx' if face value <= x
                ways += solveRecursion(idx + 1, x - f, m , n);
            }
        }
        return ways;
    }
};
