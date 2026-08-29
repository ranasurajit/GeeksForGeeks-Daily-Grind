class Solution {
    private int m;
    private static final int MOD = (int) 1e9 + 7;

    /*
     * Approach III : Using Tabulation Approach
     * 
     * TC : O(m x n)
     * SC : O(m) + O(m x n)
     * - O(n) - dp memory
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int countSubsequences(String s, int n) {
        long[] dp = new long[n];
        for (char ch : s.toCharArray()) {
            int digit = ch - '0';
            // Copy old states.
            // This represents SKIP.
            long[] next = dp.clone();
            // PICK current digit as a new subsequence
            int newRemainder = digit % n;
            next[newRemainder] =
                (next[newRemainder] + 1) % MOD;
            // PICK current digit and append it
            // to every existing subsequence
            for (int remainder = 0; remainder < n; remainder++) {
                if (dp[remainder] == 0) {
                    continue;
                }
                int remainderAfterPick =
                    (remainder * 10 + digit) % n;
                next[remainderAfterPick] =
                    (next[remainderAfterPick]
                    + dp[remainder]) % MOD;
            }
            dp = next;
        }
        return (int) dp[0];
    }
    
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC : O(m x n)
     * SC : O(m) + O(m x n)
     * - O(m) - recursion stack
     * - O(m x n) - memoization memory
     * 
     * Time Limit Exceeded (1101 / 1115 testcases passed)
     */
    public int countSubsequencesMemoization(String s, int n) {
        this.m = s.length();
        int[][][] memo = new int[m][n][2]; // SC : O(m x n) ~ O(m)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, 0, s, n, 0, memo);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(m x n x 2) ~ O(m x n)
     * SC : O(m)
     */
    private int solveMemoization(int idx, int remainder,
        String s, int n, int picked, int[][][] memo) {
        // Base Case
        if (idx == m) {
            return picked == 1 && remainder == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[idx][remainder][picked] != -1) {
            return memo[idx][remainder][picked];
        }
        // Recursion Calls
        // skip or pick
        int skip = solveMemoization(idx + 1, remainder, s, n, picked, memo);
        int digit = s.charAt(idx) - '0';
        int newRemainder = ((remainder * 10) + digit) % n;
        int pick = solveMemoization(idx + 1, newRemainder, s, n, 1, memo);
        return memo[idx][remainder][picked] = (skip + pick) % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (26 / 1115 testcases passed)
     */
    public int countSubsequencesRecursion(String s, int n) {
        this.m = s.length();
        return solveRecursion(0, 0, s, n, 0);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int idx, int remainder, String s, int n, int picked) {
        // Base Case
        if (idx == m) {
            return picked == 1 && remainder == 0 ? 1 : 0;
        }
        // Recursion Calls
        // skip or pick
        int skip = solveRecursion(idx + 1, remainder, s, n, picked);
        int digit = s.charAt(idx) - '0';
        int newRemainder = ((remainder * 10) + digit) % n;
        int pick = solveRecursion(idx + 1, newRemainder, s, n, 1);
        return (skip + pick) % MOD;
    }
}
