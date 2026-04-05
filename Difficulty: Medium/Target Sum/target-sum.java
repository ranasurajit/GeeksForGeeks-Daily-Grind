class Solution {
    /**
     * Approach II : Using Recursion Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1011 testcases passed)
     */
    public int totalWays(int[] arr, int target) {
        int n = arr.length;
        int total = 0;
        for (int i = 0; i < n; i++) { // TC: O(n)
            total += arr[i];
        }
        /**
         * sp - sn = total, sp + sn = target. so, 
         * 2 * sp = total + target, sp = (total + target) / 2
         * where sp = sum(positives) and sn = sum(negatives)
         * 
         * so problem is reduced to finding number of ways to 
         * make subset sum = (total + target) / 2 so if
         * (total + target) is odd then we cannot form sum = 
         * target at all
         */
        if (((total + target) & 1) != 0) {
            return 0;
        }
        int targetSum = (total + target) / 2;
        return solveRecursion(0, n, arr, 0, targetSum);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n)
     */
    private int solveRecursion(int idx, int n, int[] arr,
        int sum, int targetSum) {
        // Base Case
        if (idx == n) {
            return sum == targetSum ? 1 : 0;
        }
        // Recursion Calls
        // we have option to pick or skip arr[idx]
        int skip = solveRecursion(idx + 1, n, arr, sum, targetSum);
        int pick = solveRecursion(idx + 1, n, arr, sum + arr[idx], targetSum);
        return pick + skip;
    }

    /**
     * Approach I : Using Brute-Force (Simple Recursion) Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1011 testcases passed)
     */
    public int totalWaysRecursion(int[] arr, int target) {
        int n = arr.length;
        return solve(0, n, arr, 0, target);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2ⁿ)
     * SC: O(n)
     */
    private int solve(int idx, int n, int[] arr,
        int sum, int target) {
        // Base Case
        if (idx == n) {
            return sum == target ? 1 : 0;
        }
        // Recursion Calls
        /**
         * we have two options at index 'idx', arr[idx]
         * has to be taken (no skip allowed) either as
         * a positive or negative contribution to sum
         */
        int option1 = solve(idx + 1, n, arr, sum + arr[idx], target);
        int option2 = solve(idx + 1, n, arr, sum - arr[idx], target);
        return option1 + option2;
    }
}
