class Solution {
    /**
     * Approach : Using Array Pre-Processing Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public int formPyramid(int[] arr) {
        int n = arr.length;
        /**
         * we need to store from left to right
         * the length of increasing sub-array
         * ending at index 'i'
         */
        int[] left = new int[n];      // SC : O(n)
        left[0] = 1;
        for (int i = 1; i < n; i++) { // TC : O(n)
            left[i] = Math.min(arr[i], left[i - 1] + 1);
        }
        /**
         * we need to store from right to left
         * the length of decreasing sub-array
         * ending at index 'i'
         */
        int[] right = new int[n];     // SC : O(n)
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            right[i] = Math.min(arr[i], right[i + 1] + 1);
        }
        int totalSum = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            totalSum += arr[i];
        }
        int maxPyramidSum = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            // compute peak height at index 'i'
            int peak = Math.min(left[i], right[i]);
            int pyramidSum = peak * peak;
            maxPyramidSum = Math.max(maxPyramidSum, pyramidSum);
        }
        return totalSum - maxPyramidSum;
    }
};
