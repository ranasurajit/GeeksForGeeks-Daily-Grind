class Solution {
    private int n;
    
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(N)
     * 
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     */
    public int maxProfit(int arr[]) {
        this.n = arr.length;
        int[][] memo = new int[n][2]; // SC: O(2 x N) ~ O(N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, 1, arr, memo); // TC: O(N), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int canBuy, int[] arr, int[][] memo) {
        // Base Case
        if (idx >= n) {
            // cannot buy further
            return 0;
        }
        // Memoization Check
        if (memo[idx][canBuy] != -1) {
            return memo[idx][canBuy];
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // pick or skip to buy
            pick = -1 * arr[idx] + solveMemoization(idx + 1, 0, arr, memo);
            skip = solveMemoization(idx + 1, 1, arr, memo);
        } else {
            // pick or skip to sell
            pick = arr[idx] + solveMemoization(idx + 2, 1, arr, memo); // cooldown of 1 
            skip = solveMemoization(idx + 1, 0, arr, memo);
        }
        return memo[idx][canBuy] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * 
     * - O(N) - recursion stack
     */
    public int maxProfitRecursion(int arr[]) {
        this.n = arr.length;
        return solveRecursion(0, 1, arr); // TC: O(2 ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int canBuy, int[] arr) {
        // Base Case
        if (idx >= n) {
            // cannot buy further
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (canBuy == 1) {
            // pick or skip to buy
            pick = -1 * arr[idx] + solveRecursion(idx + 1, 0, arr);
            skip = solveRecursion(idx + 1, 1, arr);
        } else {
            // pick or skip to sell
            pick = arr[idx] + solveRecursion(idx + 2, 1, arr); // cooldown of 1 
            skip = solveRecursion(idx + 1, 0, arr);
        }
        return Math.max(pick, skip);
    }
}
