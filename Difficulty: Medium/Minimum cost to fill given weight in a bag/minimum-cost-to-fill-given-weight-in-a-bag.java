class Solution {
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n x w)
     * SC : O(n x w) + O(n)
     * - O(n x w) - memoization memory
     * - O(n) - recursion stack
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int minimumCost(int[] cost, int w) {
        int n = cost.length;
        int[][] memo = new int[n][w + 1]; // SC : O(n x w)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        int minCost =
            solveMemoization(0, n, cost, w, memo); // TC : O(n x w), SC : O(n)
        return minCost == Integer.MAX_VALUE / 2 ? -1 : minCost;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n x w)
     * SC : O(n)
     */
    private int solveMemoization(int idx, int n, int[] cost,
        int w, int[][] memo) {
        // Base Case
        if (idx == n) {
            return w == 0 ? 0 : Integer.MAX_VALUE / 2;
        }
        // Memoization Check
        if (memo[idx][w] != -1) {
            return memo[idx][w];
        }
        // Recursion Calls
        int skip = solveMemoization(idx + 1, n, cost, w, memo);
        int pick = Integer.MAX_VALUE / 2;
        int currentWeight = idx + 1;
        if (cost[idx] != -1 && currentWeight <= w) {
            // unlimited supply of available quantity
            pick = cost[idx] 
                + solveMemoization(idx, n, cost, w - currentWeight, memo);
        }
        return memo[idx][w] = Math.min(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (10 / 1111 testcases passed)
     */
    public int minimumCostRecursion(int[] cost, int w) {
        int n = cost.length;
        int minCost = solveRecursion(0, n, cost, w); // TC : O(2ⁿ), SC : O(n)
        return minCost == Integer.MAX_VALUE / 2 ? -1 : minCost;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int idx, int n, int[] cost, int w) {
        // Base Case
        if (idx == n) {
            return w == 0 ? 0 : Integer.MAX_VALUE / 2;
        }
        // Recursion Calls
        int skip = solveRecursion(idx + 1, n, cost, w);
        int pick = Integer.MAX_VALUE / 2;
        int currentWeight = idx + 1;
        if (cost[idx] != -1 && currentWeight <= w) {
            // unlimited supply of available quantity
            pick = cost[idx] 
                + solveRecursion(idx, n, cost, w - currentWeight);
        }
        return Math.min(pick, skip);
    }
}
