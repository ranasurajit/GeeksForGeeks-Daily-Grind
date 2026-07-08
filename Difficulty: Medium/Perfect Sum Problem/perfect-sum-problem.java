class Solution {
    /**
     * Approach I : Using Bottom-Up DP Approach
     *
     * TC : O(n) + O(n x k) ~ O(n x k)
     * SC : O(n x k)
     */
    public int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1]; // SC : O(n x k)
        /**
         * here dp[i][j] denotes the count of all subsets considering
         * first 'i' elements ([0...(i - 1)]) with sum target = j
         * 
         * dp[0][0] = 1 if we have to compute count of subsets with sum = 0 
         * with first 0 elements of array 'nums' we have 1 count
         * 
         * dp[i][j]
         *  -> skip, count of subarrays = count with (first i - 1 elements and sum j)
         *  i.e. dp[i - 1][j]
         *  -> pick, count of subarrays = count with (first i elements and sum j) =
         *  i.e. dp[i - 1][j - nums[i - 1]]
         */
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {          // TC : O(n)
            for (int j = 0; j <= target; j++) { // TC : O(k)
                int skip = dp[i - 1][j];
                int pick = 0;
                if (nums[i - 1] <= j) {
                    pick = dp[i - 1][j - nums[i - 1]];
                }
                dp[i][j] = skip + pick;
            }
        }
        return dp[n][target];
    }
}
