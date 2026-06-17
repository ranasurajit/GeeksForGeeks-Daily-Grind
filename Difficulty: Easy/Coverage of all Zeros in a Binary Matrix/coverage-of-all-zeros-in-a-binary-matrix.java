class Solution {
    /**
     * Approach : Using 2D-Matrix Simulation Approach
     * 
     * TC : O(n x m x (m + n))
     * SC : O(1)
     */
    public int findCoverage(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int coverage = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int j = 0; j < m; j++) { // TC : O(m)
                if (mat[i][j] == 0) {
                    // check in all 4 directions
                    // check top
                    for (int k = i - 1; k >= 0; k--) { // TC : O(p)
                        if (mat[k][j] == 1) {
                            coverage++;
                            break;
                        }
                    }
                    // check down
                    for (int k = i + 1; k < n; k++) { // TC : O(n - p)
                        if (mat[k][j] == 1) {
                            coverage++;
                            break;
                        }
                    }
                    // check left
                    for (int k = 0; k < j; k++) { // TC : O(q)
                        if (mat[i][k] == 1) {
                            coverage++;
                            break;
                        }
                    }
                    // check right
                    for (int k = j + 1; k < m; k++) { // TC : O(m - q)
                        if (mat[i][k] == 1) {
                            coverage++;
                            break;
                        }
                    }
                }
            }
        }
        return coverage;
    }
}
