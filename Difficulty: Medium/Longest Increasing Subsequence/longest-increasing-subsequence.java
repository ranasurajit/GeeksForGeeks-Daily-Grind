class Solution {
    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N x N) + O(N)
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    static int lis(int arr[]) {
        int n = arr.length;
        int[][] memo = new int[n][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, -1, n, arr, memo);
    }
    
    /**
     * Using Recursion Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private static int solveMemoization(int idx, int prevIdx, int n, int[] nums, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][prevIdx + 1] != -1) {
            return memo[idx][prevIdx + 1];
        }
        // Recursion Calls
        // skip
        int skip = solveMemoization(idx + 1, prevIdx, n, nums, memo);
        // pick
        int pick = 0;
        if (prevIdx == -1 || nums[prevIdx] < nums[idx]) {
            pick = 1 + solveMemoization(idx + 1, idx, n, nums, memo);
        }
        return memo[idx][prevIdx + 1] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * 
     * Time Limit Exceeded (1010 / 1111 testcases passed)
     */
    static int lisRecursion(int arr[]) {
        int n = arr.length;
        return solveRecursion(0, -1, n, arr);
    }
    
    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private static int solveRecursion(int idx, int prevIdx, int n, int[] nums) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        // skip
        int skip = solveRecursion(idx + 1, prevIdx, n, nums);
        // pick
        int pick = 0;
        if (prevIdx == -1 || nums[prevIdx] < nums[idx]) {
            pick = 1 + solveRecursion(idx + 1, idx, n, nums);
        }
        return Math.max(pick, skip);
    }
}
