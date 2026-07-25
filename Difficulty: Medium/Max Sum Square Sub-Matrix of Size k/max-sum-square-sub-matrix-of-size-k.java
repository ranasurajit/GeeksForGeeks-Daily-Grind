class Solution {
    /**
     * Approach : Using 2-D Prefix Sum Approach
     * 
     * TC : O(n²) + O((n - k) x (n - k)) ~ O(n²)
     * SC : O(n²)
     */
    public int maximumSum(int[][] mat, int k) {
        int n = mat.length;
        /**
         * we need to pre-compute the prefix sum of the
         * grid by the formula below:
         * 
         * prefix[i][j] = mat[i][j] + prefix[i - 1][j] + 
         * prefix[i][j - 1] - prefix[i - 1][j - 1]
         */
        int[][] prefix = new int[n][n];   // SC : O(n²)
        for (int i = 0; i < n; i++) {     // TC : O(n)
            for (int j = 0; j < n; j++) { // TC : O(n)
                prefix[i][j] = mat[i][j];
                if (i > 0) {
                    prefix[i][j] += prefix[i - 1][j];
                }
                if (j > 0) {
                    prefix[i][j] += prefix[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    prefix[i][j] -= prefix[i - 1][j - 1];
                }
            }
        }
        /**
         * now we need to find and compare the sums of all cells
         * at or after i = (k - 1) to n and j = (k - 1) to n
         */
        int maxSum = Integer.MIN_VALUE;
        for (int i = k - 1; i < n; i++) {     // TC : O(n - k)
            for (int j = k - 1; j < n; j++) { // TC : O(n - k)
                int currentSum = prefix[i][j]
                    - ((i >= k) ? prefix[i - k][j] : 0)
                    - ((j >= k) ? prefix[i][j - k] : 0)
                    + ((i >= k && j >= k) ? prefix[i - k][j - k] : 0);
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}
