class Solution {
    /**
     * Approach I : Using Tabulation (Bottom-Up) Approach
     *
     * TC : O(m x n)
     * SC : O(m x n)
     */
    public int numberOfPaths(int m, int n) {
        int[][] dp = new int[m][n]; // SC : O(m x n)
        /**
         * dp[i][j] denotes count of unique paths
         * from cell (0, 0) to (i, j)
         * horizontally from (0, 0) to (0, j) 
         * there is only 1 way
         * vertically from (0, 0) to (i, 0) 
         * there is only 1 way
         *
         * dp[i][j] can be reached from dp[i - 1][j]
         * and dp[i][j - 1]
         */
        for (int j = 0; j < n; j++) { // TC : O(n)
            dp[0][j] = 1;
        }
        for (int i = 0; i < m; i++) { // TC : O(m)
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) { // TC : O(m)
            for (int j = 1; j < n; j++) { // TC : O(n)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
