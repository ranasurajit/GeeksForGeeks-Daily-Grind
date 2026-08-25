class Solution {
    /**
     * Approach : Using Array Simulation Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public int minMoves(int[] arr) {
        int n = arr.length;
        int[] pos = new int[n + 1]; // SC : O(n)
        /**
         * we need to find the maximum running length
         * of increasing sequence indices (maxLIS) 
         * in array 'arr'
         * 
         * so, we need to make (n - maxLIS) operations
         * to make the array 'arr' sorted
         */
        for (int i = 0; i < n; i++) {     // TC : O(n)
            pos[arr[i]] = i;
        }
        int longestRun = 1;
        int maxLIS = 1;
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (pos[i] < pos[i + 1]) {
                longestRun++;
            } else {
                // reset
                longestRun = 1;
            }
            maxLIS = Math.max(longestRun, maxLIS);
        }
        return n - maxLIS;
    }
}
