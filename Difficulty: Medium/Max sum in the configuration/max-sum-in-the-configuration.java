class Solution {
    /**
     * Approach : Using Greedy Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    int maxSum(int[] arr) {
        int n = arr.length;
        /**
         * whenever we rotate the array clockwize by k steps
         * we get a formula as: 
         * 
         * R(k) = R(k - 1) + totalSum - n * arr[n - 1]
         * so, we need to try all possible rotations and
         * store and return the maximum sum
         */
        int totalSum = 0;
        int maxRotationSum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            totalSum += arr[i];
            maxRotationSum += i * arr[i];
        }
        int currentRotationSum = maxRotationSum;
        for (int i = n - 1; i >= 1; i--) { // TC: O(N)
            // index i denotes rotation around element at index i of original array
            currentRotationSum = currentRotationSum + totalSum - n * arr[i];
            maxRotationSum = Math.max(maxRotationSum, currentRotationSum);
        }
        return maxRotationSum;
    }
}
