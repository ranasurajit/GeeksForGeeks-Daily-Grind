class Solution {
    private static final int[][] directions = {
        { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 },
        { -2, -1 }, { -2, 1 }, { 2, -1 }, { 2, 1 }
    };

    private int[][] visited;
    private boolean found = false;
    private int n;

    /**
     * Approach II : Using Warnsdorff’s Rule (Recursion + Backtracking) Approach
     * 
     * TC: O((N x N)) + O(N x N) + O(N x N) ~ O(N x N)
     * Worst Case: O(8 ^ (N x N))
     * 
     * SC: O(N x N) + O(N x N) ~ O(N x N)
     * 
     * Accepted (6 / 6 test cases passed)
     */
    public ArrayList<ArrayList<Integer>> knightTour(int n) {
        this.n = n;
        this.visited = new int[n][n]; // SC: O(N x N)
        for (int[] vis : visited) { // TC: O(N)
            // marking all cells unvisited
            Arrays.fill(vis, -1); // TC: O(N)
        }
        visited[0][0] = 0; // starting point mark visited
        found = false;
        backtrack(0, 0, 1);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {     // TC: O(N)
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) { // TC: O(N)
                list.add(visited[i][j]);
            }
            result.add(list);
        }
        return found ? result : new ArrayList<ArrayList<Integer>>();
    }
    
    private void backtrack(int row, int col, int current) {
        // Base Case
        if (found) {
            // early return
            return;
        }
        if (current == n * n) {
            found = true;
            return;
        }
        // Recursion Calls
        List<int[]> nextMoves = getNextMoves(row, col);
        nextMoves.sort(Comparator.comparingInt(m -> compareOnwardMove(m[0], m[1]))); // TC: O(N x N x log(N))
        for (int[] move : nextMoves) { // TC: O(8)
            int effRow = move[0];
            int effCol = move[1];
            visited[effRow][effCol] = current; // modify
            backtrack(effRow, effCol, current + 1); // explore
            if (found) {
                return;
            }
            visited[effRow][effCol] = -1;
        }
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private List<int[]> getNextMoves(int row, int col) {
        List<int[]> moves = new ArrayList<int[]>();
        for (int[] dir : directions) { // TC: O(8)
            int effRow = row + dir[0];
            int effCol = col + dir[1];
            if (isValidMove(effRow, effCol)) {
                moves.add(new int[] { effRow, effCol });
            }
        }
        return moves;
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private int compareOnwardMove(int row, int col) {
        int count = 0;
        for (int[] dir : directions) { // TC: O(8)
            int effRow = row + dir[0];
            int effCol = col + dir[1];
            if (isValidMove(effRow, effCol)) { // TC: O(1)
                count++;
            }
        }
        return count;
    }

    /**
     * Approach I : Using Recursion + Backtracking Approach
     * 
     * TC: O(8 ^ (N x N)) + O(N x N) + O(N x N) ~ O(8 ^ (N x N))
     * SC: O(N x N) + O(N x N) ~ O(N x N)
     * 
     * Time Limit Exceeded (0 / 6 test cases passed)
     */
    public ArrayList<ArrayList<Integer>> knightTourBacktracking(int n) {
        this.n = n;
        this.visited = new int[n][n]; // SC: O(N x N)
        for (int[] vis : visited) { // TC: O(N)
            // marking all cells unvisited
            Arrays.fill(vis, -1); // TC: O(N)
        }
        visited[0][0] = 0; // starting point mark visited
        found = solveRecursionBacktracking(0, 0, 0); // TC: O(8 ^ (N x N)), SC: O(N x N)
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (!found) {
            return result;
        }
        for (int i = 0; i < n; i++) {     // TC: O(N)
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) { // TC: O(N)
                list.add(visited[i][j]);
            }
            result.add(list);
        }
        return result;
    }

    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(8 ^ (N x N))
     * SC: O(N x N)
     */
    private boolean solveRecursionBacktracking(int row, int col, int current) {
        // Base Case
        if (found) {
            return true;
        }
        if (current == n * n - 1) {
            // we visited all cells
            found = true;
            return true;
        }
        // Recursion Calls
        for (int[] dir : directions) { // TC: O(8)
            int effRow = row + dir[0];
            int effCol = col + dir[1];
            if (isValidMove(effRow, effCol)) {
                visited[effRow][effCol] = current + 1; // modify
                if (solveRecursionBacktracking(effRow, effCol, current + 1)) {  // explore
                    return true;
                }
                visited[effRow][effCol] = -1; // backtrack
            }
        }
        return false;
    }
    
    /**
     * Using Enumeration Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isValidMove(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n && visited[r][c] == -1;
    }
}
