class Solution {
    private int[] val;
    private int[] wt;

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x T)
     * SC: O(N x T) + O(N)
     * - O(N x T) - memoization memory
     * - O(N) - recursion stack
     * 
     * Accepted (1120 / 1120 testcases passed)
     */
    public int knapSack(int val[], int wt[], int capacity) {
        this.val = val;
        this.wt = wt;
        int n = wt.length;
        int[][] memo = new int[n][capacity + 1]; // SC: O(N x T)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n, capacity, memo); // TC: O(N x T), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x T)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int capacity, int[][] memo) {
        // Base Case
        if (idx == n || capacity == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][capacity] != -1) {
            return memo[idx][capacity];
        }
        // Recursion Calls
        // we can choose to pick or skip
        int skip = solveMemoization(idx + 1, n, capacity, memo);
        int pick = 0;
        if (wt[idx] <= capacity) {
            // we may pick it and continue picking same 'idx' as duplicate items are allowed
            pick = val[idx] + solveMemoization(idx, n, capacity - wt[idx], memo);
        }
        return memo[idx][capacity] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1120 testcases passed)
     */
    public int knapSackRecursion(int val[], int wt[], int capacity) {
        this.val = val;
        this.wt = wt;
        int n = wt.length;
        return solveRecursion(0, n, capacity); // TC: O(2 ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int capacity) {
        // Base Case
        if (idx == n || capacity == 0) {
            return 0;
        }
        // Recursion Calls
        // we can choose to pick or skip
        int skip = solveRecursion(idx + 1, n, capacity);
        int pick = 0;
        if (wt[idx] <= capacity) {
            // we may pick it and continue picking same 'idx' as duplicate items are allowed
            pick = val[idx] + solveRecursion(idx, n, capacity - wt[idx]);
        }
        return Math.max(pick, skip);
    }
}
