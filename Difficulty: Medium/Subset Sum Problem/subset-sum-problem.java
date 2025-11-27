class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x T)
     * SC: O(N x T) + O(N)
     * - O(N x T) - memoization memory
     * - O(N) - recursion stack
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        int[][] memo = new int[n][sum + 1]; // SC: O(N x T)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, arr, sum, memo); // TC: O(N x T), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x T)
     * SC: O(N)
     */
    private static Boolean solveMemoization(int idx, int n, int[] arr,
        int sum, int[][] memo) {
        // Base Case
        if (idx == n) {
            return sum == 0;
        }
        // Memoization Check
        if (memo[idx][sum] != -1) {
            return memo[idx][sum] == 1;
        }
        // Recursion Calls
        // we may skip or pick the element at index idx
        // skip
        Boolean skip = solveMemoization(idx + 1, n, arr, sum, memo);
        Boolean pick = false;
        if (arr[idx] <= sum) {
            // at this condition only we can choose to pick
            pick = solveMemoization(idx + 1, n, arr, sum - arr[idx], memo);
        }
        Boolean result = pick || skip;
        memo[idx][sum] = result ? 1 : 0;
        return result;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (10 / 1115 testcases passed)
     */
    static Boolean isSubsetSumRecursion(int arr[], int sum) {
        int n = arr.length;
        return solveRecursion(0, n, arr, sum); // TC: O(2 ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private static Boolean solveRecursion(int idx, int n, int[] arr, int sum) {
        // Base Case
        if (idx == n) {
            return sum == 0;
        }
        // Recursion Calls
        // we may skip or pick the element at index idx
        // skip
        Boolean skip = solveRecursion(idx + 1, n, arr, sum);
        Boolean pick = false;
        if (arr[idx] <= sum) {
            // at this condition only we can choose to pick
            pick = solveRecursion(idx + 1, n, arr, sum - arr[idx]);
        }
        return pick || skip;
    }
}
