class Solution {
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n x m)
     * SC : O(n x m) + O(n)
     *  - O(n x m) - memoization memory
     *  - O(n) - recursion stack
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int maxDotProduct(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int[][] memo = new int[n][m]; // SC : O(n x m)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, 0, n, m, a, b, memo);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n x m)
     * SC : O(n)
     */
    private int solveMemoization(int i, int j, int n, int m,
        int[] a, int[] b, int[][] memo) {
        // Base Case
        if (j == m) {
            return 0;
        }
        if (i == n) {
            return j == m ? 0 : Integer.MIN_VALUE / 2;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int skip = solveMemoization(i + 1, j, n, m, a, b, memo);
        int pick = a[i] * b[j] + solveMemoization(i + 1, j + 1, n, m, a, b, memo);
        return memo[i][j] = Math.max(skip, pick);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     *  - O(n) - recursion stack
     * 
     * Time Limit Exceeded (11 / 1115 testcases passed)
     */
    public int maxDotProductRecursion(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        return solveRecursion(0, 0, n, m, a, b);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int i, int j, int n, int m, int[] a, int[] b) {
        // Base Case
        if (j == m) {
            return 0;
        }
        if (i == n) {
            return j == m ? 0 : Integer.MIN_VALUE / 2;
        }
        // Recursion Calls
        int skip = solveRecursion(i + 1, j, n, m, a, b);
        int pick = a[i] * b[j] + solveRecursion(i + 1, j + 1, n, m, a, b);
        return Math.max(skip, pick);
    }
}
