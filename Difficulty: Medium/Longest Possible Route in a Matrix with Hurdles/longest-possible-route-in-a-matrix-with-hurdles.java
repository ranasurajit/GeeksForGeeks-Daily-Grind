class Solution {
    private static final int[][] directions = {
        { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };

    private int n;
    private int m;

    /**
     * Approach : Using Backtracking Approach
     * 
     * TC : O(4 ^ (n x m))
     * SC : O(n x m) + O(n x m) ~ O(n x m)
     *  - O(n x m) - visited array space
     *  - O(n x m) - recursion stack
     */
    public int longestPath(int[][] mat, int xs, int ys, int xd, int yd) {
        this.n = mat.length;
        this.m = mat[0].length;
        boolean[][] visited = new boolean[n][m]; // SC : O(n x m)
        return backtrackGrid(xs, ys, visited, mat, xd, yd);
    }
    
    /**
     * Using Backtracking Approach
     * 
     * TC : O(4 ^ (n x m))
     * SC : O(n x m)
     */
    private int backtrackGrid(int i, int j, boolean [][] visited,
        int[][] mat, int xd, int yd) {
        // Base Case
        if (i < 0 || i >= n || j < 0 || j >= m ||
            mat[i][j] == 0 || visited[i][j]) {
            // invalid / blocked / visited cell
            return -1;    
        }
        if (i == xd && j == yd) {
            return 0;
        }
        visited[i][j] = true; // modify
        int dist = -1;
        for (int[] dir : directions) {
            int i_ = i + dir[0];
            int j_ = j + dir[1];
            int currentDist =
                backtrackGrid(i_, j_, visited, mat, xd, yd); // explore
            if (currentDist != -1) {
                dist = Math.max(dist, 1 + currentDist);
            }
        }
        visited[i][j] = false; // backtrack
        return dist;
    }
}
