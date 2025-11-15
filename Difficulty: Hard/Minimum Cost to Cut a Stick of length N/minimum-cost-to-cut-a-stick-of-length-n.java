class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(M x M x M) + O(M x log(M)) + O(M) ~ O(M x M x M)
     * SC: O(M x M) + O(M) + O(M)
     * 
     * - O(M x M) - memoization memory
     * - O(M) - arr memory
     * - O(M) - recursion stack
     * 
     * where M = Size(cuts)
     * 
     * Accepted (1120 / 1120 testcases passed)
     */
    public int minCutCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] arr = new int[m + 2]; // SC: O(M)
        arr[0] = 0;
        arr[m + 1] = n;
        for (int i = 0; i < m; i++) { // TC: O(M)
            arr[i + 1] = cuts[i];
        }
        Arrays.sort(arr); // TC: O(M x log(M))
        int[][] memo = new int[m + 2][m + 2]; // SC: O(M x M)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, m + 1, arr, memo); // TC: O(M x M x M), SC: O(M)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(M x M x M)
     * SC: O(M)
     * 
     * where M = Size(cuts)
     */
    private int solveMemoization(int i, int j, int[] arr, int[][] memo) {
        // Base Case
        if (j - i == 1) {
            // it is a single segment
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int minCost = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            // k is the cut position
            minCost = Math.min(minCost,
                (arr[j] - arr[i]) +
                solveMemoization(i, k, arr, memo) + solveMemoization(k, j, arr, memo));
        }
        return memo[i][j] = minCost;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(M x 2 ^ M) + O(M x log(M)) + O(M) ~ O(M x 2 ^ M)
     * SC: O(M) + O(M)
     * 
     * - O(M) - arr memory
     * - O(M) - recursion stack
     * 
     * where M = Size(cuts)
     * 
     * Time Limit Exceeded (1010 / 1120 testcases passed)
     */
    public int minCutCostRecursion(int n, int[] cuts) {
        int m = cuts.length;
        int[] arr = new int[m + 2]; // SC: O(M)
        arr[0] = 0;
        arr[m + 1] = n;
        for (int i = 0; i < m; i++) { // TC: O(M)
            arr[i + 1] = cuts[i];
        }
        Arrays.sort(arr); // TC: O(M x log(M))
        return solveRecursion(0, m + 1, arr); // TC: O(M x 2 ^ M), SC: O(M)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(M x 2 ^ M)
     * SC: O(M)
     * 
     * where M = Size(cuts)
     */
    private int solveRecursion(int i, int j, int[] arr) {
        // Base Case
        if (j - i == 1) {
            // it is a single segment
            return 0;
        }
        int minCost = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            // k is the cut position
            minCost = Math.min(minCost,
                (arr[j] - arr[i]) +
                solveRecursion(i, k, arr) + solveRecursion(k, j, arr));
        }
        return minCost;
    }
}
