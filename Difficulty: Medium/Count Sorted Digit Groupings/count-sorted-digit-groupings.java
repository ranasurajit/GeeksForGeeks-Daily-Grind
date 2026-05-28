class Solution {
    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(n x n)
     * SC : O(n) + O(n)
     * - O(n) - recursion stack
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int validGroups(String s) {
        int n = s.length();
        int[][] memo = new int[n][901]; // SC : O(n)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, 0, s, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n x 2ⁿ)
     * SC : O(n)
     */
    private int solveMemoization(int idx, int n,
        int prevSum, String s, int[][] memo) {
        // Base Case
        if (idx == n) {
            // got 1 such valid group
            return 1;
        }
        // Memoization Check
        if (memo[idx][prevSum] != -1) {
            return memo[idx][prevSum];
        }
        // Recursion Calls
        int count = 0;
        int sum = 0;
        for (int i = idx; i < n; i++) {
            sum += s.charAt(i) - '0';
            if (prevSum <= sum) {
                count += solveMemoization(i + 1, n, sum, s, memo);
            }
        }
        return memo[idx][prevSum] = count;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n x 2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (1015 / 1115 testcases passed)
     */
    public int validGroupsRecursion(String s) {
        int n = s.length();
        return solveRecursion(0, n, 0, s);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n x 2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int idx, int n, int prevSum, String s) {
        // Base Case
        if (idx == n) {
            // got 1 such valid group
            return 1;
        }
        // Recursion Calls
        int count = 0;
        int sum = 0;
        for (int i = idx; i < n; i++) {
            sum += s.charAt(i) - '0';
            if (prevSum <= sum) {
                count += solveRecursion(i + 1, n, sum, s);
            }
        }
        return count;
    }
}
