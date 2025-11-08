class Solution {
    private int n;
    private int m;
    
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x M x K)
     * SC: O(N x M x K) + O(N x M x K)
     * 
     * - O(N x M x K) - memoization memory
     * - O(N x M x K) - recursion stack
     * 
     * Accepted (1121 /1121 testcases passed)
     */
    public int numberOfPath(int[][] mat, int k) {
        this.n = mat.length;
        this.m = mat[0].length;
        int[][][] memo = new int[n][m][k + 1]; // SC: O(N x M x K)
        for (int[][] mem : memo) {
            for (int[] arr : mem) {
                Arrays.fill(arr, -1);
            }
        }
        return solveMemoization(n - 1, m - 1, k, mat, memo); // TC: O(N x M x K), SC: O(N x M x K)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x M x K)
     * SC: O(N x M x K)
     */
    private int solveMemoization(int i, int j, int k, int[][] mat, int[][][] memo) {
        // Base Case
        if (i < 0 || i >= n || j < 0 || j >= m) {
            // out of bounds
            return 0;
        }
        if (k < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return k == mat[0][0] ? 1 : 0;
        }
        // Memoization Check
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        // Recursion Calls
        int leftMove = 0;
        int upMove = 0;
        if (k >= mat[i][j]) {
            leftMove = solveMemoization(i, j - 1, k - mat[i][j], mat, memo);
            upMove = solveMemoization(i - 1, j, k - mat[i][j], mat, memo);
        }
        return memo[i][j][k] = leftMove + upMove;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ (N + M))
     * SC: O(N + M)
     * 
     * Time Limit Exceeded (1017 /1121 testcases passed)
     */
    public int numberOfPathRecursion(int[][] mat, int k) {
        this.n = mat.length;
        this.m = mat[0].length;
        return solveRecursion(n - 1, m - 1, k, mat);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ (N + M))
     * SC: O(N + M)
     */
    private int solveRecursion(int i, int j, int k, int[][] mat) {
        // Base Case
        if (i < 0 || i >= n || j < 0 || j >= m) {
            // out of bounds
            return 0;
        }
        if (k < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return k == mat[0][0] ? 1 : 0;
        }
        // Recursion Calls
        int leftMove = 0;
        int upMove = 0;
        if (k >= mat[i][j]) {
            leftMove = solveRecursion(i, j - 1, k - mat[i][j], mat);
            upMove = solveRecursion(i - 1, j, k - mat[i][j], mat);
        }
        return leftMove + upMove;
    }
}
