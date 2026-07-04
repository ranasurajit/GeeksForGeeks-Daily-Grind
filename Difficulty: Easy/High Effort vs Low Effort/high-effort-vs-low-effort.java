class Solution {
    /**
     * Approach I : Using Tabulation (Bottom-Up) Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public int maxTask(int[] h, int[] l) {
        int n = h.length;
        /**
         * we can denote the type of the tasks performed as below:
         * 0 : no task
         * 1 : low-effort task
         * 2 : high effort task
         * 
         * dp[i][t] represents the maximum number of tasks 
         * performed on ith day
         * 
         * i.e. 
         * 
         * dp[i][0] = maximum number of tasks performed on ith day
         * when no task is done
         * dp[i][1] = maximum number of tasks performed on ith day
         * when low-effort task is done
         * dp[i][2] = maximum number of tasks performed on ith day
         * when high-effort task is done
         */
        int[][] dp = new int[n][3]; // SC : O(n x 3) ~ O(n)
        dp[0][0] = 0; // no task performed on 0th day
        dp[0][1] = l[0]; // easy task performed on 0th day
        dp[0][2] = h[0]; // hard task performed on 0th day
        /**
         * dp[1][0] = Max(dp[0][0], dp[0][1], dp[0][2])
         * dp[1][1] = l[1] + Max(dp[0][0], dp[0][1], dp[0][2])
         * 
         * if hard task is selected on 1st day then on 0th day
         * no task should be selected
         * dp[1][2] = h[1] + dp[0][0]
         * 
         * dp[2][0] = Max(dp[1][0], dp[1][1], dp[1][2])
         * dp[2][1] = l[2] + Max(dp[1][0], dp[1][1], dp[1][2])
         * 
         * if hard task is selected on 2nd day then on 1st day
         * no task should be selected
         * dp[2][2] = h[2] + dp[1][0]
         */
        for (int i = 1; i < n; i++) { // TC : O(n)
            dp[i][0] = Math.max(dp[i - 1][0], 
                Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = l[i] + Math.max(dp[i - 1][0], 
                Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][2] = h[i] + dp[i - 1][0];
        }
        return Math.max(dp[n - 1][0], 
                Math.max(dp[n - 1][1], dp[n - 1][2]));
    }
}
