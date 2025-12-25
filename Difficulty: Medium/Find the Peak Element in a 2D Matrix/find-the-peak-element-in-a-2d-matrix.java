class Solution {
    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(M x log(N))
     * SC: O(1)
     */
    public ArrayList<Integer> findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        /**
         * Intuition:
         * 
         * we will transform 2D matrix to 1D equivaleny by below trick
         * 1. we will pick any column index from binary search i.e. mid
         * 2. then we will compute the row index i, having maximum value
         * 3. we will compare mat[i][j] with left and right cells
         */
        ArrayList<Integer> result = new ArrayList<Integer>();
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            int row = getMaxRowIndex(mat, m, mid); // TC: O(M), SC: O(1)
            int leftValue = mid > 0 ? mat[row][mid - 1] : Integer.MIN_VALUE;
            int rightValue = mid < n - 1 ? mat[row][mid + 1] : Integer.MIN_VALUE;
            if (mat[row][mid] >= leftValue && mat[row][mid] >= rightValue) {
                result.add(row);
                result.add(mid);
                return result;
            } else if (leftValue > mat[row][mid]) {
                high = mid - 1;
            } else if (rightValue > mat[row][mid]) {
                low = mid + 1;
            }
        }
        return result;
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(M)
     * SC: O(1)
     */
    private int getMaxRowIndex(int[][] mat, int m, int col) {
        int maxValue = Integer.MIN_VALUE;
        int maxRowIndex = -1;
        for (int i = 0; i < m; i++) { // TC: O(M)
            if (mat[i][col] > maxValue) {
                maxValue = mat[i][col];
                maxRowIndex = i;
            }
        }
        return maxRowIndex;
    }
}
