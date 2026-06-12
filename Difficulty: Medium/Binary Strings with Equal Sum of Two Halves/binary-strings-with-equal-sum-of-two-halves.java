class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private static final int MAX = (int) (2 * 1e5);

    /**
     * Approach II : Using Optimal (Math + Combinatorics) Approach
     * 
     * TC : O(m)
     * SC : O(m) + O(m) + O(log(m)) ~ O(m)
     * 
     * Accepted (165/165 test cases passed)
     */
    public int computeValue(int n) {
        long[] fact = new long[MAX + 1];    // SC : O(m)
        long[] invFact = new long[MAX + 1]; // SC : O(m)
        precompute(fact, invFact); // TC : O(m), SC : O(log(m))
        /**
         * we need to compute Sum (nCk) where k = 0 to n
         * = (2n)! / (n!)^2 = (2n)! x Inverse (n!) x Inverse (n!)
         */
        return (int) (((fact[2 * n] * invFact[n]) % MOD) *
                (invFact[n] % MOD) % MOD);
    }

    /**
     * Using Array Pre-Processing Approach
     * 
     * TC : O(m) + O(m) + O(log(m)) ~ O(m)
     * SC : O(log(m))
     */
    private void precompute(long[] fact, long[] invFact) {
        fact[0] = 1;
        for (int i = 1; i <= MAX; i++) { // TC : O(m)
            fact[i] = (i * fact[i - 1]) % MOD;
        }
        invFact[MAX] =
            fastPower(fact[MAX], MOD - 2); // TC : O(log(m)), SC : O(log(m))
        for (int i = MAX - 1; i >= 0; i--) { // TC : O(m)
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    /**
     * Using Binary Exponentiation Approach
     * 
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private long fastPower(long x, long n) {
        if (n == 0L) {
            return 1L;
        }
        if (n == 1L) {
            return x;
        }
        long half = fastPower(x, n / 2);
        long ans = (half * half) % MOD;
        if ((n & 1) != 0) {
            return (x * ans) % MOD;
        }
        return ans % MOD;
    }

    /**
     * Approach I : Using Brute-Force (Recursion) Approach
     * 
     * TC : O(2²ⁿ) ~ O(4ⁿ)
     * SC : O(n) + O(2ⁿ)
     * 
     * Time Limit Exceeded (10/165 test cases passed)
     */
    public int computeValueBruteForce(int n) {
        StringBuilder sb = new StringBuilder(); // SC : O(2ⁿ)
        return (int) (solve(0, 2 * n, sb) % MOD);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2²ⁿ) ~ O(4ⁿ)
     * SC : O(2 x n) ~ O(n)
     */
    private long solve(int idx, int n, StringBuilder sb) {
        // Base Case
        if (idx == n) {
            int totalSum = 0;
            int halfSum = 0;
            for (int i = 0; i < n; i++) {
                totalSum += (sb.charAt(i) - '0');
                if (i < n / 2) {
                    halfSum += (sb.charAt(i) - '0');
                }
            }
            return halfSum * 2 == totalSum ? 1L : 0L;
        }
        // Recursion Calls
        // pick 0 or 1 to fill index 'idx'
        // pick 0
        int size = sb.length();
        sb.append('0');
        long pickZero = solve(idx + 1, n, sb);
        sb.setLength(size);
        sb.append('1');
        long pickOne = solve(idx + 1, n, sb);
        return pickZero + pickOne;
    }
}
