class Solution {
    private int n;

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(k x n²)
     * SC : O(n) + O(n²)
     * - O(n) - recursion stack
     * - O(n²) - path array construction
     * 
     * Accepted (201 / 201 testcases passed)
     */
    public ArrayList<ArrayList<Integer>> shortestDist(int[][] mat) {
        this.n = mat.length;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int[][] path = new int[n][n]; // SC : O(n²)
        Boolean[][] memo = new Boolean[n][n];
        boolean hasPath = solveMemoization(0, 0, mat, path, memo);
        if (!hasPath) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(-1);
            result.add(row);
        } else {
            for (int i = 0; i < n; i++) { // TC : O(n)
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < n; j++) { // TC : O(n)
                    row.add(path[i][j]);
                }
                result.add(row);
            }
        }
        return result;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(k x n²)
     * SC : O(n)
     */
    private boolean solveMemoization(int i, int j, int[][] mat,
        int[][] path, Boolean[][] memo) {
        // Base Case
        if (i >= n || j >= n || mat[i][j] == 0) {
            return false;
        }
        if (i == n - 1 && j == n - 1) {
            path[i][j] = 1;
            return true;
        }
        // Memoization Check
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        // Recursion Calls
        int steps = mat[i][j];
        path[i][j] = 1;
        /**
         * we can pick to go towards right or down (preferably right)
         */
        for (int k = 1; k <= steps; k++) { // TC : O(k)
            // right movement
            if (solveMemoization(i, j + k, mat, path, memo)) {
                return memo[i][j] = true;
            }
            // down movement
            if (solveMemoization(i + k, j, mat, path, memo)) {
                return memo[i][j] = true;
            }
        }
        path[i][j] = 0;
        return memo[i][j] = false;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n ^ (2 x n))
     * SC : O(n) + O(n²)
     * - O(n) - recursion stack
     * - O(n²) - path array construction
     * 
     * Time Limit Exceeded (200 / 201 testcases passed)
     */
    public ArrayList<ArrayList<Integer>> shortestDistRecursion(int[][] mat) {
        this.n = mat.length;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int[][] path = new int[n][n]; // SC : O(n²)
        boolean hasPath = solveRecursion(0, 0, mat, path);
        if (!hasPath) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(-1);
            result.add(row);
        } else {
            for (int i = 0; i < n; i++) { // TC : O(n)
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < n; j++) { // TC : O(n)
                    row.add(path[i][j]);
                }
                result.add(row);
            }
        }
        return result;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O((2 x n) ^ (2 x n)) ~ O(n ^ (2 x n))
     * SC : O(n)
     */
    private boolean solveRecursion(int i, int j, int[][] mat, int[][] path) {
        // Base Case
        if (i >= n || j >= n || mat[i][j] == 0) {
            return false;
        }
        if (i == n - 1 && j == n - 1) {
            path[i][j] = 1;
            return true;
        }
        // Recursion Calls
        int steps = mat[i][j];
        path[i][j] = 1;
        /**
         * we can pick to go towards right or down (preferably right)
         */
        for (int k = 1; k <= steps; k++) { // TC : O(k)
            // right movement
            if (solveRecursion(i, j + k, mat, path)) {
                return true;
            }
            // down movement
            if (solveRecursion(i + k, j, mat, path)) {
                return true;
            }
        }
        path[i][j] = 0;
        return false;
    }
}
