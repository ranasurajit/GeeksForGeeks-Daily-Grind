class Solution {
    private final int[][] directions = {
        { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
    };
    
    private int n;
    private int m;

    /**
     * Approach : Using Graph DFS Approach
     * 
     * TC : O(n + m) + O(n x m) ~ O(n x m)
     * SC : O(n + m)
     */
    int cntOnes(int[][] grid) {
        this.n = grid.length;
        this.m = grid[0].length;
        for (int j = 0; j < m; j++) { // TC : O(m)
            // top and bottom row boundary
            dfsGrid(0, j, grid);
            dfsGrid(n - 1, j, grid);
        }
        for (int i = 0; i < n; i++) { // TC : O(n)
            // left and right column boundary
            dfsGrid(i, 0, grid);
            dfsGrid(i, m - 1, grid);
        }
        // counting grid 1's those were not visited
        int count1s = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int j = 0; j < m; j++) { // TC : O(m)
                if (grid[i][j] == 1) {
                    count1s++;
                }
            }
        }
        return count1s;
    }
    
    /**
     * Using Graph DFS Approach
     * 
     * TC : O(n x m)
     * SC : O(n + m)
     */
    private void dfsGrid(int i, int j, int[][] grid) {
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = -1;
        for (int[] dir : directions) {
            int effI = i + dir[0];
            int effJ = j + dir[1];
            dfsGrid(effI, effJ, grid);
        }
    }
};
