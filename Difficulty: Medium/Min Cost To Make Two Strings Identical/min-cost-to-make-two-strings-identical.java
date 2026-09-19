class Solution {
    private int n1;
    private int n2;
    private String s1;
    private String s2;
    private int costS1;
    private int costS2;

    /**
     * Approach I : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n1 x n2)
     * SC : O(n1 x n2) + O(n1 x n2)
     * - O(n1 x n2) - memoization memory
     * - O(n1 x n2) - recursion stack
     */
    public int findMinCost(String s1, String s2, int costS1, int costS2) {
        n1 = s1.length();
        n2 = s2.length();
        this.s1 = s1;
        this.s2 = s2;
        this.costS1 = costS1;
        this.costS2 = costS2;
        int[][] dp = new int[n1 + 1][n2 + 1]; // SC : O(n1 x n2)
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return solve(0, 0, dp);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n1 x n2)
     * SC : O(n1 x n2)
     */
    private int solve(int i, int j, int[][] dp) {
        // Base Case
        if (i == n1 && j == n2) {
            // both Strings 's1' and 's2' are exhaused
            return 0;
        }
        if (i == n1) {
            /**
             * String 's1' is exhaused so, we need 
             * to delete remaining characters of 
             * String 's2'
             */
            return costS2 * (n2 - j);
        }
        if (j == n2) {
            /**
             * String 's2' is exhaused so, we need 
             * to delete remaining characters of 
             * String 's1'
             */
            return costS1 * (n1 - i);
        }
        // Memoization Check
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        // Recursion Calls
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = solve(i + 1, j + 1, dp);
        }
        return dp[i][j] = Math.min(
            costS1 + solve(i + 1, j, dp),
            costS2 + solve(i, j + 1, dp)
        );
    }
}
