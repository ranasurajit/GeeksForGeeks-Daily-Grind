class Solution {
    private String s1;
    private String s2;
    private String s3;
    private int n1;
    private int n2;
    private int n3;
    
    /**
     * Approach III : Using Optimized Memoization Approach
     * 
     * TC: O(N1 x N2)
     * SC: O(N1 x N2) + O(N1 x N2)
     * 
     * - O(N1 x N2) - memoization memory
     * - O(N1 x N2) - recursion stack
     * 
     * Accepted (1111 /1111 testcases passed)
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.n1 = s1.length();
        this.n2 = s2.length();
        this.n3 = s3.length();
        if (n1 + n2 != n3) {
            return false;
        }
        // k is dependent on n1 and n2 so we can reduce it to two states only
        int[][] memo = new int[n1 + 1][n2 + 1]; // SC: O(N1 x N2)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveOptimizedMemoization(0, 0, memo); // TC: O(N1 x N2), SC: O(N1 x N2)
    }
    
    /**
     * Using Optimized Memoization Approach
     * 
     * TC: O(N1 x N2)
     * SC: O(N1 x N2)
     */
    private boolean solveOptimizedMemoization(int i, int j, int[][] memo) {
        int k = i + j;
        // Base Case
        if (i == n1 && j == n2) {
            return true;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }
        // Recursion Calls
        boolean first = false;
        boolean second = false;
        if (i < n1 && s1.charAt(i) == s3.charAt(k)) {
            first = solveOptimizedMemoization(i + 1, j, memo);
        }
        if (j < n2 && s2.charAt(j) == s3.charAt(k)) {
            second = solveOptimizedMemoization(i, j + 1, memo);
        }
        memo[i][j] = first || second ? 1 : 0;
        return first || second;
    }
    
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N1 x N2 x N3)
     * SC: O(N1 x N2 x N3) + O(N1 x N2 x N3)
     * 
     * - O(N1 x N2 x N3) - memoization memory
     * - O(N1 x N2 x N3) - recursion stack
     * 
     * Memory Limit Exceeded (1010 /1111 testcases passed)
     */
    public boolean isInterleaveMemoization(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.n1 = s1.length();
        this.n2 = s2.length();
        this.n3 = s3.length();
        if (n1 + n2 != n3) {
            return false;
        }
        int[][][] memo = new int[n1 + 1][n2 + 1][n3 + 1]; // SC: O(N1 x N2 x N3)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, 0, 0, memo); // TC: O(N1 x N2 x N3), SC: O(N1 x N2 x N3)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N1 x N2 x N3)
     * SC: O(N1 x N2 x N3)
     */
    private boolean solveMemoization(int i, int j, int k, int[][][] memo) {
        // Base Case
        if (i == n1 && j == n2 && k == n3) {
            return true;
        }
        // Memoization Check
        if (memo[i][j][k] != -1) {
            return memo[i][j][k] == 1;
        }
        // Recursion Calls
        boolean first = false;
        boolean second = false;
        if (i < n1 && s1.charAt(i) == s3.charAt(k)) {
            first = solveMemoization(i + 1, j, k + 1, memo);
        }
        if (j < n2 && s2.charAt(j) == s3.charAt(k)) {
            second = solveMemoization(i, j + 1, k + 1, memo);
        }
        memo[i][j][k] = first || second ? 1 : 0;
        return first || second;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ (N1 + N2))
     * SC: O(N1 + N2)
     * 
     * - O(N1 + N2) - recursion stack
     * 
     * Time Limit Exceeded (1010 /1111 testcases passed)
     */
    public boolean isInterleaveRecursion(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.n1 = s1.length();
        this.n2 = s2.length();
        this.n3 = s3.length();
        if (n1 + n2 != n3) {
            return false;
        }
        return solveRecursion(0, 0, 0); // TC: O(2 ^ (N1 + N2)), SC: O(N1 + N2)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ (N1 + N2))
     * SC: O(N1 + N2)
     */
    private boolean solveRecursion(int i, int j, int k) {
        // Base Case
        if (i == n1 && j == n2 && k == n3) {
            return true;
        }
        // Recursion Calls
        boolean first = false;
        boolean second = false;
        if (i < n1 && s1.charAt(i) == s3.charAt(k)) {
            first = solveRecursion(i + 1, j, k + 1);
        }
        if (j < n2 && s2.charAt(j) == s3.charAt(k)) {
            second = solveRecursion(i, j + 1, k + 1);
        }
        return first || second;
    }
}
