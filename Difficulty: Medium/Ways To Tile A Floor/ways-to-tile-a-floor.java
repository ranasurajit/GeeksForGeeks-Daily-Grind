class Solution {
    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * Accepted (45 / 45 Testcases Passed)
     */
    public int numberOfWays(int n) {
        // n == 1, number of ways = 1, n = 2, number of ways = 2
        // f(n) = f(n - 1) + f(n - 2)
        if (n <= 2) {
            return n;
        }
        int prev2 = 1;
        int prev = 2;
        for (int i = 3; i <= n; i++) { // TC: O(N)
            int current = prev + prev2;
            prev2 = prev;
            prev = current;
        }
        return prev;
    }

    /**
     * Approach III : Using Tabulation Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * - O(N) - dp memory
     * 
     * Accepted (45 / 45 Testcases Passed)
     */
    public int numberOfWaysTabulation(int n) {
        // n == 1, number of ways = 1, n = 2, number of ways = 2
        // f(n) = f(n - 1) + f(n - 2)
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1]; // SC: O(N)
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) { // TC: O(N)
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - recursion stack space
     * - O(N) - memoization memory
     * 
     * Accepted (45 / 45 Testcases Passed)
     */
    public int numberOfWaysMemoization(int n) {
        // n == 1, number of ways = 1, n = 2, number of ways = 2
        // f(n) = f(n - 1) + f(n - 2)
        int[] memo = new int[n + 1]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(n, memo); // TC: O(N), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int solveMemoization(int n, int[] memo) {
        // Base Case
        if (n == 1 || n == 2) {
            return n;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        return memo[n] = solveMemoization(n - 1, memo) + solveMemoization(n - 2, memo);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack space
     * 
     * Time Limit Exceeded (0 / 45 Testcases Passed)
     */
    public int numberOfWaysRecursion(int n) {
        // n == 1, number of ways = 1, n = 2, number of ways = 2
        // f(n) = f(n - 1) + f(n - 2)
        return solveRecursion(n); // TC: O(2 ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int n) {
        // Base Case
        if (n == 1 || n == 2) {
            return n;
        }
        // Recursion Calls
        return solveRecursion(n - 1) + solveRecursion(n - 2);
    }
};
