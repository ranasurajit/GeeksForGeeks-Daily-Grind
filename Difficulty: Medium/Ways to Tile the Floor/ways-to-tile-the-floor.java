class Solution {
    private static final int MOD = (int) 1e9 + 7;
    
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     * 
     * TC : O(n)
     * SC : O(n)
     *      - O(n) - memoization memory
     * 
     * Accepted (1111 /1111 testcases passed)
     */
    public int countWays(int n, int m) {
        if (n < m) {
            /**
             * tiles would not fit the height (so only 
             * horizontal place is possible) so return 1
             */
            return 1;
        }
        if (n == m) {
            // place all either vertically or horizontally
            return 2;
        }
        long[] dp = new long[n + 1]; // SC : O(n)
        /**
         * Base Case 1 : n < m
         */
        for (int i = 0; i < m; i++) {
            dp[i] = 1L;
        }
        /**
         * Base Case 2 : n == m
         */
        dp[m] = 2L;
        for (int i = m + 1; i <= n; i++) { // TC : O(n)
            /**
             * Option 1: Place first tile (1 x m) horizontally.
             * 
             * This leaves (n - 1) rows so we can rely on previous states
             */
            long option1 = dp[i - 1] % MOD;
            /**
             * Option 2: Place the first tile vertically.
             *
             * This leaves the first m rows with (m - 1) columns remaining.
             * Since a horizontal tile requires all m columns, it can no longer
             * fit inside this partially filled block.
             *
             * Therefore, we are forced to place the remaining (m - 1) tiles
             * vertically as well, completely filling the first (m x m) block.
             *
             * The remaining floor becomes a rectangle of size
             * (n - m) x m, so the answer is solveRecursion(n - m, m).
             */
            long option2 = dp[i - m] % MOD;
            dp[i] = (option1 + option2) % MOD;
        }
        return (int) (dp[n] % MOD);
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n)
     * SC : O(n) + O(n)
     *      - O(n) - memoization memory
     *      - O(n) - recursion stack
     * 
     * Accepted (1111 /1111 testcases passed)
     */
    public int countWaysMemoization(int n, int m) {
        long[] memo = new long[n + 1]; // SC : O(n)
        Arrays.fill(memo, -1L);
        return (int) (solveMemoization(n, m, memo) % MOD);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private long solveMemoization(int n, int m, long[] memo) {
        // Base Case
        if (n < m) {
            /**
             * tiles would not fit the height (so only 
             * horizontal place is possible) so return 1
             */
            return 1;
        }
        if (n == m) {
            // place all either vertically or horizontally
            return 2;
        }
        // Memoization Check
        if (memo[n] != -1L) {
            return memo[n];
        }
        // Recursion Calls - n > m
        /**
         * Option 1: Place first tile (1 x m) horizontally.
         * 
         * This leaves (n - 1) rows so we can rely on previous states
         */
        long option1 = solveMemoization(n - 1, m, memo) % MOD;
        /**
         * Option 2: Place the first tile vertically.
         *
         * This leaves the first m rows with (m - 1) columns remaining.
         * Since a horizontal tile requires all m columns, it can no longer
         * fit inside this partially filled block.
         *
         * Therefore, we are forced to place the remaining (m - 1) tiles
         * vertically as well, completely filling the first (m x m) block.
         *
         * The remaining floor becomes a rectangle of size
         * (n - m) x m, so the answer is solveRecursion(n - m, m).
         */
        long option2 = solveMemoization(n - m, m, memo) % MOD;
        return memo[n] = (option1 + option2) % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2 ^ n)
     * SC : O(n)
     *      - O(n) - recursion stack
     * 
     * Time limit exceeded (1068 /1111 testcases passed)
     */
    public int countWaysRecursion(int n, int m) {
        return (int) (solveRecursion(n, m) % MOD);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2 ^ n)
     * SC : O(n)
     */
    private long solveRecursion(int n, int m) {
        // Base Case
        if (n < m) {
            /**
             * tiles would not fit the height (so only 
             * horizontal place is possible) so return 1
             */
            return 1;
        }
        if (n == m) {
            // place all either vertically or horizontally
            return 2;
        }
        // Recursion Calls - n > m
        /**
         * Option 1: Place first tile (1 x m) horizontally.
         * 
         * This leaves (n - 1) rows so we can rely on previous states
         */
        long option1 = solveRecursion(n - 1, m) % MOD;
        /**
         * Option 2: Place the first tile vertically.
         *
         * This leaves the first m rows with (m - 1) columns remaining.
         * Since a horizontal tile requires all m columns, it can no longer
         * fit inside this partially filled block.
         *
         * Therefore, we are forced to place the remaining (m - 1) tiles
         * vertically as well, completely filling the first (m x m) block.
         *
         * The remaining floor becomes a rectangle of size
         * (n - m) x m, so the answer is solveRecursion(n - m, m).
         */
        long option2 = solveRecursion(n - m, m) % MOD;
        return (option1 + option2) % MOD;
    }
}
