class Solution {
    /**
     * Approach : Using DP (Tabulation - Bottom Up) + Kadane's Algorithm Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public int maxSumSubarray(int[] arr) {
        int n = arr.length;
        /**
         * dp[i][0] = max sub-array sum till index 'i' with 0 removal
         * dp[i][1] = max sub-array sum till index 'i' with 1 removal
         */
        int[][] dp = new int[n][2]; // TC : O(2 x n)
        dp[0][0] = arr[0];
        dp[0][1] = Integer.MIN_VALUE / 2;
        int result = Math.max(dp[0][0], dp[0][1]);
        for (int i = 1; i < n; i++) { // TC : O(n)
            dp[i][0] = Math.max(
                arr[i], // start sub-array from index 'i'
                dp[i - 1][0] + arr[i]   // previous best answer + current index 'i'
            );
            dp[i][1] = Math.max(
                dp[i - 1][0], // skip arr[i] and best answer till (i - 1) with 0 removal
                dp[i - 1][1] + arr[i] // best answer till (i - 1) with 1 removal and include arr[i]
            );
            result = Math.max(result, Math.max(dp[i][0], dp[i][1]));
        }
        return result;
    }
}
