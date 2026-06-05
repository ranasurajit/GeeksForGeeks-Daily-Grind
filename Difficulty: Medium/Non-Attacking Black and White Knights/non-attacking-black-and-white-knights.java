class Solution {
    /**
     * Approach : Using Math Approach
     * 
     * TC : O(1)
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
        long attacks = 0L;
        /**
         * consider n as rows so it decides vertical movement
         * and m as columns and it decides horizontal movement
         */
        if (n > 2 && m > 1) {
            attacks += 4L * (n - 2) * (m - 1);
        }
        if (n > 1 && m > 2) {
            attacks += 4L * (n - 1) * (m - 2);
        }
        return (int) (totalWays - attacks);
    }
};
