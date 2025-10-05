class Solution {
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private static final char[] dirMarker = { 'D', 'U', 'R', 'L' };
    private int m;
    private int n;

    public ArrayList<String> ratInMaze(int[][] maze) {
        this.m = maze.length;
        this.n = maze[0].length;
        ArrayList<String> result = new ArrayList<String>();
        if (maze[0][0] == 0) {
            return result;
        }
        solveRecursion(0, 0, new StringBuilder(), maze, result);
        Collections.sort(result, (a, b) -> a.compareTo(b));
        return result;
    }
    
    private void solveRecursion(int r, int c, StringBuilder sb, int[][] maze, 
        ArrayList<String> result) {
        // Base Case
        if (maze[r][c] == 2) {
            return;
        }
        if (r == m - 1 && c == n - 1) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        for (int i = 0; i < directions.length; i++) {
            int effRow = r + directions[i][0];
            int effCol = c + directions[i][1];
            if (effRow >= 0 && effRow < m && effCol >= 0 && effCol < n && 
            maze[effRow][effCol] == 1) {
                int temp = maze[r][c];
                sb.append(dirMarker[i]);
                maze[r][c] = 2;
                solveRecursion(effRow, effCol, sb, maze, result); // explore
                // backtrack
                sb.setLength(sb.length() - 1);
                maze[r][c] = temp;
            }
        }
    }
}
