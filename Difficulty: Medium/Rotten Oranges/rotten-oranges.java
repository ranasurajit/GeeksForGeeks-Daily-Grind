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
                } else if (mat[i][j] == 2) {
                    // rotten oranges
                    queue.offer(new Pair(i, j));
                }
            }
        }
        int time = 0;
        while (!queue.isEmpty()) { // TC: O(M x N)
            int size = queue.size();
            boolean hasEntered = false;
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                int row = pair.row;
                int col = pair.col;
                for (int[] dir : directions) { // TC: O(4)
                    int effRow = row + dir[0];
                    int effCol = col + dir[1];
                    if (effRow < 0 || effRow >= m || effCol < 0 || effCol >= n) {
                        continue;
                    }
                    if (mat[effRow][effCol] == 1) {
                        mat[effRow][effCol] = 2; // marking cell as visited
                        freshOranges--;
                        queue.offer(new Pair(effRow, effCol));
                        hasEntered = true;
                    }
                }
            }
            if (hasEntered) {
                time++;
            }
        }
        return freshOranges != 0 ? -1 : time;
    }
}

class Pair {
    int row;
    int col;
    
    public Pair (int row, int col) {
        this.row = row;
        this.col = col;
    }
}
