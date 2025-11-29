class Solution {
    /**
     * Approach II : Using Optimal(Bit-Manipulation) Approach
     * 
     * TC: O(32) ~ O(1)
     * SC: O(1)
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public static int countSetBits(int n) {
        /**
         * We need to optimally think about set bits contributed by
         * numbers from [1...n] at any bit position i
         * 
         * Generic Formula: 
         * 
         * cycle = 2 ^ (i + 1), where bit repeats every cycle at ith bit 
         * fullCycles = (n + 1) / cycle
         * Each full cycle contributes 2 ^ i ones for that bit, 
         * so contribution from full cycles = fullCycles * 2 ^ i.
         * 
         * Remainder part: rem = (n + 1) % cycle. From this remainder, 
         * extra ones = max(0, rem - 2 ^ i).
         * 
         * So total ones at bit i: onesi = fullCycles × 2 ^ i + max(0, rem − 2 ^ i)
         */
        long countBits = 0L;
        for (int i = 0; i < 32; i++) { // TC: O(32)
            long half = (1L << i);
            long cycle = (1L << (i + 1));
            if (half > n) {
                break;
            }
            long fullCycles = (n + 1) / cycle;
            long contribFullCycle = fullCycles * half;
            long rem = (n + 1) % cycle;
            countBits += contribFullCycle + Math.max(0, rem - half);
        }
        return (int) countBits;
    }

    /**
     * Approach I : Using Brute-Force(Bit-Manipulation) Approach
     * 
     * TC: O(32 x N) ~ O(N)
     * SC: O(1)
     * 
     * Time Limit Exceeded (10 / 1115 testcases passed)
     */
    public static int countSetBitsBruteForce(int n) {
        int totalSetBits = 0;
        for (int i = 1; i <= n; i++) { // TC: O(N)
            totalSetBits += countOneBits(i); // TC: O(32), SC: O(1)
        }
        return totalSetBits;
    }
    
    /**
     * Using Bit-Manipulation Approach
     * 
     * TC: O(32)
     * SC: O(1)
     */
    private static int countOneBits(int num) {
        int count = 0;
        for (int i = 31; i >= 0; i--) { // TC:O(32)
            if (((num >> i) & 1) != 0) {
                count++;
            }
        }
        return count;
    }
}
