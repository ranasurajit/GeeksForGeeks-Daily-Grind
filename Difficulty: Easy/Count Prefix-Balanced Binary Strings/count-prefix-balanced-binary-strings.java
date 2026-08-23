class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private static final int MAX = 1000;

    private static final long[] catalan = new long[MAX + 1];

    static {
        catalan[0] = 1;

        for (int n = 1; n <= MAX; n++) {
            catalan[n] =
                catalan[n - 1]
                * (4L * n - 2)
                % MOD
                * modInverse(n + 1)
                % MOD;
        }
    }

    /**
     * Approach III : Using Math + Catalan Numbers Approach
     * 
     * TC : O(n)
     * SC : O(n)
     * 
     * Accepted (1000 / 1000 testcases passed)
     */
    public int prefixStrings(int n) {
        return (int) catalan[n];
    }

    private static long modInverse(long x) {
        return power(x, MOD - 2);
    }

    private static long power(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC : O(n²)
     * SC : O(n) + O(n²)
     * - O(n²) - memoization array
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (620 / 1000 testcases passed)
     */
    public int prefixStringsMemoization(int n) {
        int[][] memo = new int[n + 1][n + 1];
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, 0, n, memo);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n²)
     * SC : O(n)
     */
    private int solveMemoization(int count0s, int count1s, int n, int[][] memo) {
        // Base Case
        if (count0s == n && count1s == n) {
            return 1;
        }
        // Memoization Check
        if (memo[count0s][count1s] != -1) {
            return memo[count0s][count1s];
        }
        // Recursion Calls
        long count = 0L;
        if (count1s < n) {
            // we can choose to pick 1
            count += solveMemoization(count0s, count1s + 1, n, memo);
        }
        if (count0s < count1s) {
            count += solveMemoization(count0s + 1, count1s, n, memo);
        }
        return memo[count0s][count1s] = (int) (count % MOD);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (6 / 1000 testcases passed)
     */
    public int prefixStringsRecursion(int n) {
        return solve(0, 0, n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solve(int count0s, int count1s, int n) {
        // Base Case
        if (count0s == n && count1s == n) {
            return 1;
        }
        // Recursion Calls
        int pick1s = 0;
        int pick0s = 0;
        if (count1s < n) {
            // we can choose to pick 1
            pick1s = solve(count0s, count1s + 1, n) % MOD;
        }
        if (count0s < count1s) {
            pick0s = solve(count0s + 1, count1s, n) % MOD;
        }
        return (pick0s + pick1s) % MOD;
    }
}
