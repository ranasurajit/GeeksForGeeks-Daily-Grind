class Solution {
    /**
     * Approach : Using Prime Factorization Approach
     * 
     * TC : O(√(y/x))
     * SC : O(1)
     */
    public int pairCount(int x, int y) {
        // ensuring x > y
        if (y % x != 0) {
            // x and y are not divisible by each other
            return 0;
        }
        int distinctPrimeFactors = 0;
        /**
         * we need to compute distinct ordered pair 
         * of prime factors of x / y 
         */
        int n = y / x;
        for (int i = 2; i * i <= n; i++) { // TC : O(√(x/y))
            if (n % i == 0) {
                distinctPrimeFactors++;
                // remove all occurences of this prime
                while (n % i == 0) {
                    n = n / i;
                }
            }
        }
        if (n > 1) {
            // remaining value in n is also a distinct prime factor
            distinctPrimeFactors++;
        }
        return 1 << distinctPrimeFactors;
    }
}
