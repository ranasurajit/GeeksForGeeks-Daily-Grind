class Solution {
    /**
     * Approach : Using Memoization Approach
     * 
     * TC: O(M x N)
     * SC: O(M x N) + O(M x N)
     * 
     * - O(M x N) - memoization memory
     * - O(M x N) - recursion stack
     */
    public boolean wildCard(String txt, String pat) {
        int m = pat.length();
        int n = txt.length();
        // Using memoization
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return solve(pat, txt, m - 1, n - 1, dp);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     */
    private boolean solve(String pattern, String str, int m, int n, int[][] dp) {
        if (m < 0 && n < 0) {
            // if both pointers exhaust
            return true;
        }
        if (m < 0) {
            // pattern pointer exhausts
            return false;
        }
        if (n < 0) {
            // str pointer exhausts
            while (m >= 0) {
                if (pattern.charAt(m) != '*') {
                    // check if any character left in pattern is other than '*''
                    return false;
                }
                m--;
            }
            return true;
        }
        if (dp[m][n] != -1) {
            return dp[m][n] == 1 ? true : false;
        }
        if (pattern.charAt(m) == str.charAt(n) || pattern.charAt(m) == '?') {
            boolean result = solve(pattern, str, m - 1, n - 1, dp);
            dp[m][n] = result ? 1 : 0;
            return result;
        }
        if (pattern.charAt(m) == '*') {
            /**
             * 1st case - ignore * and check for next characters in pattern
             * 2nd case - keep pointer at * in pattern and keep checking 
             * for next characters in str
             */
            boolean result = solve(pattern, str, m - 1, n, dp) || 
                solve(pattern, str, m, n - 1, dp);
            dp[m][n] = result ? 1 : 0;
            return result;
        }
        return false;
    }
}
