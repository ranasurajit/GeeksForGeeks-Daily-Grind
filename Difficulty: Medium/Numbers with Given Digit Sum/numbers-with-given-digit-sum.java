class Solution {
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n x sum)
     * SC : O(n x sum) + O(n)
     * - O(n x sum) - memoization memory
     * - O(n) - recursion stack
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int countWays(int n, int sum) {
        int[][] memo = new int[n][sum + 1]; // SC : O(n x sum)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        int ways = solveMemoization(0, n, sum, memo);
        return ways == 0 ? -1 : ways;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n x sum)
     * SC : O(n)
     */
    private int solveMemoization(int position, int n, int sum, int[][] memo) {
        // Base Case
        if (position == n) {
            return sum == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[position][sum] != -1) {
            return memo[position][sum];
        }
        // Recursion Calls
        int ways = 0;
        int start = position == 0 ? 1 : 0;
        for (int digit = start; digit <= 9; digit++) {
            if (sum >= digit) {
                ways += solveMemoization(position + 1, n, sum - digit, memo);
            }
        }
        return memo[position][sum] = ways;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(10ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (1111 / 1115 testcases passed)
     */
    public int countWaysRecursion(int n, int sum) {
        int ways = solve(0, n, sum);
        return ways == 0 ? -1 : ways;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(10ⁿ)
     * SC : O(n)
     */
    private int solve(int position, int n, int sum) {
        // Base Case
        if (position == n) {
            return sum == 0 ? 1 : 0;
        }
        // Recursion Calls
        int ways = 0;
        int start = position == 0 ? 1 : 0;
        for (int digit = start; digit <= 9; digit++) {
            if (sum >= digit) {
                ways += solve(position + 1, n, sum - digit);
            }
        }
        return ways;
    }
};
