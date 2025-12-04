// User function Template for Java
class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x N x N)
     * SC: O(N x N) + O(N)
     * 
     * Accepted (1110 / 1110 testcases passed)
     */
    static int countWays(String s) {
        int n = s.length();
        int[][][] memo = new int[n][n][2]; // SC: O(N x N x 2) ~ O(N x N)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        // returns number of ways s evaluates to True
        return solveMemoization(s, 0, n - 1, 1, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x N x N)
     * SC: O(N)
     */
    private static int solveMemoization(String s, int i, int j, int isTrue, int[][][] memo) {
        // Base Case
        if (i > j) {
             // invalid String s
             return 0;
        }
        if (i == j) {
            if (isTrue == 1) {
                return s.charAt(i) == 'T' ? 1 : 0;
            } else {
                return s.charAt(i) == 'F' ? 1 : 0;
            }
        }
        // Memoization Check
        if (memo[i][j][isTrue] != -1) {
            return memo[i][j][isTrue];
        }
        // Recursion Calls
        int ways = 0;
        for (int k = i + 1; k <= j - 1; k += 2) { // TC: O(N)
            char ch = s.charAt(k);
            int leftTrue = solveMemoization(s, i, k - 1, 1, memo);
            int leftFalse = solveMemoization(s, i, k - 1, 0, memo);
            int rightTrue = solveMemoization(s, k + 1, j, 1, memo);
            int rightFalse = solveMemoization(s, k + 1, j, 0, memo);
            if (ch == '&') {
                if (isTrue == 1) {
                    ways += leftTrue * rightTrue;
                } else {
                    ways += (leftTrue * rightFalse) +
                        (leftFalse * rightTrue) + (leftFalse * rightFalse);
                }
            } else if (ch == '|') {
                if (isTrue == 1) {
                    ways += (leftTrue * rightTrue) + 
                        (leftTrue * rightFalse) + 
                        (leftFalse * rightTrue);
                } else {
                    ways += (leftFalse * rightFalse);
                }
            } else {
                // ch == '^'
                if (isTrue == 1) {
                    ways += (leftTrue * rightFalse) + (leftFalse * rightTrue);
                } else {
                    ways += (leftFalse * rightFalse) + (leftTrue * rightTrue);
                }
            }
        }
        return memo[i][j][isTrue] = ways;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: Exponential
     * SC: O(N)
     * 
     * Time Limit Exceeded (1010 / 1110 testcases passed)
     */
    static int countWaysRecursion(String s) {
        int n = s.length();
        return solveRecursion(s, 0, n - 1, 1); // returns number of ways s evaluates to True
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: Exponential
     * SC: O(N)
     */
    private static int solveRecursion(String s, int i, int j, int isTrue) {
        // Base Case
        if (i > j) {
             // invalid String s
             return 0;
        }
        if (i == j) {
            if (isTrue == 1) {
                return s.charAt(i) == 'T' ? 1 : 0;
            } else {
                return s.charAt(i) == 'F' ? 1 : 0;
            }
        }
        // Recursion Calls
        int ways = 0;
        for (int k = i + 1; k <= j - 1; k += 2) {
            char ch = s.charAt(k);
            int leftTrue = solveRecursion(s, i, k - 1, 1);
            int leftFalse = solveRecursion(s, i, k - 1, 0);
            int rightTrue = solveRecursion(s, k + 1, j, 1);
            int rightFalse = solveRecursion(s, k + 1, j, 0);
            if (ch == '&') {
                if (isTrue == 1) {
                    ways += leftTrue * rightTrue;
                } else {
                    ways += (leftTrue * rightFalse) +
                        (leftFalse * rightTrue) + (leftFalse * rightFalse);
                }
            } else if (ch == '|') {
                if (isTrue == 1) {
                    ways += (leftTrue * rightTrue) + 
                        (leftTrue * rightFalse) + 
                        (leftFalse * rightTrue);
                } else {
                    ways += (leftFalse * rightFalse);
                }
            } else {
                // ch == '^'
                if (isTrue == 1) {
                    ways += (leftTrue * rightFalse) + (leftFalse * rightTrue);
                } else {
                    ways += (leftFalse * rightFalse) + (leftTrue * rightTrue);
                }
            }
        }
        return ways;
    }
}
