class Solution {
    private int n;
    private int m;
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC: O(n x m x m)
     * SC: O(n x m x m) + O(n)
     * - O(n x m x m) - memoization memory
     * - O(n) - recursion stack
     * 
     * Accepted (1112 / 1112 testcases passed)
     */
    public int maxChocolate(int grid[][]) {
        this.n = grid.length;
        this.m = grid[0].length;
        int[][][] memo = new int[n][m][m]; // SC: O(n x m x m)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, 0, m - 1, grid, memo);
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(n x m x m)
     * SC: O(n)
     */
    private int solveMemoization(int i, int j1, int j2,
        int[][] grid, int[][][] memo) {
        // Base Case
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) {
            // invalid case
            return Integer.MIN_VALUE;
        }
        if (i == n - 1) {
            return j1 == j2 ? grid[i][j1] : grid[i][j1] + grid[i][j2];
        }
        // Memoization Check
        if (memo[i][j1][j2] != -1) {
            return memo[i][j1][j2];
        }
        // Recursion Calls
        /**
         * state at (i, j1, j2) represents maximum chocolates
         * collected by both robots at this state
         */
        int currentCollected =
            (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
        int maxCollected = Integer.MIN_VALUE;
        for (int p = -1; p <= 1; p++) { // TC: O(3)
            for (int q = -1; q <= 1; q++) { // TC: O(3)
                maxCollected = Math.max(maxCollected,
                    solveMemoization(i + 1, j1 + p, j2 + q, grid, memo));
            }
        }
        return memo[i][j1][j2] = currentCollected + maxCollected;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(9ⁿ)
     * SC: O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1112 testcases passed)
     */
    public int maxChocolateRecursion(int grid[][]) {
        this.n = grid.length;
        this.m = grid[0].length;
        return solveRecursion(0, 0, m - 1, grid);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(9ⁿ)
     * SC: O(n)
     */
    private int solveRecursion(int i, int j1, int j2, int[][] grid) {
        // Base Case
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) {
            // invalid case
            return Integer.MIN_VALUE;
        }
        if (i == n - 1) {
            return j1 == j2 ? grid[i][j1] : grid[i][j1] + grid[i][j2];
        }
        // Recursion Calls
        /**
         * state at (i, j1, j2) represents maximum chocolates
         * collected by both robots at this state
         */
        int currentCollected =
            (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
        int maxCollected = Integer.MIN_VALUE;
        for (int p = -1; p <= 1; p++) { // TC: O(3)
            for (int q = -1; q <= 1; q++) { // TC: O(3)
                maxCollected = Math.max(maxCollected,
                    solveRecursion(i + 1, j1 + p, j2 + q, grid));
            }
        }
        return currentCollected + maxCollected;
    }
}
