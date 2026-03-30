class Solution {
    private int k;
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n) + O(n)
     * - O(n) - memoization memory
     * - O(n) - recursion stack
     */
    public int maxProfit(int arr[], int k) {
        this.k = k;
        int n = arr.length;
        int[][] memo = new int[n][2]; // SC: O(2 x n) ~ O(n)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, arr, 1, memo); // TC: O(n), SC: O(n)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(2 x n) ~ O(n)
     * SC: O(n)
     */
    private int solveMemoization(int idx, int n, int[] arr,
        int buy, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][buy] != -1) {
            return memo[idx][buy];
        }
        // Recursion Calls
        // pick or skip buying
        int pick = 0;
        int skip = 0;
        if (buy == 1) {
            // buy here
            pick = -1 * arr[idx] + solveMemoization(idx + 1, n, arr, 0, memo);
            skip = solveMemoization(idx + 1, n, arr, 1, memo);
        } else {
            // sell here
            pick = arr[idx] - k + solveMemoization(idx + 1, n, arr, 1, memo);
            skip = solveMemoization(idx + 1, n, arr, 0, memo);
        }
        return memo[idx][buy] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n)
     * - O(n) - recursion stack
     */
    public int maxProfitRecursion(int arr[], int k) {
        this.k = k;
        int n = arr.length;
        return solveRecursion(0, n, arr, 1); // TC: O(2ⁿ), SC: O(n)
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n)
     */
    private int solveRecursion(int idx, int n, int[] arr, int buy) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        // pick or skip buying
        int pick = 0;
        int skip = 0;
        if (buy == 1) {
            // buy here
            pick = -1 * arr[idx] + solveRecursion(idx + 1, n, arr, 0);
            skip = solveRecursion(idx + 1, n, arr, 1);
        } else {
            // sell here
            pick = arr[idx] - k + solveRecursion(idx + 1, n, arr, 1);
            skip = solveRecursion(idx + 1, n, arr, 0);
        }
        return Math.max(pick, skip);
    }
}
