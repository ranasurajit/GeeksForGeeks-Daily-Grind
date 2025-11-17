class Solution {
    private int n;

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N x N) + O(N)
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (1112 / 1112 testcases passed)
     */
    public int maxSumIS(int arr[]) {
        this.n = arr.length;
        int[][] memo = new int[n][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, -1, arr, memo);
    }
    
    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int prevIdx, int[] arr, int[][] memo) {
        // Base Case
        if (idx >= n) {
            // nums is exhaused at index 'idx' here
            return 0;
        }
        // Memoization Check
        if (memo[idx][prevIdx + 1] != -1) {
            return memo[idx][prevIdx + 1];
        }
        // Recursion Calls
        int maxSum = solveMemoization(idx + 1, prevIdx, arr, memo); // skip
        if (prevIdx == -1 || arr[prevIdx] < arr[idx]) {
            maxSum = Math.max(maxSum, arr[idx] + solveMemoization(idx + 1, idx, arr, memo));
        }
        return memo[idx][prevIdx + 1] = maxSum;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (1010 / 1112 testcases passed)
     */
    public int maxSumISRecursion(int arr[]) {
        this.n = arr.length;
        return solveRecursion(0, -1, arr);
    }
    
    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int prevIdx, int[] arr) {
        // Base Case
        if (idx >= n) {
            // nums is exhaused at index 'idx' here
            return 0;
        }
        // Recursion Calls
        int maxSum = solveRecursion(idx + 1, prevIdx, arr); // skip
        if (prevIdx == -1 || arr[prevIdx] < arr[idx]) {
            maxSum = Math.max(maxSum, arr[idx] + solveRecursion(idx + 1, idx, arr));
        }
        return maxSum;
    }
}
