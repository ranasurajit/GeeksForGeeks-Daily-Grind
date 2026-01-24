class Solution {
    /**
     * Approach II : Using Backtracking (To Generate Catalan Subsequences) Approach
     * 
     * TC: O(Catalan(N / 2))
     * SC: O(N)
     */
    int findWays(int n) {
        if ((n & 1) != 0) {
            // n must be even to form balanced valid parentheses
            return 0;
        }
        int totalPairs = n / 2;
        int[] ways = { 0 };
        solveBetter(0, 0, totalPairs, ways);
        return ways[0];
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(Catalan(N / 2))
     * SC: O(N)
     */
    private void solveBetter(int openUsed, int closeUsed, int total, int[] ways) {
        // Base Case
        if (openUsed == total && closeUsed == total) {
            ways[0]++;
            return;
        }
        // Recursion Calls
        if (openUsed < total) {
            solveBetter(openUsed + 1, closeUsed, total, ways);
        }
        if (openUsed > closeUsed) {
            // only after open bracket we can place a closed bracket
            solveBetter(openUsed, closeUsed + 1, total, ways);
        }
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(Catalan(N / 2))
     * SC: O(N)
     */
    int findWaysUsingBasicRecursion(int n) {
        if ((n & 1) != 0) {
            // n must be even to form balanced valid parentheses
            return 0;
        }
        int open = n / 2;
        int closed = open;
        int[] ways = { 0 };
        solve("", open, closed, n, ways);
        return ways[0];
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(Catalan(N / 2))
     * SC: O(N)
     */
    private void solve(String current, int open, int closed, int n, int[] ways) {
        // Base Case
        if (current.length() == n) {
            if (open == closed) {
                ways[0]++;
            }
            return;
        }
        // Recursion Calls
        solve(current + "(", open + 1, closed, n, ways);
        if (open > closed) {
            // only after open bracket we can place a closed bracket
            solve(current + ")", open, closed + 1, n, ways);
        }
    }
}
