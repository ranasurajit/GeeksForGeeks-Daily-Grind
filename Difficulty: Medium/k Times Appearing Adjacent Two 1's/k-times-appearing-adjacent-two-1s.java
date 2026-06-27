class Solution {
    private int n;
    private int k;
    private static final int MOD = (int) 1e9 + 7;
    
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC : O(n x k)
     * SC : O(n x k) + O(n)
     *    - O(n x k) - memoization memory
     *    - O(n) - recursion stack
     * 
     * Accepted (513 / 513 testcases passed)
     */
    public int countStrings(int n, int k) {
        this.n = n;
        this.k = k;
        long[][][] memo = new long[n][k + 1][3]; // SC : O(n x k x 3) ~ O(n x k)
        for (long[][] mem : memo) {
            for (long[] m : mem) {
                Arrays.fill(m, -1L);
            }
        }
        return (int) (solveMemoization(0, 0, -1, memo) % MOD);
    }

    /**
     * Using Memoization Approach
     * 
     * TC : O(n x k x 3) ~ O(n x k)
     * SC : O(n)
     */
    private long solveMemoization(int idx, int count, int prev, long[][][] memo) {
        // Base Case
        if (count > k) {
            return 0L;
        }
        if (idx == n) {
            return count == k ? 1L : 0L;
        }
        // Memoization Check
        if (memo[idx][count][prev + 1] != -1) {
            return memo[idx][count][prev + 1];
        }
        // Recursion Calls
        long pickZero = solveMemoization(idx + 1, count, 0, memo) % MOD;
        int newCount = count;
        if (prev == 1) {
            newCount++;
        }
        long pickOne = solveMemoization(idx + 1, newCount, 1, memo) % MOD;
        return memo[idx][count][prev + 1] = (pickZero + pickOne) % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     *    - O(n) - recursion stack
     * 
     * Time Limit Exceeded (10 / 513 testcases passed)
     */
    public int countStringsRecursion(int n, int k) {
        this.n = n;
        this.k = k;
        return solveRecursion(0, 0, -1);
    }

    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int idx, int count, int prev) {
        // Base Case
        if (count > k) {
            return 0;
        }
        if (idx == n) {
            return count == k ? 1 : 0;
        }
        // Recursion Calls
        int pickZero = solveRecursion(idx + 1, count, 0);
        int newCount = count;
        if (prev == 1) {
            newCount++;
        }
        int pickOne = solveRecursion(idx + 1, newCount, 1);
        return pickZero + pickOne;
    }
}
