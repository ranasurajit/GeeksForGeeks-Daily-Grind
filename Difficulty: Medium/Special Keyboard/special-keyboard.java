class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC : O(n²)
     * SC : O(n) + O(n)
     * - O(n) - recursion stack
     * - O(n) - memoization memory
     */
    public int optimalKeys(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solveMemoization(n, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n²)
     * SC : O(n)
     */
    private int solveMemoization(int n, int[] memo) {
        // Base Case
        if (n <= 1) {
            return n;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        // option 1 : write 'A' on screen
        int max = 1 + solveMemoization(n - 1, memo);
        for (int j = 1; j <= n - 3; j++) { // TC : O(n - 3)
            /**
             * we offset (n - 3) sp that we can use 
             * select-copy-paste operations
             * number of pastes for a selected j
             * index = we first solve for j and then
             * use select-copy-paste to multiply
             * the characters to (n - j - 1) times
             */
            max = Math.max(max, solveMemoization(j, memo) * (n - j - 1));
        }
        return memo[n] = max;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(Exponential)
     * SC : O(n)
     * - O(n) - recursion stack
     */
    public int optimalKeysRecursion(int n) {
        return solveRecursion(n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(Exponential)
     * SC : O(n)
     */
    private int solveRecursion(int n) {
        // Base Case
        if (n <= 1) {
            return n;
        }
        // Recursion Calls
        // option 1 : write 'A' on screen
        int max = 1 + solveRecursion(n - 1);
        for (int j = 1; j <= n - 3; j++) { // TC : O(n - 3)
            /**
             * we offset (n - 3) sp that we can use 
             * select-copy-paste operations
             * number of pastes for a selected j
             * index = we first solve for j and then
             * use select-copy-paste to multiply
             * the characters to (n - j - 1) times
             */
            max = Math.max(max, solveRecursion(j) * (n - j - 1));
        }
        return max;
    }
}
