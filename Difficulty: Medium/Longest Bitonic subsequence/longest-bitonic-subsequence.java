class Solution {
    /**
     * Approach : Using Optimized Tabulation (LIS) Approach
     * 
     * TC: O(N x N) + O(N x N) + O(N) ~ O(N x N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public static int longestBitonicSequence(int n, int[] nums) {
        /**
         * To find the Bitonic subsequence we need to find the
         * longest LIS from left to right and longest LIS from
         * right to left
         * 
         * Then we need to find at any index the maximum value of
         * dpLeft[i] + dpRight[i] - 1
         */
        int[] dpLeft = new int[n]; // SC: O(N)
        Arrays.fill(dpLeft, 1); // max-length at every index can be 1 (element itself)
        for (int idx = 1; idx < n; idx++) { // TC: O(N)
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (nums[prevIdx] < nums[idx] && dpLeft[idx] < 1 + dpLeft[prevIdx]) {
                    dpLeft[idx] = 1 + dpLeft[prevIdx];
                }
            }
        }
        int[] dpRight = new int[n]; // SC: O(N)
        Arrays.fill(dpRight, 1); // max-length at every index can be 1 (element itself)
        for (int idx = n - 2; idx >= 0; idx--) { // TC: O(N)
            for (int prevIdx = n - 1; prevIdx > idx; prevIdx--) { // TC: O(N)
                if (nums[prevIdx] < nums[idx] && dpRight[idx] < 1 + dpRight[prevIdx]) {
                    dpRight[idx] = 1 + dpRight[prevIdx];
                }
            }
        }
        /**
         * Now for every index i we need to do Maximize (dpLeft[i] + dpRight[i] - 1)
         * 1 is substracted as element at index 'i' can be counted once and here we
         * included it twice so we substract 1
         */
        int maxLength = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (dpLeft[i] > 1 && dpRight[i] > 1) {
                maxLength = Math.max(maxLength, dpLeft[i] + dpRight[i] - 1);
            }
        }
        return maxLength;
    }
}
