class Solution {
    /**
     * Approach II : Using Optimal (Math) Approach
     * 
     * TC : O(√n)
     * SC : O(1)
     */
    public boolean isSumOfConsecutive(int n) {
        /**
         * n = k + (k + 1) + (k + 2) + .... + (k + m - 1)
         * n = m * k + (m * (m - 1) / 2)
         * n - (m * (m - 1) / 2) = m * k
         * 
         * so, remaining n - (m * (m - 1) / 2) % m = 0 
         * to have a valid AP sum
         */
        for (long m = 2; (m * (m + 1) / 2) <= n; m++) { // TC : O(√n)
            long remaining = n - (m * (m - 1) / 2);
            if (remaining % m == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Approach I : Using Brute-Force (Hashing) Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public boolean isSumOfConsecutiveBruteForce(int n) {
        Map<Long, Long> map = new HashMap<>(); // SC : O(n)
        long prefixSum = 0L;
        map.put(0L, -1L);
        for (int i = 1; i <= n; i++) { // TC : O(n)
            prefixSum += (long) i;
            long diff = prefixSum - n;
            if (map.containsKey(diff)) {
                long idx = map.get(diff);
                // validates if consecutive elements >= 2
                if (idx == -1) {
                    idx = 0;
                }
                if (i - idx >= 2) {
                    return true;
                }
            }
            map.put(prefixSum, (long) i);
        }
        return false;
    }
}
