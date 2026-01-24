class Solution {
    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    int findWays(int n) {
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
     * TC: O(2 ^ N)
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
