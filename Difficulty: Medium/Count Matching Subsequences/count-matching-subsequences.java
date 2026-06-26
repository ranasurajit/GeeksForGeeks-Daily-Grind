class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach IV : Using Space-Optimization (Optimized DP) Approach
     * 
     * TC : O(m x n)
     * SC : O(n) + O(n) ~ O(n)
     *  - O(n) - 'next' and 'current' array memory
     * 
     * Accepted (1117 /1117 testcases passed)
     */
    public static int countWays(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // Initialization
        long[] next = new long[n + 1]; // SC : O(n)
        long[] current = new long[n + 1]; // SC : O(n)
        for (int i = 0; i <= m; i++) {
            next[n] = 1L;
            current[n] = 1L;
        }
        // Iterative Calls
        for (int i = m - 1; i >= 0; i--) {     // TC : O(m)
            for (int j = n - 1; j >= 0; j--) { // TC : O(n)
                long skip = (next[j]) % MOD;
                long pick = 0L;
                if (s1.charAt(i) == s2.charAt(j)) {
                    pick = (next[j + 1]) % MOD;
                }
                current[j] = (pick + skip) % MOD;
            }
            next = current.clone();
        }
        return (int) (next[0] % MOD);
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     * 
     * TC : O(m x n)
     * SC : O(m x n)
     *  - O(m x n) - dp array memory
     * 
     * Accepted (1117 /1117 testcases passed)
     */
    public static int countWaysTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // Initialization
        long[][] dp = new long[m + 1][n + 1]; // SC : O(m x n)
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1L;
        }
        // Iterative Calls
        for (int i = m - 1; i >= 0; i--) {     // TC : O(m)
            for (int j = n - 1; j >= 0; j--) { // TC : O(n)
                long skip = (dp[i + 1][j]) % MOD;
                long pick = 0L;
                if (s1.charAt(i) == s2.charAt(j)) {
                    pick = (dp[i + 1][j + 1]) % MOD;
                }
                dp[i][j] = (pick + skip) % MOD;
            }
        }
        return (int) (dp[0][0] % MOD);
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(m x n)
     * SC : O(m x n) + O(m + n)
     *  - O(m x n) - memoization memory
     *  - O(m + n) - recursion stack
     * 
     * Accepted (1117 /1117 testcases passed)
     */
    public static int countWaysMemoization(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        long[][] memo = new long[m][n]; // SC : O(m x n)
        for (long[] mem : memo) {
            Arrays.fill(mem, -1L);
        }
        long result =
            solveMemoization(0, 0, m, n, s1, s2, memo); // TC : O(m x n)
        return (int) (result % MOD);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(m x n)
     * SC : O(m + n)
     */
    private static long solveMemoization(int idx1, int idx2, int m,
        int n, String s1, String s2, long[][] memo) {
        // Base Case
        if (idx2 == n) {
            return 1L;
        }
        if (idx1 >= m) {
            return 0;
        }
        // Memoization Check
        if (memo[idx1][idx2] != -1L) {
            return memo[idx1][idx2];
        }
        // Recursion Calls
        // we can pick or skip
        long pick = 0L;
        long skip = solveMemoization(idx1 + 1, idx2, m, n, s1, s2, memo) % MOD;
        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            pick = solveMemoization(idx1 + 1, idx2 + 1, m, n, s1, s2, memo) % MOD;
        }
        return memo[idx1][idx2] = pick + skip;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     *  - O(m + n) - recursion stack
     * 
     * Time limit exceeded (1017 /1117 testcases passed)
     */
    public static int countWaysRecursion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        return solveRecursion(0, 0, m, n, s1, s2);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     */
    private static int solveRecursion(int idx1, int idx2, int m,
        int n, String s1, String s2) {
        // Base Case
        if (idx2 == n) {
            return 1;
        }
        if (idx1 >= m) {
            return 0;
        }
        // Recursion Calls
        // we can pick or skip
        int pick = 0;
        int skip = solveRecursion(idx1 + 1, idx2, m, n, s1, s2);
        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            pick = solveRecursion(idx1 + 1, idx2 + 1, m, n, s1, s2);
        }
        return pick + skip;
    }
}
