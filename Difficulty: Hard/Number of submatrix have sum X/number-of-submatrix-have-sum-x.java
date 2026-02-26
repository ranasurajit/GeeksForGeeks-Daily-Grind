class Solution {
    /**
     * Approach : Using 2D Array Prefix Pre-processing Approach
     * 
     * TC: O(N x M) + O(Min(N, M) x N x M) ~ O(Min(N, M) x N x M)
     * SC: O(N x M)
     */
    public int countSquare(int[][] mat, int x) {
        int n = mat.length;
        int m = mat[0].length;
        /**
         * Intuition is to pre-process the matrix
         * to form cumulative prefix-sum of matrix
         */
        long[][] prefix = new long[n][m]; // SC: O(N x M)
        for (int i = 0; i < n; i++) {     // TC: O(N)
            for (int j = 0; j < m; j++) { // TC: O(M)
                prefix[i][j] = mat[i][j] +
                    (j > 0 ? prefix[i][j - 1] : 0L) +
                    (i > 0 ? prefix[i - 1][j] : 0L) -
                    ((i > 0 && j > 0) ? prefix[i - 1][j - 1] : 0);
            }
        }
        /**
         * To compute the count of 2D square sub-arrays
         * the size of matrix can vary from (1 x 1) to
         * Min(n, m) x Min(n, m)
         */
        int count = 0;
        for (int k = 1; k <= Math.min(n, m); k++) { // TC: O(Min(N, M))
            for (int i = 0; i + k <= n; i++) {      // TC: O(N)
                for (int j = 0; j + k <= m; j++) {  // TC: O(M)
                    int r1 = i;
                    int c1 = j;
                    int r2 = i + k - 1;
                    int c2 = j + k - 1;
                    long sum = prefix[r2][c2] - 
                        (r1 > 0 ? prefix[r1 - 1][c2] : 0) - 
                        (c1 > 0 ? prefix[r2][c1 - 1] : 0) +
                        ((r1 > 0 && c1 > 0) ? prefix[r1 - 1][c1 - 1] : 0);
                    if (sum == x) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
