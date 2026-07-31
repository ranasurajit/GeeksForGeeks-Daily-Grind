class Solution {
    private static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
    private static final int MOD = (int) 1e9 + 7;
    private int n;
    
    /**
     * Approach III : Using Tabulation + BitMasking Approach
     * 
     * TC : O(n)
     * SC : O(1048 x 2) ~ O(1)
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int countSubsets(int[] arr) {
        int max = (1 << 10);
        int[] dp = new int[max]; // SC : O(1048)
        dp[0] = 1;
        int ones = 0;
        for (int num : arr) { // TC : O(n)
            if (num == 1) {
                ones++;
                continue;
            }
            int numMask = getMask(num);
            if (numMask == -1) {
                // invalid number
                continue;
            }
            int[] next = dp.clone(); // SC : O(1048)
            for (int mask = 0; mask < max; mask++) { // TC : O(1048)
                /**
                 * Can include current number only if
                 * none of its prime factors are already used.
                 */
                if ((mask & numMask) == 0) {
                    int newMask = (mask | numMask);
                    next[newMask] = (next[newMask] + dp[mask]) % MOD;
                }
            }
            dp = next;
        }
        long result = 0L;
        /**
         * Ignore mask = 0 because it represents
         * the empty subset.
         */
        for (int mask = 1; mask < max; mask++) { // TC : O(1048)
            result = (result + dp[mask]) % MOD;
        }
        /**
         * Every 1 can either be picked or skipped.
         */
        result = (result * fastPower(2, ones)) % MOD; // TC : O(log(ones))
        return (int) (result % MOD);
    }

    /**
     * Fast Modular Exponentiation
     *
     * Computes (base^exp) % MOD
     * 
     * TC : O(log(exp))
     * SC : O(1)
     */
    private long fastPower(long base, int exp) {
        long ans = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                ans = (ans * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return ans;
    }
    
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC : O(n)
     * SC : O(n) + O(n)
     * - O(n) - memoization memory
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (1012 / 1111 testcases passed)
     */
    public int countSubsetsMemoization(int[] arr) {
        this.n = arr.length;
        /**
         * we need to efficiently mark which prime numbers have been 
         * chosen in the product so we can keep a bit mask of 10 digits
         * i.e. 0000000000 as initial bitmask
         */
        Integer[][] memo = new Integer[n][1 << 10]; // SC : O(n x 1024) ~ O(n)
        return solveMemoization(0, 0, arr, memo) ;  // TC : O(n), SC : O(n)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(n x 2048) ~ O(n)
     * SC : O(n)
     */
    private int solveMemoization(int idx, int bitmask, int[] arr,
        Integer[][] memo) {
        // Base Case
        if (idx == n) {
            return bitmask == 0 ? 0 : 1;
        }
        // Memoization Check
        if (memo[idx][bitmask] != null) {
            return memo[idx][bitmask];
        }
        // Recursion Calls - skip or pick approach
        // skip
        int skip = solveMemoization(idx + 1,
            bitmask, arr, memo) % MOD;
        // we are free to choose arr[idx] if it is a prime
        int pick = 0;
        int numMask = getMask(arr[idx]);
        if (numMask != -1 && (numMask & bitmask) == 0) {
            // we can pick the number at arr[idx]
            pick = solveMemoization(idx + 1, 
                (numMask | bitmask), arr, memo) % MOD;
        }
        return memo[idx][bitmask] = (skip + pick) % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     * 
     * Time Limit Exceeded (12 / 1111 testcases passed)
     */
    public int countSubsetsRecursion(int[] arr) {
        this.n = arr.length;
        /**
         * we need to efficiently mark which prime numbers have been 
         * chosen in the product so we can keep a bit mask of 10 digits
         * i.e. 0000000000 as initial bitmask
         */
        return solveRecursion(0, 0, arr) % MOD;
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int idx, int bitmask, int[] arr) {
        // Base Case
        if (idx == n) {
            return bitmask == 0 ? 0 : 1;
        }
        // Recursion Calls - skip or pick approach
        // skip
        int skip = solveRecursion(idx + 1, bitmask, arr) % MOD;
        // we are free to choose arr[idx] if it is a prime
        int pick = 0;
        int numMask = getMask(arr[idx]);
        if (numMask != -1 && (numMask & bitmask) == 0) {
            // we can pick the number at arr[idx]
            pick = solveRecursion(idx + 1, (numMask | bitmask), arr) % MOD;
        }
        return (skip + pick) % MOD;
    }
    
    /**
     * Using BitMasking Approach
     * 
     * returns -1 for invalid numbers else
     * returns prime-factor bitmask for num
     * 
     * TC : O(10) ~ O(1)
     * SC : O(1)
     */
    private int getMask(int num) {
        if (num == 1) {
            return 0;
        }
        int bitmask = 0;
        for (int i = 0; i < primes.length; i++) {
            int prime = primes[i];
            if (num % prime == 0) {
                /**
                 * number is divisible by prime so, we 
                 * need to double check if the same number 
                 * is again divisible by prime
                 */
                num = num / prime;
                if (num % prime == 0) {
                    return -1;
                }
                bitmask = (bitmask | (1 << i));
            }
        }
        return bitmask;
    }
}
