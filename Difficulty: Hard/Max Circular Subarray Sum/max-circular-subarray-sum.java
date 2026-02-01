class Solution {
    /**
     * Approach II : Using Optimal (Kadane's Algorithm) Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(1)
     * 
     * Accepted (1113 / 1113 testcases passed)
     */
    public int maxCircularSum(int arr[]) {
        int n = arr.length;
        /**
         * there can be two cases:
         * 1. maximum sub-array sum resides in linear array 'arr'
         * 2. maximum sub-array sum resides in circular array 'arr' 
         *    (wrapped), so we need to find the minimum in linear
         *    array 'arr' so maxWrapSum = totalSum - minSubArraySum
         */
        int totalSum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            totalSum += arr[i];
        }
        int maxSumLinear = getMaxSumInLinearArray(arr, n); // TC: O(N)
        if (maxSumLinear < 0) {
            /**
             * this means all elements < 0 so minimum subarray sum = total sum
             * then no need further to compute wrapped sum as minimum sub-array
             */
            return maxSumLinear;
        }
        int minSubWrapped = getMinSumInLinearArray(arr, n); // TC: O(N)
        return Math.max(maxSumLinear, totalSum - minSubWrapped);
    }

    /**
     * Using Kadane's Algorithm Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private int getMaxSumInLinearArray(int[] arr, int n) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            currentSum += arr[i];
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }
    
    /**
     * Using Modified Kadane's Algorithm Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private int getMinSumInLinearArray(int[] arr, int n) {
        int minSum = Integer.MAX_VALUE;
        int currentSum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            currentSum += arr[i];
            minSum = Math.min(minSum, currentSum);
            if (currentSum > 0) {
                currentSum = 0;
            }
        }
        return minSum;
    }

    /**
     * Approach I : Using Brute-Force (Kadane's Algorithm) Approach
     * 
     * TC: O(N²)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1011 / 1113 testcases passed)
     */
    public int maxCircularSumBruteForce(int arr[]) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            maxSum = Math.max(maxSum, getMaxInSubArray(arr, i, n)); // TC: O(N)
        }
        return maxSum;
    }
    
    /**
     * Using Kadane's Algorithm Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private int getMaxInSubArray(int[] arr, int start, int n) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = start; i < start + n; i++) { // TC: O(N)
            currentSum += arr[i % n];
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }
}
