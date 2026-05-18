class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     * 
     * TC : O(n)
     * SC : O(n)
     * 
     * - O(n) - dp array memory
     */
    public int maxSum(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];     // SC : O(n)
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) { // TC : O(n)
            int currentSum = dp[i / 2] + dp[i / 3] + dp[i / 4];
            dp[i] = Math.max(currentSum, i);
        }
        return dp[n];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n)
     * SC : O(log(n)) + O(n)
     * 
     * - O(log(n)) - recursion stack
     * - O(n) - memoization memory
     */
    public int maxSumMemoization(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solveMemoization(n, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n)
     * SC : O(log(n))
     */
    private int solveMemoization(int n, int[] memo) {
        // Base Case
        if (n <= 2) {
            return n;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        int currentSum = 
            solveMemoization(n / 2, memo) + 
                solveMemoization(n / 3, memo) + 
                    solveMemoization(n / 4, memo);
        return memo[n] = Math.max(n, currentSum);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(3 ^ (log(n)))
     * SC : O(log(n))
     * 
     * - O(log(n)) - recursion stack
     */
    public int maxSumRecursion(int n) {
        return solveRecursion(n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(3 ^ (log(n)))
     * SC : O(log(n))
     */
    private int solveRecursion(int n) {
        // Base Case
        if (n <= 2) {
            return n;
        }
        // Recursion Calls
        int currentSum = 
            solveRecursion(n / 2) + 
                solveRecursion(n / 3) + 
                    solveRecursion(n / 4);
        return Math.max(n, currentSum);
    }
}
