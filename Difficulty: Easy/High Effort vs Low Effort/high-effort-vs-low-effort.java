class Solution {
    /**
     * Approach : Using Tabulation (Bottom-Up) Approach
     * 
     * TC : O(n)
     * SC : O(3 x n) ~ O(n)
     */
    public int maxTask(int[] h, int[] l) {
        int n = h.length;
        /**
         * we can consider:
         * 0 - no task done
         * 1 - low-effort task done
         * 2 - high-effort task done
         */
        int[][] dp = new int[n + 1][3]; // SC : O(3 x n) ~ O(n)
        /**
         * dp[i][0] - maximum number of tasks completed 
         * till ith day when no task was performed 
         * previous day
         * 
         * dp[i][1] - maximum number of tasks completed 
         * till ith day when low-effort task was 
         * performed previous day
         * 
         * dp[i][2] - maximum number of tasks completed 
         * till ith day when high-effort task was 
         * performed previous day
         */
        for (int i = 1; i <= n; i++) { // TC : O(n)
            int maxTasksPrev = Math.max(dp[i - 1][0], 
                Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][0] = maxTasksPrev;
            dp[i][1] = maxTasksPrev + l[i - 1];
            dp[i][2] = dp[i - 1][0] + h[i - 1];
        }
        return Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
    }
}
