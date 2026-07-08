class Solution {
    private static final int[][] directions = {
        { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };

    /**
     * Approach : Using Multi-Source BFS Approach
     * 
     * TC : O(n x m) + O(n x m) + O(n x m) ~ O(n x m)
     * SC : O(n x m) + O(n x m) + O(n x m) ~ O(n x m)
     */
    public int countCoordinates(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        boolean[][] visitedP = new boolean[n][m]; // SC : O(n x m)
        boolean[][] visitedQ = new boolean[n][m]; // SC : O(n x m)
        /**
         * we will perform Multi-Source BFS from Top-Left cells
         * (adjacent to Station P) and will mark the visited 
         * cells in 'visitedQ' as true if signal strength >= the 
         * signal strength in current cell and similary we would 
         * perform the same from Bottom-Right cells (adjacent to
         * Station P) and will mark the visited cells in 'visitedQ'
         * as true if signal strength >= the signal strength in
         * current cell
         */
        multiSourceBFS(mat, n, m, visitedP, 0); // TC : O(n x m), SC : O(n x m)
        multiSourceBFS(mat, n, m, visitedQ, 1); // TC : O(n x m), SC : O(n x m)
        /**
         * now we will count the cells which is marked visited as 
         * 'true' in both 'visitedP' and 'visitedQ' grids
         */
        int count = 0;
        for (int i = 0; i < n; i++) {     // TC : O(n)
            for (int j = 0; j < m; j++) { // TC : O(m)
                if (visitedP[i][j] && visitedQ[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Using Multi-Source BFS Approach
     * 
     * TC : O(n x m) + O(n x m) ~ O(n x m)
     * SC : O(n x m)
     */
    private void multiSourceBFS(int[][] mat, int n, int m,
        boolean[][] visited, int type) {
        /**
         * we will perform Multi-Source BFS from Top-Left cells
         * (adjacent to Station P) and will mark the visited 
         * cells in 'visited' grid as true if signal strength 
         * >= the signal strength in current cell
         */
        Queue<int[]> queue = new LinkedList<>(); // SC : O(n x m)
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int j = 0; j < m; j++) { // TC : O(m)
                if (type == 0) {
                    if (i == 0 || j == 0) {
                        queue.offer(new int[] { i, j });
                        visited[i][j] = true;
                    }
                } else {
                    if (i == n - 1 || j == m - 1) {
                        queue.offer(new int[] { i, j });
                        visited[i][j] = true;
                    }
                }
            }
        }
        while (!queue.isEmpty()) { // TC : O(n x m)
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            for (int[] dir : directions) { // TC : O(4)
                int i_ = i + dir[0];
                int j_ = j + dir[1];
                if (i_ < 0 || i_ >= n || j_ < 0 || j_ >= m ||
                    visited[i_][j_] || mat[i_][j_] < mat[i][j]) {
                    // invalid cells / already visited // signal is less to travel
                    continue;
                }
                visited[i_][j_] = true;
                queue.offer(new int[] { i_, j_ });
            }
        }
    }
}
