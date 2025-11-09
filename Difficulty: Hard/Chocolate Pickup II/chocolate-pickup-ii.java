class Solution {
    private int n;
    
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N ^ 4)
     * SC: O(N ^ 4) + O(N ^ 4)
     * 
     * Time Limit Exceeded (1061 / 1061 testcases passed)
     */
    public int chocolatePickup(int[][] mat) {
        this.n = mat.length;
        int[][][][] memo = new int[n][n][n][n]; // SC: O(N ^ 4)
        for (int[][][] mem : memo) {
            for (int[][] me : mem) {
                for (int[] d : me) {
                    Arrays.fill(d, -1);
                }
            }
        }
        int maxChocolates = solveMemoization(0, 0, 0, 0, mat, memo); // TC: O(4 ^ (2 x N)), SC: O(N)
        return maxChocolates < 0 ? 0 : maxChocolates;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N ^ 4)
     * SC: O(N ^ 4)
     */
    private int solveMemoization(int i1, int j1, int i2, int j2, int[][] mat, int[][][][] memo) {
        // Base Case 1 - Out of Bounds Check
        if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n || j1 < 0 || j1 >= n || j2 < 0 || j2 >= n) {
            return Integer.MIN_VALUE;
        }
        // Base Case 2 - Blocked Cell Check
        if (mat[i1][j1] == -1 || mat[i2][j2] == -1) {
            return Integer.MIN_VALUE;
        }
        // Base Case 3 - when both robots reaches (n - 1, n - 1)
        if (i1 == n - 1 && i2 == n - 1 && j1 == n - 1 && j2 == n - 1) {
            if (i1 == i2 && j1 == j2) {
                return mat[i1][j1]; // only one robot can get the chocolates in cell (0, 0)
            } else {
                return mat[i1][j1] + mat[i2][j2];
            }
        }
        // Memoization Check
        if (memo[i1][j1][i2][j2] != -1) {
            return memo[i1][j1][i2][j2];
        }
        // Recursion Calls
        int chocolates = 0;
        chocolates += (i1 == i2 && j1 == j2) ? mat[i1][j1] : (mat[i1][j1] + mat[i2][j2]);
        int maxCollect = Integer.MIN_VALUE;
        maxCollect = Math.max(maxCollect,
            solveMemoization(i1 + 1, j1, i2 + 1, j2, mat, memo)); // r1 down, r2 down
        maxCollect = Math.max(maxCollect, 
            solveMemoization(i1, j1 + 1, i2, j2 + 1, mat, memo)); // r1 right, r2 right
        maxCollect = Math.max(maxCollect, 
            solveMemoization(i1, j1 + 1, i2 + 1, j2, mat, memo)); // r1 right, r2 down
        maxCollect = Math.max(maxCollect,
            solveMemoization(i1 + 1, j1, i2, j2 + 1, mat, memo)); // r1 down, r2 right
        return memo[i1][j1][i2][j2] = chocolates + maxCollect;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(4 ^ (2 x N))
     * SC: O(N)
     * 
     * Time Limit Exceeded (1010 / 1061 testcases passed)
     */
    public int chocolatePickupRecursion(int[][] mat) {
        this.n = mat.length;
        int maxChocolates = solveRecursion(0, 0, 0, 0, mat); // TC: O(4 ^ (2 x N)), SC: O(N)
        return maxChocolates < 0 ? 0 : maxChocolates;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(4 ^ (2 x N))
     * SC: O(N)
     */
    private int solveRecursion(int i1, int j1, int i2, int j2, int[][] mat) {
        // Base Case 1 - Out of Bounds Check
        if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n || j1 < 0 || j1 >= n || j2 < 0 || j2 >= n) {
            return Integer.MIN_VALUE;
        }
        // Base Case 2 - Blocked Cell Check
        if (mat[i1][j1] == -1 || mat[i2][j2] == -1) {
            return Integer.MIN_VALUE;
        }
        // Base Case 3 - when both robots reaches (n - 1, n - 1)
        if (i1 == n - 1 && i2 == n - 1 && j1 == n - 1 && j2 == n - 1) {
            if (i1 == i2 && j1 == j2) {
                return mat[i1][j1]; // only one robot can get the chocolates in cell (0, 0)
            } else {
                return mat[i1][j1] + mat[i2][j2];
            }
        }
        // Recursion Calls
        int chocolates = 0;
        chocolates += (i1 == i2 && j1 == j2) ? mat[i1][j1] : (mat[i1][j1] + mat[i2][j2]);
        int maxCollect = Integer.MIN_VALUE;
        maxCollect = Math.max(maxCollect,
            solveRecursion(i1 + 1, j1, i2 + 1, j2, mat)); // r1 down, r2 down
        maxCollect = Math.max(maxCollect, 
            solveRecursion(i1, j1 + 1, i2, j2 + 1, mat)); // r1 right, r2 right
        maxCollect = Math.max(maxCollect, 
            solveRecursion(i1, j1 + 1, i2 + 1, j2, mat)); // r1 right, r2 down
        maxCollect = Math.max(maxCollect,
            solveRecursion(i1 + 1, j1, i2, j2 + 1, mat)); // r1 down, r2 right
        return chocolates + maxCollect;
    }
}
