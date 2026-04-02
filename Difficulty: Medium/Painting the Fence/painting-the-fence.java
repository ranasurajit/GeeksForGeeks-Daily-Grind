class Solution {
    /**
     * Approach : Using Math + Combinatorics Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    int countWays(int n, int k) {
        if (n == 1) {
            return k;
        }
        /**
         * For 2 fences, for k colours, the number of ways
         * to color them such that last two fence has same
         * or different colors
         */
        int same = k;
        int diff = k * (k - 1);
        /**
         * For i fences, for k colors, the number of ways
         * to color them such that last two fence has same
         * or different colors can be computed as below
         */
        int total = same + diff;
        for (int i = 3; i <= n; i++) { // TC: O(N)
            same = diff;
            diff = total * (k - 1);
            total = same + diff;
        }
        return total;
    }
}
