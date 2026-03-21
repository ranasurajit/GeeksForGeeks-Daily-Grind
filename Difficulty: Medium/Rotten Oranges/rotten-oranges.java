class Solution {
    private static int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    /**
     * Approach : Using Multi-Source BFS Approach
     * 
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N) + O(M x N) ~ O(M x N)
     */
    public int orangesRot(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visited = new boolean[m][n]; // SC: O(M x N)
        int freshOranges = 0;
        /**
         * From all cells having rotten oranges
         * we can perform Multi-Source BFS to
         * rot the fresh oranges
         */
        Queue<Pair> queue = new LinkedList<>(); // SC: O(M x N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] == 1) {
                    freshOranges++;
                } else if (mat[i][j] == 0) {
                    visited[i][j] = true;
                } else {
                    // rotten oranges
                    queue.offer(new Pair(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }
        int maxTime = 0;
        while (!queue.isEmpty()) { // TC: O(M x N)
            Pair pair = queue.poll();
            int row = pair.row;
            int col = pair.col;
            int time = pair.time;
            maxTime = Math.max(maxTime, time);
            for (int[] dir : directions) { // TC: O(4)
                int effRow = row + dir[0];
                int effCol = col + dir[1];
                if (effRow < 0 || effRow >= m || effCol < 0 || effCol >= n) {
                    continue;
                }
                if (!visited[effRow][effCol] && mat[effRow][effCol] == 1) {
                    freshOranges--;
                    visited[effRow][effCol] = true;
                    queue.offer(new Pair(effRow, effCol, time + 1));
                }
            }
        }
        return freshOranges != 0 ? -1 : maxTime;
    }
}

class Pair {
    int row;
    int col;
    int time;
    
    public Pair (int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
