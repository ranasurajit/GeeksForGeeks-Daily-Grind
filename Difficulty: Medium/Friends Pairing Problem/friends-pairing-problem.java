class Solution {
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n)
     * SC : O(n) + O(n)
     */
    public int countFriendsPairings(int n) {
        /**
         * this problem follows a recursion formula
         */
        int[] memo = new int[n + 1]; // SC : O(n)
        Arrays.fill(memo, -1);
        return solveMemoization(n, memo);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private int solveMemoization(int n, int[] memo) {
        // Base Case
        if (n <= 2) {
            return n;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        return memo[n] = solveMemoization(n - 1, memo) + 
            (n - 1) * solveMemoization(n - 2, memo);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    public int countFriendsPairingsRecursion(int n) {
        /**
         * this problem follows a recursion formula
         */
        return solveRecursion(n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int n) {
        // Base Case
        if (n <= 2) {
            return n;
        }
        // Recursion Calls
        return solveRecursion(n - 1) + (n - 1) * solveRecursion(n - 2);
    }
}
