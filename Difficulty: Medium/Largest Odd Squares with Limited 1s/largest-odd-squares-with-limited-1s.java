class Solution {
    private int n;
    private int m;

    /**
     * Approach : Using Prefix Sum on 2D Matrix + Binary Search Approach
     * 
     * TC : O(n x m) + O(q x log(Min(n, m)))
     * SC : O(n x m)
     */
    ArrayList<Integer> largestSquare(int[][] mat, int[][] queries, int k) {
        this.n = mat.length;
        this.m = mat[0].length;
        /**
         * we need to compute the prefix sum of 2D matrix
         * using the formula : 
         * 
         * prefix[i][j] = mat[i][j] + 
         *     prefix[i - 1][j] + prefix[i][j - 1] - 
         *     prefix[i - 1][j - 1]
         */
        int[][] prefix = new int[n][m];   // SC : O(n x m)
        for (int i = 0; i < n; i++) {     // TC : O(n)
            for (int j = 0; j < m; j++) { // TC : O(m)
                prefix[i][j] = mat[i][j] + 
                    (i > 0 ? prefix[i - 1][j] : 0) +
                    (j > 0 ? prefix[i][j - 1] : 0) - 
                    ((i > 0 && j > 0) ? prefix[i - 1][j - 1] : 0);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] query : queries) { // TC : O(q)
            int i = query[0];
            int j = query[1];
            /**
             * we need to find the maximum radius such that
             * 
             * the boundaries form the square and has atmost
             * k 1's
             * 
             * so, if 'r' is the radius of the square with (i, j)
             * as its center so, we have:
             * 
             * top : (i - r, j), bottom: (i + r, j)
             * left: (i, j - r), right: (i, j + r)
             * 
             * so, to compute the k 1's we need the three coordinates
             * 
             * right-bottom cell's coordinate is (i + r, j + r)
             * cell above top-right cell is (i - r - 1, j + r)
             * cell before bottom-left cell is (i + r, j - r - 1)
             * cell diagonally above-left of the top-left is (i - r - 1, j - r - 1)
             */
            int r = getMaximumRadius(i, j, prefix, k); // TC : O(log(Min(n, m)))
            result.add(2 * r + 1);
        }
        return result;
    }
    
    /**
     * Using Binary Search Approach
     * 
     * TC : O(log(Min(n, m)))
     * SC : O(1)
     */
    private int getMaximumRadius(int i, int j, int[][] prefix, int k) {
        int low = 0;
        int high = Math.min(Math.min(i, n - i - 1), Math.min(j, m - j - 1));
        while (low <= high) { // TC : O(log(Min(n, m)))
            int mid = low + (high - low) / 2;
            int count1s = getCount1s(i, j, mid, prefix); // TC : O(1), SC : O(1)
            if (count1s > k) {
                // we need to minimize mid
                high = mid - 1;
            } else {
                // we need to maximize mid
                low = mid + 1;
            }
        }
        return high;
    }
    
    /**
     * Using Math Approach
     * 
     * TC : O(1)
     * SC : O(1)
     */
    private int getCount1s(int i, int j, int r, int[][] prefix) {
        int sum = 0;
        if (i + r < n && j + r < m) {
            sum += prefix[i + r][j + r];
        }
        if (i - r - 1 >= 0 && i - r - 1 < n && j + r >= 0 && j + r < m) {
            sum -= prefix[i - r - 1][j + r];
        }
        if (i + r >= 0 && i + r < n && j - r - 1 >= 0 && j - r - 1 < m) {
            sum -= prefix[i + r][j - r - 1];
        }
        if (i - r - 1 >= 0 && i - r - 1 < n && j - r - 1 >= 0 && j - r - 1 < m) {
            sum += prefix[i - r - 1][j - r - 1];
        }
        return sum;
    }
}
