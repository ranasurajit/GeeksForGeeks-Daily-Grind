class Solution {
    private int m;
    private int n;
    private static int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    
    /**
     * Approach : Using Multi-source BFS Approach
     * 
     * TC: O(M x N) + O(M x N) + O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N) + O(M x N) ~ O(M x N)
     */
    public ArrayList<ArrayList<Integer>> nearest(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        int[][] dist = new int[m][n]; // SC: O(M x N) - acts as a visited array
        for (int[] row : dist) { // TC: O(M)
            Arrays.fill(row, -1); // TC: O(N)
        }
        /**
         * we will try to do Multi-source BFS from all cells 
         * with value = 1 and will do towards unvisited cells 
         * that are having value as 0
         */
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 1) {
                    dist[i][j] = 0;
                    queue.offer(new int[] { i, j });
                }
            }
        }
        while (!queue.isEmpty()) { // TC: O(M x N)
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            for (int[] dir : directions) {
                int effRow = row + dir[0];
                int effCol = col + dir[1];
                if (effRow >= 0 && effRow < m && effCol >= 0 && effCol < n &&
                    dist[effRow][effCol] == -1) {
                    dist[effRow][effCol] = dist[row][col] + 1;
                    queue.offer(new int[] { effRow, effCol });
                }
            }
        }
        ArrayList<ArrayList<Integer>> minDist = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < m; i++) { // TC: O(M)
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) { // TC: O(N)
                list.add(dist[i][j]);
            }
            minDist.add(list);
        }
        return minDist;
    }
}
