class Solution {
    private int m;
    private int n;
    private int[][] suffix;
    private static final int MOD = (int) 1e9 + 7;
    
    /**
     * Approach III : Using Pre-Processing + Tabulation (Bottom-Up) Approach
     * 
     * TC : O((m x n x k x (m + n))
     * SC : O(k) + O(m x n)
     * - O(k) - recursion stack
     * - O(m x n) - suffix, nextRow, nextCol matrix memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int findWays(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        // suffix[i][j] = number of ones in submatrix (i,j) -> (m-1,n-1)
        int[][] suffix = new int[m + 1][n + 1]; // SC : O(m x n)
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                suffix[i][j] = matrix[i][j]
                        + suffix[i + 1][j]
                        + suffix[i][j + 1]
                        - suffix[i + 1][j + 1];
            }
        }
        if (suffix[0][0] < k) {
            return 0;
        }
        // next valid horizontal cut
        int[][] nextRow = new int[m][n]; // SC : O(m x n)
        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                nextRow[i][j] = m;
                if (suffix[i][j] > suffix[i + 1][j]) {
                    nextRow[i][j] = i + 1;
                } else if (i + 1 < m) {
                    nextRow[i][j] = nextRow[i + 1][j];
                }
            }
        }
        // next valid vertical cut
        int[][] nextCol = new int[m][n]; // SC : O(m x n)
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                nextCol[i][j] = n;
                if (suffix[i][j] > suffix[i][j + 1]) {
                    nextCol[i][j] = j + 1;
                } else if (j + 1 < n) {
                    nextCol[i][j] = nextCol[i][j + 1];
                }
            }
        }
        // Base DP (1 piece)
        int[][] dp = new int[m + 1][n + 1]; // SC : O(m x n)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = suffix[i][j] > 0 ? 1 : 0;
            }
        }
        for (int pieces = 2; pieces <= k; pieces++) {
            int[][] rowSuffix = new int[m + 1][n + 1];
            int[][] colSuffix = new int[m + 1][n + 1];
            // suffix sums over rows and columns
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    rowSuffix[i][j] = (dp[i][j] + rowSuffix[i + 1][j]) % MOD;
                    colSuffix[i][j] = (dp[i][j] + colSuffix[i][j + 1]) % MOD;
                }
            }
            int[][] current = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (suffix[i][j] < pieces) {
                        continue;
                    }
                    long ways = 0;
                    if (nextRow[i][j] < m) {
                        ways += rowSuffix[nextRow[i][j]][j];
                    }
                    if (nextCol[i][j] < n) {
                        ways += colSuffix[i][nextCol[i][j]];
                    }
                    current[i][j] = (int) (ways % MOD);
                }
            }
            dp = current;
        }
        return dp[0][0];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O((m x n x k x (m + n))
     * SC : O(k) + O(m x n)
     * - O(k) - recursion stack
     * - O(m x n) - suffix matrix memory
     * - O(m x n x k) - memoization memory
     * 
     * Time Limit Exceeded (1110 / 1111 testcases passed)
     */
    public int findWaysMemoization(int[][] matrix, int k) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.suffix = new int[m][n]; // SC : O(m x n)
        for (int i = m - 1; i >= 0; i--) { // TC : O(m)
            for (int j = n - 1; j >= 0; j--) { // TC : O(n)
                suffix[i][j] = matrix[i][j] +
                    (i < m - 1 ? suffix[i + 1][j] : 0) +
                    (j < n - 1 ? suffix[i][j + 1] : 0) -
                    (i < m - 1 && j < n - 1 ? suffix[i + 1][j + 1] : 0);
            }
        }
        int[][][] memo = new int[m][n][k + 1]; // SC : O(m x n x k)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, 0, k, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O((m + n) ^ k)
     * SC : O(k)
     */
    private int solveMemoization(int i, int j, int k, int[][][] memo) {
        // Base Case
        if (k == 1) {
            return suffix[i][j] > 0 ? 1 : 0;
        }
        if (suffix[i][j] == 0 || suffix[i][j] < k) {
            // we cannot split more k partitions further
            return 0;
        }
        // Memoization Check
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        // Recursion Calls
        int ways = 0;
        // horizontal cut
        for (int row = i + 1; row < m; row++) { // TC : O(m)
            if (suffix[i][j] > suffix[row][j]) {
                ways = 
                    (ways + solveMemoization(row, j, k - 1, memo)) % MOD;
            }
        }
        // vertical cut
        for (int col = j + 1; col < n; col++) { // TC : O(n)
            if (suffix[i][j] > suffix[i][col]) {
                ways =
                    (ways + solveMemoization(i, col, k - 1, memo)) % MOD;
            }
        }
        return memo[i][j][k] = ways % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O((m + n) ^ k)
     * SC : O(k) + O(m x n)
     * - O(k) - recursion stack
     * - O(m x n) - suffix matrix memory
     * 
     * Time Limit Exceeded (1011 / 1111 testcases passed)
     */
    public int findWaysRecursion(int[][] matrix, int k) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.suffix = new int[m][n]; // SC : O(m x n)
        for (int i = m - 1; i >= 0; i--) { // TC : O(m)
            for (int j = n - 1; j >= 0; j--) { // TC : O(n)
                suffix[i][j] = matrix[i][j] +
                    (i < m - 1 ? suffix[i + 1][j] : 0) +
                    (j < n - 1 ? suffix[i][j + 1] : 0) -
                    (i < m - 1 && j < n - 1 ? suffix[i + 1][j + 1] : 0);
            }
        }
        return solveRecursion(0, 0, k);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O((m + n) ^ k)
     * SC : O(k)
     */
    private int solveRecursion(int i, int j, int k) {
        // Base Case
        if (k == 1) {
            return suffix[i][j] > 0 ? 1 : 0;
        }
        // Recursion Calls
        int ways = 0;
        // horizontal cut
        for (int row = i + 1; row < m; row++) { // TC : O(m)
            if (suffix[i][j] > suffix[row][j]) {
                ways = 
                    (ways + solveRecursion(row, j, k - 1)) % MOD;
            }
        }
        // vertical cut
        for (int col = j + 1; col < n; col++) { // TC : O(n)
            if (suffix[i][j] > suffix[i][col]) {
                ways =
                    (ways + solveRecursion(i, col, k - 1)) % MOD;
            }
        }
        return ways % MOD;
    }
}
