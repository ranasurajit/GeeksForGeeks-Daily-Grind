class Solution {
    private static int[][] directions = {
        { 1, 0 },  // down
        { -1, 0 }, // up
        { 0, 1 },  // right
        { 0, -1 }  // left
    };
    private static final int INF = Integer.MAX_VALUE;

    private int n;
    private int m;

    /**
     * Approach : Using Graph BFS Approach
     * 
     * TC : O(n x m)
     * SC : O(n x m)
     */
    public int numberOfCells(int r, int c, int u, int d, char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        if (mat[r][c] == '#') {
            return 0;
        }

        /*
         * dist[i][j] = minimum number of upward moves
         * required to reach (i, j).
         */
        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        /*
         * 0-1 BFS:
         *
         * up    -> cost 1
         * down  -> cost 0
         * left  -> cost 0
         * right -> cost 0
         */
        Deque<int[]> deque = new ArrayDeque<>();

        dist[r][c] = 0;
        deque.offerFirst(new int[]{r, c});

        while (!deque.isEmpty()) {

            int[] current = deque.pollFirst();

            int row = current[0];
            int col = current[1];

            int currentUp = dist[row][col];

            for (int[] direction : directions) {

                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow < 0 || newRow >= n ||
                    newCol < 0 || newCol >= m ||
                    mat[newRow][newCol] == '#') {
                    continue;
                }

                /*
                 * Moving upward costs 1.
                 * All other directions cost 0.
                 */
                int cost = (direction[0] == -1) ? 1 : 0;

                int newUp = currentUp + cost;

                if (newUp < dist[newRow][newCol]) {

                    dist[newRow][newCol] = newUp;

                    if (cost == 0) {
                        deque.offerFirst(new int[]{newRow, newCol});
                    } else {
                        deque.offerLast(new int[]{newRow, newCol});
                    }
                }
            }
        }

        int answer = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (mat[row][col] == '#' || dist[row][col] == INF) {
                    continue;
                }

                int upUsed = dist[row][col];

                /*
                 * row = r + down - up
                 *
                 * Therefore:
                 *
                 * down = up + row - r
                 */
                int downUsed = upUsed + row - r;

                if (upUsed <= u && downUsed <= d) {
                    answer++;
                }
            }
        }
        return answer;
    }

    /**
     * Using Enumeration Approach
     * 
     * TC : O(1)
     * SC : O(1)
     */
    private boolean isValidCell(int row, int col, char[][] mat) {
        if (row < 0 || row >= n || col < 0 || col >= m || mat[row][col] == '#') {
            return false;
        }
        return true;
    }
}
