class Solution {
    private int n;
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n⁴)
     * SC : O(n²) + O(n)
     * - O(n) - recursion stack
     * - O(n²) - memoization memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int zigzagSequence(int[][] mat) {
        this.n = mat.length;
        int maxValue = Integer.MIN_VALUE;
        int[][] memo = new int[n][n]; // SC : O(n²)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        for (int j = 0; j < n; j++) { // TC : O(n)
            maxValue = Math.max(maxValue, 
                solveMemoization(0, j, mat, memo) // TC : O(n³), SC : O(n)
            );
        }
        return maxValue;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n³)
     * SC : O(n)
     */
    private int solveMemoization(int i, int j, int[][] mat, int[][] memo) {
        // Base Case
        if (i == n - 1) {
            return mat[i][j];
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        /**
         * from a cell (i, j) we can move to two 
         * cells (i + 1, j - 1) or (i + 1, j + 1)
         */
        int maxOption = Integer.MIN_VALUE;
        for (int col = 0; col < n; col++) { // TC : O(n)
            if (j == col) {
                // cannot pick the same consecutive column
                continue;
            }
            maxOption = Math.max(maxOption,
                solveMemoization(i + 1, col, mat, memo));
        }
        return memo[i][j] = mat[i][j] + maxOption;
    }

     /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n x nⁿ) ~ O(nⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1111 testcases passed)
     */
    public int zigzagSequenceRecursion(int[][] mat) {
        this.n = mat.length;
        int maxValue = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) { // TC : O(n)
            maxValue = Math.max(maxValue, 
                solveRecursion(0, j, mat) // TC : O(nⁿ), SC : O(n)
            );
        }
        return maxValue;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(nⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int i, int j, int[][] mat) {
        // Base Case
        if (i == n - 1) {
            return mat[i][j];
        }
        // Recursion Calls
        /**
         * from a cell (i, j) we can move to two 
         * cells (i + 1, j - 1) or (i + 1, j + 1)
         */
        int maxOption = Integer.MIN_VALUE;
        for (int col = 0; col < n; col++) { // TC : O(n)
            if (j == col) {
                // cannot pick the same consecutive column
                continue;
            }
            maxOption = Math.max(maxOption, solveRecursion(i + 1, col, mat));
        }
        return mat[i][j] + maxOption;
    }
}
