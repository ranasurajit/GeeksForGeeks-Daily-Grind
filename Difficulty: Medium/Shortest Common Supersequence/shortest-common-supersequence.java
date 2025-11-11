class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N1 x N2)
     * SC: O(N1 x N2) + O(N1 + N2)
     * 
     * - O(N1 x N2) - memoization memory
     * - O(N1 + N2) - recursion stack
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public static int minSuperSeq(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] memo = new int[n1][n2]; // SC: O(N1 x N2)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        int lcs = solveMemoization(n1 - 1, n2 - 1, s1, s2, memo); // TC: O(N1 x N2), SC: O(N1 + N2)
        return n1 + n2 - lcs;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N1 x N2)
     * SC: O(N1 + N2)
     */
    private static int solveMemoization(int i, int j, String s1, String s2, int[][] memo) {
        // Base Case
        if (i < 0 || j < 0) {
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j] = 1 + solveMemoization(i - 1, j - 1, s1, s2, memo);
        } else {
            return memo[i][j] = 
                Math.max(solveMemoization(i - 1, j, s1, s2, memo),
                    solveMemoization(i, j - 1, s1, s2, memo));
        }
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ Min(N1, N2))
     * SC: O(N1 + N2)
     * 
     * - O(N1 + N2) - recursion stack
     * 
     * Time Limit Exceeded (10 / 1111 testcases passed)
     */
    public static int minSuperSeqRecursion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int lcs = solveRecursion(n1 - 1, n2 - 1, s1, s2); // TC: O(2 ^ Min(N1, N2)), SC: O(N1 + N2)
        return n1 + n2 - lcs;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ Min(N1, N2))
     * SC: O(N1 + N2)
     */
    private static int solveRecursion(int i, int j, String s1, String s2) {
        // Base Case
        if (i < 0 || j < 0) {
            return 0;
        }
        // Recursion Calls
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + solveRecursion(i - 1, j - 1, s1, s2);
        } else {
            return Math.max(solveRecursion(i - 1, j, s1, s2),
                solveRecursion(i, j - 1, s1, s2));
        }
    }
}
