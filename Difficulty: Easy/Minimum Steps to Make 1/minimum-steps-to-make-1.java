class Solution {
    /**
     * Approach : Using Tabulation DP (Bottom Up) Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public int getMinSteps(int n) {
        int[] dp = new int[n + 1]; // SC : O(n)
        dp[1] = 0; // no operations needed
        /**
         * dp[2] = Min(1 + dp[2 / 2], 1 + dp[2 - 1]) as 2 is divisible by 2
         * dp[3] = Min(1 + dp[3 / 3], 1 + dp[3 - 1]) as 3 is divisible by 3
         * dp[4] = Min(1 + dp[4 / 2], 1 + dp[4 - 1]) as 4 is divisible by 2
         * dp[5] = Min(1 + dp[5 - 1]) as 5 is not divisible by 2 or 3
         * 
         * dp[i] denotes the minimum steps to make i to 1
         */
        for (int i = 2; i <= n; i++) { // TC : O(n)
            dp[i] = n;
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], 1 + dp[i / 3]);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], 1 + dp[i / 2]);
            }
            dp[i] = Math.min(dp[i], 1 + dp[i - 1]);
        }
        return dp[n];
    }
}
