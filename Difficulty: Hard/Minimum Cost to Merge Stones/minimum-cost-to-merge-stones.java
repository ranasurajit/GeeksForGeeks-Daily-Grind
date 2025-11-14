class Solution {
    private static int[] prefixSum;
    
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: TC: O((N ^ 3) / K) + O(N) ~ O((N ^ 3) / K)
     * SC: O(N x N) + O(N)
     * 
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack space
     * 
     * Accepted (1120 / 1120 testcases passed)
     */
    public static int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            // merging k consecutive piles is not possible
            return -1;
        }
        prefixSum = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSum[i] = i > 0 ? (prefixSum[i - 1] + stones[i]) : stones[i];
        }
        int[][] memo = new int[n][n]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n - 1, k, memo); // TC: O((N ^ 3) / K), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O((N ^ 3) / K)
     * SC: O(N)
     */
    private static int solveMemoization(int i, int j, int k, int[][] memo) {
        // Base Case
        if (i >= j) {
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int p = i; p < j; p += (k - 1)) { // TC: O(N / K)
            minCost = Math.min(minCost,
                solveMemoization(i, p, k, memo) + solveMemoization(p + 1, j, k, memo));
        }
        if ((j - i) % (k - 1) == 0) {
            minCost += prefixSum[j] - (i > 0 ? prefixSum[i - 1] : 0);
        }
        return memo[i][j] = minCost;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(K ^ N) + O(N) ~ O(K ^ N)
     * SC: O(N) + O(N)
     * 
     * Time Limit Exceeded (1010 / 1120 testcases passed)
     */
    public static int mergeStonesRecursion(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            // merging k consecutive piles is not possible
            return -1;
        }
        prefixSum = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSum[i] = i > 0 ? (prefixSum[i - 1] + stones[i]) : stones[i];
        }
        return solveRecursion(0, n - 1, k); // TC: O(K ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(K ^ N)
     * SC: O(N)
     */
    private static int solveRecursion(int i, int j, int k) {
        // Base Case
        if (i >= j) {
            return 0;
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int p = i; p < j; p += (k - 1)) {
            minCost = Math.min(minCost,
                solveRecursion(i, p, k) + solveRecursion(p + 1, j, k));
        }
        if ((j - i) % (k - 1) == 0) {
            minCost += prefixSum[j] - (i > 0 ? prefixSum[i - 1] : 0);
        }
        return minCost;
    }
}
