class Solution {
    /**
     * Approach : Using Partition DP - Tabulation with Parenthesis Reconstruction Approach
     * 
     * TC: O(N x N x N)
     * SC: O(N x N) + O(N x N) ~ O(N x N)
     */
    public String matrixChainOrder(int arr[]) {
        int n = arr.length;
        // Initialization
        int[][] dp = new int[n][n]; // SC: O(N x N)
        int[][] split = new int[n][n]; // SC: O(N x N)
        // Iterative Calls
        for (int i = n - 1; i >= 1; i--) {         // TC: O(N)
            for (int j = i + 1; j < n; j++) {      // TC: O(N)
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) { // TC: O(N)
                    int cost = dp[i][k] + 
                        arr[i - 1] * arr[k] * arr[j] + 
                        dp[k + 1][j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        split[i][j] = k; // for tracking split 'k'
                    }
                }
            }
        }
        return buildChainOrder(1, n - 1, split);
    }
    
    private String buildChainOrder(int i, int j, int[][] split) {
        if (i == j) {
            return String.valueOf((char) ('A' + i - 1));
        }
        int k = split[i][j];
        return "(" + buildChainOrder(i, k, split) + buildChainOrder(k + 1, j, split) + ")";
    }

    /**
     * Classic MCM - Using Partition DP - Tabulation Approach
     * 
     * TC: O(N x N x N)
     * SC: O(N x N)
     */
    public int mcmTabulation(int arr[]) {
        int n = arr.length;
        // Initialization
        int[][] dp = new int[n][n]; // SC: O(N x N)
        // Iterative Calls
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int currentCost = arr[i - 1] * arr[k] * arr[j];
                    minCost = Math.min(minCost, dp[i][k] + currentCost + dp[k + 1][j]);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][n - 1];
    }

    /**
     * Classic MCM - Using Partition DP - Memoization Approach
     * 
     * TC: O(N x N x N)
     * SC: O(N x N) + O(N)
     */
    public int mcmMemoization(int arr[]) {
        int n = arr.length;
        int[][] memo = new int[n][n]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        int minCost = solve(1, n - 1, arr, memo);
        return minCost;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x N x N)
     * SC: O(N)
     */
    private int solve(int i, int j, int[] arr, int[][] memo) {
        // Base Case
        if (i >= j) {
            // invalid matrix
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int currentCost = arr[i - 1] * arr[k] * arr[j];
            minCost = Math.min(minCost, 
                solve(i, k, arr, memo) + 
                currentCost + 
                solve(k + 1, j, arr, memo)
            );
        }
        return memo[i][j] = minCost;
    }
}
