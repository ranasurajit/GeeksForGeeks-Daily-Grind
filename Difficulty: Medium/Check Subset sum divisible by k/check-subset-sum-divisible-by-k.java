class Solution {
    private int n;
    private int k;
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n x k)
     * SC : O(n x k) + O(n)
     *  - O(n x k) - memoization memory
     *  - O(n) - recursion stack
     * 
     * Accepted (1116 / 1116 testcases passed)
     */
    public boolean divisibleByK(int[] arr, int k) {
        this.n = arr.length;
        this.k = k;
        Boolean[][] memo = new Boolean[n][k]; // SC : O(n x k)
        return solveMemoization(0, 0, 0, arr, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n x k)
     * SC : O(n)
     */
    private boolean solveMemoization(int idx, int count,
        int rem, int[] arr, Boolean[][] memo) {
        // Base Case
        if (idx == n) {
            return count != 0 && rem == 0;
        }
        // Memoization Check
        if (memo[idx][rem] != null) {
            return memo[idx][rem];
        }
        // Recursion Calls
        // pick or skip
        boolean skip =
            solveMemoization(idx + 1, count, rem, arr, memo);
        boolean pick =
            solveMemoization(idx + 1, count + 1, (rem + arr[idx]) % k, arr, memo);
        return memo[idx][rem] = skip || pick;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     *  - O(n) - recursion stack
     * 
     * Time Limit Exceeded (11 / 1116 testcases passed)
     */
    public boolean divisibleByKRecursion(int[] arr, int k) {
        this.n = arr.length;
        this.k = k;
        return solveRecursion(0, 0, 0, arr);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private boolean solveRecursion(int idx, int count, int rem, int[] arr) {
        // Base Case
        if (idx == n) {
            return count != 0 && rem == 0;
        }
        // Recursion Calls
        // pick or skip
        boolean skip = solveRecursion(idx + 1, count, rem, arr);
        boolean pick = solveRecursion(idx + 1, count + 1, (rem + arr[idx]) % k, arr);
        return skip || pick;
    }
}
