class Solution {
    /**
     * Approach II : Using 2D Arrays Simulation Approach
     * 
     * TC: O(m x n)
     * SC: O(1)
     */
    public boolean isToeplitz(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 1; i < n; i++) { // TC: O(n)
            for (int j = 1; j < m; j++) { // TC: O(n)
                if (mat[i][j] != mat[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Approach I : Using 2D Arrays + Two Pointers Approach
     * 
     * TC: O(m x (m + n)) + O(n x (m + n)) ~ O(m² + n² + (m x n))
     * SC: O(1)
     */
    public boolean isToeplitzTwoPointers(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int j = 0;
        // process for all columns for all diagonals starting from row 0
        while (j < m - 1) { // TC: O(m)
            int p = 0;
            int q = j;
            while (p < n - 1 && q < m - 1) { // TC: O(m + n)
                if (mat[p][q] != mat[p + 1][q + 1]) {
                    return false;
                }
                p++;
                q++;
            }
            j++;
        }
        // process all diagonals starting from row 1 to (n - 1)
        int i = 1;
        while (i < n - 1) { // TC: O(n)
            int p = i;
            int q = 0;
            while (p < n - 1 && q < m - 1) { // TC: O(m + n)
                if (mat[p][q] != mat[p + 1][q + 1]) {
                    return false;
                }
                p++;
                q++;
            }
            i++;
        }
        return true;
    }
}
