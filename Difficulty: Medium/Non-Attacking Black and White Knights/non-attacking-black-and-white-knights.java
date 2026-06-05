class Solution {
    private static final int[][] directions = {
        { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 },
        { 2, 1 }, { -2, 1 }, { 2, -1 }, { -2, -1 }
    };

    /**
     * Approach I : Using Backtracking Approach
     * 
     * TC : O(n x m)
     * SC : O(1)
     */
    public int numOfWays(int n, int m) {
        /**
         * To find the number of ways to place one black
         * and one white knight = total number of ways 
         * - ways of possible attacks
         * 
         * total ways = (n x m) ways to place one knight
         * and (n x m) - 1 ways to place the other knight
         */
        long totalWays = (n * m) * (n * m - 1);
        long attacks = solve(n, m); // TC : O(n x m), SC : O(1)
        return (int) (totalWays - attacks);
    }
    
    /**
     * Using Backtracking Approach
     * 
     * TC : O(n x m)
     * SC : O(1)
     */
    private long solve(int n, int m) {
        long attacks = 0;
        for (int i1 = 0; i1 < n; i1++) { // TC : O(n)
            for (int j1 = 0; j1 < m; j1++) { // TC : O(m)
                for (int[] dir : directions) { // TC : O(8)
                    int i2 = i1 + dir[0];
                    int j2 = j1 + dir[1];
                    if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < m) {
                        attacks++;
                    }
                }
            }
        }
        return attacks;
    }
};
