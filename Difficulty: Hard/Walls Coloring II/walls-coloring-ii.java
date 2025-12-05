class Solution {
    private int n;
    private int k;
    
    /**
     * Approach III : Using Tabulation Approach
     * 
     * TC: O(K x K x N) + O(K) + O(K) ~ O(K x K x N)
     * SC: O(N x K)
     * O(N x K) - memoization memory
     * 
     * Time Limit Exceeded (1108 / 1119 testcases passed)
     */
    int minCost(int[][] costs) {
        this.n = costs.length;
        this.k = costs[0].length;
        if (k == 1) {
            return n == 1 ? costs[0][0] : -1;
        }
        int[][] dp = new int[n][k]; // SC: O(N x K)
        // we will find the cost of painting 0th wall if painted with jth color
        for (int j = 0; j < k; j++) { // TC: O(K)
            dp[0][j] = costs[0][j];
        }
        for (int i = 1; i < n; i++) { // TC: O(N)
            for (int j = 0; j < k; j++) { // TC: O(K)
                int minPrevCost = Integer.MAX_VALUE;
                for (int p = 0; p < k; p++) { // TC: O(K)
                    if (p != j) {
                        minPrevCost = Math.min(minPrevCost, dp[i - 1][p]);
                    }
                }
                dp[i][j] = costs[i][j] + minPrevCost;
            }
        }
        int minimumCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) { // TC: O(K)
            minimumCost = Math.min(minimumCost, dp[n - 1][j]);
        }
        return minimumCost;
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(K x K x N)
     * SC: O(N x K) + O(N)
     * O(N x K) - memoization memory
     * O(N) - recursion stack
     * 
     * Time Limit Exceeded (1108 / 1119 testcases passed)
     */
    int minCostMemoization(int[][] costs) {
        this.n = costs.length;
        this.k = costs[0].length;
        if (k == 1) {
            return n == 1 ? costs[0][0] : -1;
        }
        int[][] memo = new int[n][k + 1]; // SC: O(N x K)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, -1, costs, memo);
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(K x K x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int prevColIdx,
        int[][] costs, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][prevColIdx + 1] != -1) {
            return memo[idx][prevColIdx + 1];
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) { // TC: O(K)
            if (prevColIdx != j) {
                int currentCost = costs[idx][j] + 
                    solveMemoization(idx + 1, n, j, costs, memo);
                minCost = Math.min(minCost, currentCost);
            }
        }
        return memo[idx][prevColIdx + 1] = minCost;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(K ^ N)
     * SC: O(N)
     * O(N) - recursion stack
     * 
     * Time Limit Exceeded (8 / 1119 testcases passed)
     */
    int minCostRecursion(int[][] costs) {
        this.n = costs.length;
        this.k = costs[0].length;
        if (k == 1) {
            return n == 1 ? costs[0][0] : -1;
        }
        return solveRecursion(0, n, -1, costs);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(K ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int prevColIdx, int[][] costs) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) { // TC: O(K)
            if (prevColIdx != j) {
                int currentCost = costs[idx][j] + solveRecursion(idx + 1, n, j, costs);
                minCost = Math.min(minCost, currentCost);
            }
        }
        return minCost;
    }
}
