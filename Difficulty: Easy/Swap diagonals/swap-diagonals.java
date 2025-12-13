class Solution {
    /**
     * Approach : Using 2-D Array Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public void swapDiagonal(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            // swapping diagonal cell values
            int temp = mat[i][n - 1 - i];
            mat[i][n - 1 - i] = mat[i][i];
            mat[i][i] = temp;
        }
    }
}
