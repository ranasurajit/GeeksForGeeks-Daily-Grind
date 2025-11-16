class Solution {
    private int m;
    private int n;

    /**
     * Approach III : Using Tabulation Approach
     * 
     * TC: O(M x N)
     * SC: O(N)
     * 
     * - O(N) - dp memory
     * 
     * Accepted (1010 /1070 testcases passed)
     */
    public int LCIS(int[] a, int[] b) {
        this.m = a.length;
        this.n = b.length;
        int[] dp = new int[n]; // SC: O(N) - where dp[j] denotes LCIS ending at b[j]
        int maxLength = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            int best = 0; // best LCIS length for values < a[i]
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (a[i] > b[j]) {
                    // we can extend dp[j] later when we find a[i] == b[j]
                    best = Math.max(best, dp[j]);
                } else if (a[i] == b[j]) {
                    // match found so update dp[j]
                    dp[j] = 1 + best;
                    maxLength = Math.max(maxLength, dp[j]);
                }
            }
        }
        return maxLength;
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(M x N x M)
     * SC: O(M x N x M) + O(M)
     * 
     * - O(M x N x M) - memoization memory
     * - O(M) - recursion stack
     * 
     * Memory Limit Exceeded (1010 /1070 testcases passed)
     */
    public int LCISMemoization(int[] a, int[] b) {
        this.m = a.length;
        this.n = b.length;
        int[][][] memo = new int[m + 1][n + 1][m + 2]; // SC: O(M x N)
        for (int[][] mem : memo) {
            for (int[] me : mem) {
                Arrays.fill(me, -1);
            }
        }
        return solveMemoization(0, 0, -1, a, b, memo); // TC: O(M x N x M), SC: O(M)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(M x N x M)
     * SC: O(M)
     */
    private int solveMemoization(int idx1, int idx2, int prevIdx, 
        int[] a, int[] b, int[][][] memo) {
        // Base Case
        if (idx1 >= m || idx2 >= n) {
            // one of the arrays got exhaused
            return 0;
        }
        // Memoization Check
        if (memo[idx1][idx2][prevIdx + 1] != -1) {
            return memo[idx1][idx2][prevIdx + 1];
        }
        // Recursion Calls
        // skip
        int skip = solveMemoization(idx1 + 1, idx2, prevIdx, a, b, memo);
        int pick = 0;
        // pick
        for (int j = idx2; j < n; j++) { // TC: O(N)
            if (a[idx1] == b[j] && (prevIdx == -1 || a[idx1] > a[prevIdx])) {
                pick = Math.max(pick,
                    1 + solveMemoization(idx1 + 1, j + 1, idx1, a, b, memo));
                break; // once we have a match we can break to reduce redundant branches
            }
        }
        return memo[idx1][idx2][prevIdx + 1] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(M x N x Min(M, N))
     * SC: O(M)
     * 
     * - O(M) - recursion stack
     * 
     * Time Limit Exceeded (1010 /1070 testcases passed)
     */
    public int LCISRecursion(int[] a, int[] b) {
        this.m = a.length;
        this.n = b.length;
        return solveRecursion(0, 0, -1, a, b); // TC: O(M x N x Min(M, N)), SC: O(M)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(M x N x Min(M, N))
     * SC: O(M)
     */
    private int solveRecursion(int idx1, int idx2, int prevNum, int[] a, int[] b) {
        // Base Case
        if (idx1 >= m || idx2 >= n) {
            // one of the arrays got exhaused
            return 0;
        }
        // Recursion Calls
        // skip
        int skip = solveRecursion(idx1 + 1, idx2, prevNum, a, b);
        int pick = 0;
        // pick
        for (int j = idx2; j < n; j++) { // TC: O(N)
            if (a[idx1] == b[j] && a[idx1] > prevNum) {
                pick = Math.max(pick, 1 + solveRecursion(idx1 + 1, j + 1, a[idx1], a, b));
                break; // once we have a match we can break to reduce redundant branches
            }
        }
        return Math.max(pick, skip);
    }
}
