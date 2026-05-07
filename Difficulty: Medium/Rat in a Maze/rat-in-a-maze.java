class Solution {
    private int n;
    private boolean[][] visited;
    
    /**
     * Approach : Using Backtracking Approach
     * 
     * TC : O(4 ^ (n x n))
     * SC : O(n x n)
     */
    public ArrayList<String> ratInMaze(int[][] maze) {
        this.n = maze.length;
        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0) {
            // rat cannot travel through
            return new ArrayList<>();
        }
        this.visited = new boolean[n][n];
        ArrayList<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        visited[0][0] = true;
        solve(0, 0, n, maze, sb, result);
        return result;
    }

    /**
     * Using Backtracking Approach
     * 
     * TC : O(4 ^ (n x n))
     * SC : O(n x n)
     */
    private void solve(int i, int j, int n, int[][] maze,
        StringBuilder sb, ArrayList<String> result) {
        // Base Case
        if (i == n - 1 && j == n - 1) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        // down movement
        if (i + 1 < n && j < n && !visited[i + 1][j] && maze[i + 1][j] == 1) {
            visited[i + 1][j] = true;      // modify
            sb.append('D');                // modify
            solve(i + 1, j, n, maze, sb, result); // explore
            visited[i + 1][j] = false;     // backtrack
            sb.setLength(sb.length() - 1); // backtrack
        }
        // left movement
        if (i < n && j - 1 >= 0 && !visited[i][j - 1] && maze[i][j - 1] == 1) {
            visited[i][j - 1] = true;      // modify
            sb.append('L');                // modify
            solve(i, j - 1, n, maze, sb, result); // explore
            visited[i][j - 1] = false;     // backtrack
            sb.setLength(sb.length() - 1); // backtrack
        }
        // right movement
        if (i < n && j + 1 < n && !visited[i][j + 1] && maze[i][j + 1] == 1) {
            visited[i][j + 1] = true;      // modify
            sb.append('R');                // modify
            solve(i, j + 1, n, maze, sb, result); // explore
            visited[i][j + 1] = false;     // backtrack
            sb.setLength(sb.length() - 1); // backtrack
        }
        // up movement
        if (i - 1 >= 0 && j < n && !visited[i - 1][j] && maze[i - 1][j] == 1) {
            visited[i - 1][j] = true;      // modify
            sb.append('U');                // modify
            solve(i - 1, j, n, maze, sb, result); // explore
            visited[i - 1][j] = false;     // backtrack
            sb.setLength(sb.length() - 1); // backtrack
        }
    }
}
