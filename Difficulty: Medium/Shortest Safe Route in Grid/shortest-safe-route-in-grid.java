class Solution {
    private static final int[][] directions = {
        { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 }
    };
    
    private int n;
    private int m;

    int shortestPath(int[][] mat) {
        this.n = mat.length;
        this.m = mat[0].length;
        boolean[][] visited = new boolean[n][m]; // SC : O(n x m)
        Queue<Cell> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (isCellSafe(mat, i, 0)) {
                queue.offer(new Cell(i, 0, 1));
            }
            visited[i][0] = true;
        }
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int r = current.row;
            int c = current.col;
            int s = current.step;
            if (c == m - 1) {
                return s;
            }
            for (int[] dir : directions) {
                int effRow = r + dir[0];
                int effCol = c + dir[1];
                if (effRow < 0 || effRow >= n || effCol < 0 || effCol >= m) {
                    continue;
                }
                if (!visited[effRow][effCol] &&
                    isCellSafe(mat, effRow, effCol)) {
                    visited[effRow][effCol] = true;
                    queue.offer(new Cell(effRow, effCol, s + 1));
                }
            }
        }
        return -1;
    }

    private boolean isCellSafe(int[][] mat, int r, int c) {
        for (int[] dir : directions) {
            int effRow = r + dir[0];
            int effCol = c + dir[1];
            if (effRow < 0 || effRow >= n || effCol < 0 || effCol >= m) {
                continue;
            }
            if (mat[effRow][effCol] == 0) {
                // cell is unsafe
                return false;
            }
        }
        return true;
    }
}

class Cell {
    int row;
    int col;
    int step;
    
    public Cell(int row, int col, int step) {
        this.row = row;
        this.col = col;
        this.step = step;
    }
}
