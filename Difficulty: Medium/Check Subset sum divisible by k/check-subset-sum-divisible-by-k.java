class Solution {
    private int n;
    private int k;

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC : O(n x k) + O(k) ~ O(n x k)
     * SC : O(k) + O(k) ~ O(k)
     *  - O(k) - 'next' and 'current' array memory
     * 
     * Accepted (1116 / 1116 testcases passed)
     */
    public boolean divisibleByK(int[] arr, int k) {
        this.n = arr.length;
        this.k = k;
        boolean[][] next = new boolean[k][2]; // SC : O(k)
        for (int j = 0; j < k; j++) { // TC : O(k)
            next[j][0] = false; // empty subset not allowed
            next[j][1] = j == 0;
        }
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            boolean[][] current = new boolean[k][2]; // SC : O(k)
            for (int j = 0; j < k; j++) {  // TC : OP(k)
                // picked 0
                boolean skip = next[j][0];
                boolean pick = next[(j + arr[i]) % k][1];
                current[j][0] = skip || pick;
                
                // picked atleast 1
                skip = next[j][1];
                pick = next[(j + arr[i]) % k][1];
                current[j][1] = skip || pick;
            }
            next = current.clone();
        }
        return next[0][0];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     * 
     * TC : O(n x k) + O(k) ~ O(n x k)
     * SC : O(n x k)
     *  - O(n x k) - 'dp' array memory
     * 
     * Time Limit Exceeded (1115 / 1116 testcases passed)
     */
    public boolean divisibleByKTabulation(int[] arr, int k) {
        this.n = arr.length;
        this.k = k;
        boolean[][][] dp = new boolean[n + 1][k][2]; // SC : O(n x k)
        for (int j = 0; j < k; j++) { // TC : O(k)
            dp[n][j][0] = false; // empty subset not allowed
            dp[n][j][1] = j == 0;
        }
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            for (int j = 0; j < k; j++) {  // TC : OP(k)
                // picked 0
                boolean skip = dp[i + 1][j][0];
                boolean pick = dp[i + 1][(j + arr[i]) % k][1];
                dp[i][j][0] = skip || pick;
                
                // picked atleast 1
                skip = dp[i + 1][j][1];
                pick = dp[i + 1][(j + arr[i]) % k][1];
                dp[i][j][1] = skip || pick;
            }
        }
        return dp[0][0][0];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n x k)
     * SC : O(n x k) + O(n)
     *  - O(n x k) - memoization memory
     *  - O(n) - recursion stack
     * 
     * Time Limit Exceeded (1115 / 1116 testcases passed)
     */
    public boolean divisibleByKMemoization(int[] arr, int k) {
        this.n = arr.length;
        this.k = k;
        int[][][] memo = new int[n][k][2]; // SC : O(n x k)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, 0, 0, arr, memo) == 1;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n x k x 2) ~ O(n x k)
     * SC : O(n)
     */
    private int solveMemoization(int idx, int count,
        int rem, int[] arr, int[][][] memo) {
        // Base Case
        if (idx == n) {
            return count != 0 && rem == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[idx][rem][count] != -1) {
            return memo[idx][rem][count];
        }
        // Recursion Calls
        // pick or skip
        int skip =
            solveMemoization(idx + 1, count, rem, arr, memo);
        if (skip == 1) {
            return memo[idx][rem][count] = 1;
        }
        int pick =
            solveMemoization(idx + 1, 1, (rem + arr[idx]) % k, arr, memo);
        if (pick == 1) {
            return memo[idx][rem][count] = 1;
        }
        return memo[idx][rem][count] = 0;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     *  - O(n) - recursion stack
     * 
     * Time Limit Exceeded (11 / 1116 testcases passed)
     */
    public boolean divisibleByKRecursion(int[] arr, int k) {
        this.n = arr.length;
        this.k = k;
        return solveRecursion(0, 0, 0, arr);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private boolean solveRecursion(int idx, int count, int rem, int[] arr) {
        // Base Case
        if (idx == n) {
            return count != 0 && rem == 0;
        }
        // Recursion Calls
        // pick or skip
        boolean skip = solveRecursion(idx + 1, count, rem, arr);
        boolean pick = solveRecursion(idx + 1, count + 1, (rem + arr[idx]) % k, arr);
        return skip || pick;
    }
}
