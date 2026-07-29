class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach II : Using Mathematical Approach
     * 
     * TC : O(r)
     * SC : O(1)
     * 
     * Returns wrong answer due to overflow
     */
    public int nCr(int n, int r) {
        if (r > n) {
            return 0;
        }
        /**
         * By Math formula, nC(r + 1) = (n!) / ((r + 1)! x (n - r - 1)!)
         * = (n!) / ((r + 1) x (r!) x (n - r - 1)!)
         * 
         * so multiply (n - r) to both numerator and denominator
         * 
         * = ((n!) x (n - r)) / ((r + 1) x (r!) x (n - r - 1)! x (n - r))
         * = ((n - r) / (r + 1)) x (n!) / (r! x (n - r)!)
         * = ((n - r) / (r + 1)) x nCr)
         * i.e
         * 
         * nCr = ((n - r + 1) x nC(r - 1)) / r
         * 
         * so, n is constant and we can compute from r to [0, r - 1] 
         * 
         * nC0 = 1 and nCn = 1
         */
        long result = 1L;
        for (int p = 1; p <= r; p++) { // TC : O(r)
            result = result * (n - p + 1);
            result = result / p;
        }
        return (int) (result % MOD);
    }

    /**
     * Approach I : Using Brute-Force (Mathematical) Approach
     * 
     * TC : O(n)
     * SC : O(n)
     * 
     * Returns wrong answer due to overflow
     */
    public int nCrBruteForce(int n, int r) {
        if (r > n) {
            return 0;
        }
        long[] fact = new long[n + 1];   // SC : O(n)
        fact[0] = 1L;
        for (int i = 1; i <= n; i++) { // TC : O(n)
            fact[i] = ((long) i * fact[i - 1]) % MOD;
        }
        long result = (fact[n] / (fact[r] * fact[n - r]));
        return (int) result;
    }
}
