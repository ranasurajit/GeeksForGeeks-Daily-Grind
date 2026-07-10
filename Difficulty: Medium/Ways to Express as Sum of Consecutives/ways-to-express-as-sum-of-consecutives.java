class Solution {
    /**
     * Approach : Using Math Approach
     * 
     * TC : O(√n)
     * SC : O(1)
     */
    public int getCount(int n) {
        /**
         * As per AP sum formula, difference = 1, so if k consecutive
         * elements are considered then,
         * 
         * n = k / 2 (2 x a + (k - 1))
         * 
         * a = ((2 x n) / k - k + 1) / 2
         * 
         * also (k * (k + 1)) / 2 <= n
         */
        int ways = 0;
        for (int k = 2; (k * (k + 1)) / 2 <= n; k++) { // TC : O(√n)
            /**
             * by sum formula let's try to find if
             * a is an integer value
             */
            if (2 * n % k == 0) {
                int compute = ((2 * n) / k) - k + 1;
                if (compute % 2 == 0) {
                    ways++;
                }
            }
        }
        return ways;
    }
};
