class Solution {
    private int n;
    private int maxScoreRec = 0;
    private static final int MOD = (int) 1e9 + 7;
    
    class Result {
        int count;
        int maxScore;
        
        public Result(int count, int maxScore) {
            this.count = count;
            this.maxScore = maxScore;
        }
    }
    
    /**
     * Approach III : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n²)
     * SC : O(n) + O(n²)
     * 
     * - O(n²) - memoization memory
     * - O(n) - recursion stack
     * 
     * Accepted (242 / 242 testcases passed)
     */
    public ArrayList<Integer> findWays(int[][] grid) {
        this.n = grid.length;
        Result[][] memo = new Result[n][n]; // SC : O(n²)
        Result answer = solveMemoization(0, 0, grid, memo); // TC : O(n²), SC : O(n)
        ArrayList<Integer> result = new ArrayList<>();
        result.add(answer.count);
        result.add(answer.maxScore);
        return result;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n²)
     * SC : O(n)
     */
    private Result solveMemoization(int i, int j, int[][] grid, Result[][] memo) {
        // Base Case
        if (i >= n || j >= n) {
            return new Result(0, 0);
        }
        if (i == n - 1 && j == n - 1) {
            return new Result(1, grid[i][j]);
        }
        // Memoization Check
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        // Recursion Calls
        int count;
        int maxScore;
        if (grid[i][j] == 1) {
            // we can move right
            Result result = solveMemoization(i, j + 1, grid, memo);
            count = result.count;
            maxScore = result.maxScore;
        } else if (grid[i][j] == 2) {
            // we can move down
            Result result = solveMemoization(i + 1, j, grid, memo);
            count = result.count;
            maxScore = result.maxScore;
        } else {
            // we can move down or right
            Result resultRight = solveMemoization(i, j + 1, grid, memo);
            Result resultDown = solveMemoization(i + 1, j, grid, memo);
            count = (resultRight.count + resultDown.count) % MOD;
            maxScore = Math.max(resultRight.maxScore, resultDown.maxScore);
        }
        if (count > 0) {
            maxScore += grid[i][j];
        }
        return memo[i][j] = new Result(count, maxScore);
    }
    
    /**
     * Approach II : Using Recursion Approach
     * 
     * TC : O(4ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (43 / 242 testcases passed)
     */
    public ArrayList<Integer> findWaysRecursion(int[][] grid) {
        this.n = grid.length;
        Result answer = solveRecursion(0, 0, grid);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(answer.count);
        result.add(answer.maxScore);
        return result;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2^(2n)) ~ O(4ⁿ)
     * SC : O(n)
     */
    private Result solveRecursion(int i, int j, int[][] grid) {
        // Base Case
        if (i >= n || j >= n) {
            return new Result(0, 0);
        }
        if (i == n - 1 && j == n - 1) {
            return new Result(1, grid[i][j]);
        }
        // Recursion Calls
        Result result = null;
        if (grid[i][j] == 1) {
            // we can move right
            result = solveRecursion(i, j + 1, grid);
        } else if (grid[i][j] == 2) {
            // we can move down
            result = solveRecursion(i + 1, j, grid);
        } else {
            // we can move down or right
            Result resultRight = solveRecursion(i, j + 1, grid);
            Result resultDown = solveRecursion(i + 1, j, grid);
            int count = (resultRight.count + resultDown.count) % MOD;
            int maxScore = Math.max(resultRight.maxScore, resultDown.maxScore);
            result = new Result(count, maxScore);
        }
        if (result.count > 0) {
            result.maxScore += grid[i][j];
        }
        return result;
    }
    
    /**
     * Approach I : Using Simple Recursion Approach
     * 
     * TC : O(4ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (43 / 242 testcases passed)
     */
    public ArrayList<Integer> findWaysSimpleRecursion(int[][] grid) {
        this.n = grid.length;
        int[] score = { 0 };
        int count = solve(0, 0, grid, score);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(count);
        result.add(maxScoreRec);
        return result;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2^(2n)) ~ O(4ⁿ)
     * SC : O(n)
     */
    private int solve(int i, int j, int[][] grid, int[] score) {
        // Base Case
        if (i >= n || j >= n) {
            return 0;
        }
        if (i == n - 1 && j == n - 1) {
            score[0] += grid[i][j];
            maxScoreRec = Math.max(maxScoreRec, score[0]);
            return 1;
        }
        // Recursion Calls
        int count = 0;
        if (grid[i][j] == 1) {
            // we can move right
            count = (count + 
                (solve(i, j + 1, grid, 
                    new int[] { score[0] + grid[i][j] })) % MOD) % MOD;
        } else if (grid[i][j] == 2) {
            // we can move down
            count = (count + 
                (solve(i + 1, j, grid, 
                    new int[] { score[0] + grid[i][j] })) % MOD) % MOD;
        } else {
            // we can move down or right
            count = (count + 
                (solve(i + 1, j, grid, 
                    new int[] { score[0] + grid[i][j] })) % MOD
                + (solve(i, j + 1, grid,
                    new int[] { score[0] + grid[i][j] })) % MOD) % MOD;
        }
        return count % MOD;
    }
}
