class Solution {
    /**
     * Approach : Using 2D-Matrix Simulation Approach
     * 
     * TC : O(n³)
     * SC : O(n²) + O(n²) ~ O(n²)
     */
    public int largestSubsquare(char mat[][]) {
        int n = mat.length;
        /**
         * we will pre-process the count of 'X'
         * cells from right to left in 'right' array
         * and from down to up in 'down' array
         */
        int[][] right = new int[n][n]; // SC : O(n²)
        int[][] down = new int[n][n];  // SC : O(n²
        for (int i = 0; i < n; i++) {          // TC : O(n)
            int countX = 0;
            for (int j = n - 1; j >= 0; j--) { // TC : O(n)
                if (mat[i][j] == 'X') {
                    countX++;
                } else {
                    // reset the count to 0
                    countX = 0;
                }
                right[i][j] = countX;
            }
        }
        for (int j = 0; j < n; j++) {          // TC : O(n)
            int countX = 0;
            for (int i = n - 1; i >= 0; i--) { // TC : O(n)
                if (mat[i][j] == 'X') {
                    countX++;
                } else {
                    // reset the count to 0
                    countX = 0;
                }
                down[i][j] = countX;
            }
        }
        int maxSide = 0;
        for (int i = 0; i < n; i++) {     // TC : O(n)
            for (int j = 0; j < n; j++) { // TC : O(n)
                int k = Math.min(right[i][j], down[i][j]);
                // so, sub-squares of side length [1...k] is possible
                for (int side = k; side >= 1; side--) { // TC : O(n)
                    // check if all 4 sides have valid X count 'side'
                    // if yes we can break the loop
                    int rightCol = j + side - 1;
                    int bottomRow = i + side - 1;
                    if (right[i][j] >= side && // top checked
                        down[i][j] >= side &&  // left checked
                        right[bottomRow][j] >= side && // down checked
                        down[i][rightCol] >= side) {  // right checked
                        // we have a valid square and not need to go further
                        maxSide = Math.max(maxSide, side);
                        break;
                    }
                }
            }
        }
        return maxSide;
    }
};
