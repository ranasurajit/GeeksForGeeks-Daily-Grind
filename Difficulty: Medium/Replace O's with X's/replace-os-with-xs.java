class Solution {
    private int n;
    private int m;
    private static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    /**
     * Approach : Using Multi-Source BFS Approach
     * 
     * TC: O(N x M) + O(4 x N x M) + O(N x M) ~ O(N x M)
     * SC: O(N x M)
     */
    public void fill(char[][] grid) {
        this.n = grid.length;
        this.m = grid[0].length;
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(N x M)
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < m; j++) { // TC: O(M)
                if (isBoundaryCellWithZero(grid, i, j)) {
                    queue.offer(new int[] { i, j });
                    grid[i][j] = '$'; // marking cell as visited
                }
            }
        }
        while (!queue.isEmpty()) { // TC: O(N x M)
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            grid[row][col] = '$'; // marking cell as visited
            for (int[] dir : directions) { // TC: O(4)
                int effRow = row + dir[0];
                int effCol = col + dir[1];
                if (effRow >= 0 && effRow < n && effCol >= 0 && effCol < m &&
                    grid[effRow][effCol] == 'O') {
                    grid[effRow][effCol] = '$'; // marking cell as visited
                    queue.offer(new int[] { effRow, effCol });
                }
            }
        }
        // converting safe cells to 'O' and unsafe cells as 'X'
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < m; j++) { // TC: O(M)
                if (grid[i][j] == '$') {
                    grid[i][j] = 'O'; // safe cells
                } else {
                    grid[i][j] = 'X'; // unsafe cells
                }
            }
        }
    }
    
    /**
     * Using Enumeration Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isBoundaryCellWithZero(char[][] grid, int r, int c) {
        return (r == 0 || r == n - 1|| c == 0 || c == m - 1) && grid[r][c] == 'O';
    }
}
