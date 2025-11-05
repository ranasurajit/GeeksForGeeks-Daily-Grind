class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(Sqrt(N) x N)
     * SC: O(N) + O(N) ~ O(N)
     * 
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     * 
     * Accepted (1115 / 1115 Testcases Passed)
     */
    public int minSquares(int n) {
        int[] memo = new int[n + 1]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization((long) n, memo); // TC: O(Sqrt(N) x N), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(Sqrt(N) x N)
     * SC: O(N)
     */
    private int solveMemoization(long sum, int[] memo) {
        // Base Case
        if (sum == 0L) {
            return 0;
        }
        int idx = (int) sum;
        // Memoization Check
        if (memo[idx] != -1L) {
            return (int) memo[idx];
        }
        // Recursion Calls
        int minCount = Integer.MAX_VALUE;
        for (int x = 1; x * x <= sum; x++) { // TC: O(Sqrt(N))
            long square = (long) x * (long) x;
            minCount = Math.min(minCount, 1 + solveMemoization(sum - square, memo));
        }
        return memo[idx] = minCount;
    }
    
    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(Sqrt(N) ^ N)
     * SC: O(N)
     * 
     * Time Limit Exceeded (10 / 1115 Testcases Passed)
     */
    public int minSquaresRecursion(int n) {
        return solveRecursion((long) n); // TC: O(Sqrt(N) ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(Sqrt(N) ^ N)
     * SC: O(N)
     */
    private int solveRecursion(long sum) {
        // Base Case
        if (sum == 0L) {
            return 0;
        }
        // Recursion Calls
        int minCount = Integer.MAX_VALUE;
        for (int x = 1; x * x <= sum; x++) { // TC: O(Sqrt(N))
            long square = (long) x * (long) x;
            minCount = Math.min(minCount, 1 + solveRecursion(sum - square));
        }
        return minCount;
    }
}
