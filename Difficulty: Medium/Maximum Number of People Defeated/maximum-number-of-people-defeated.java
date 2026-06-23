class Solution {
    /**
     * Approach II : Using Binary Search Approach
     * 
     * TC : O(log(n))
     * SC : O(1)
     */
    int maxPeopleDefeated(int p) {
        /**
         * Σ(n²) = (n * (n + 1) * (2 * n + 1)) / 6
         * so, n³ ~ p so, p ranges [1 ≤ p ≤ 3 x 10⁸]
         * so, maximum value of n ~ 1000
         */
        int low = 1;
        int high = 1000;
        while (low <= high) { // TC : O(log(n))
            int mid = low + (high - low) / 2;
            int sum = ((mid) * (mid + 1) * (2 * mid + 1)) / 6;
            if (sum <= p) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    /**
     * Approach I : Using Linear (Math + Simulation) Approach
     * 
     * TC : O(p^(1/3))
     * SC : O(1)
     */
    int maxPeopleDefeatedLinear(int p) {
        int i = 1;
        while (true) { // TC : O(p^(1/3))
            long square = (long) i * i;
            if (square <= p) {
                p -= square;
            } else {
                break;
            }
            i++;
        }
        return i - 1;
    }
};
