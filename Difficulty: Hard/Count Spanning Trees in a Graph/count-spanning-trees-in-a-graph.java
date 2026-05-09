class Solution {
    /**
     * Approach : Using Kirchoff's Matrix Tree Approach
     * 
     * TC : O(m) + O(n²) + O(m) + O(n²) + O(n!) ~ O(n!)
     * SC : O(n) + O(n²) + O(n²) + O(n²) ~ O(n²)
     */
    public int countSpanTree(int n, int[][] edges) {
        if (n == 1) {
            return 1;
        }
        int[] indegrees = new int[n]; // SC : O(n)
        for (int[] edge : edges) {    // TC : O(m)
            indegrees[edge[0]]++;
            indegrees[edge[1]]++;
        }
        /**
         * We need to create a Matrix Tree of size (n x n)
         * and we need to set the diagonal values i.e. 
         * matrix[i][i] = indegrees[i]
         */
        int[][] matrix = new int[n][n]; // SC : O(n²)
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int j = 0; j < n; j++) { // TC : O(n)
                if (i == j) {
                    matrix[i][j] = indegrees[i];
                }
            }
        }
        /**
         * need to set the adjacent vertices connected by an
         * edge = -1 and others will remain as 0
         */
        for (int[] edge : edges) {    // TC : O(m)
            int u = edge[0];
            int v = edge[1];
            // u and v are adjacent vertices connected by an edge
            matrix[u][v] = -1;
            matrix[v][u] = -1;
        }
        /**
         * now we can ignore any row and any column say row 0 and column 0
         * and then we need to find the determinant of (n - 1) x (n - 1)
         * matrix and return and that is the count of Spanning Trees
         */
        int[][] sub = getSubMatrix(matrix, 0, 0, n); // TC : O(n²) , SC : O(n²)
        return determinant(sub, n - 1); // TC : O(n!), SC : O(n²)
    }
    
    /**
     * Using 2D-Matrix Simulation Approach
     * 
     * TC : O(n!)
     * SC : O(n²)
     */
    private int determinant(int[][] matrix, int n) {
        // Base Case
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        }
        // Recursion Calls
        int det = 0;
        for (int col = 0; col < n; col++) { // TC : O(n)
            int[][] sub =
                getSubMatrix(matrix, 0, col, n); // TC : O(n²), SC : O(n²)
            int sign = (col & 1) == 0 ? 1 : -1;
            det += sign * matrix[0][col] * determinant(sub, n - 1);
        }
        return det;
    }
    
    /**
     * Using 2D-Matrix Simulation Approach
     * 
     * TC : O(n²)
     * SC : O(n²)
     */
    private int[][] getSubMatrix(int[][] mat, int skipRow, int skipCol, int n) {
        int[][] sub = new int[n - 1][n - 1]; // SC : O(n x n)
        int r = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (i == skipRow) {
                continue;
            }
            int c = 0;
            for (int j = 0; j < n; j++) { // TC : O(n)
                if (j == skipCol) {
                    continue;
                }
                sub[r][c++] = mat[i][j];
            }
            r++;
        }
        return sub;
    }
}
