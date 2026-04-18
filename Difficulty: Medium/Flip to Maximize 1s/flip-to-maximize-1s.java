class Solution {
    /**
     * Approach : Using Kadane's Algorithm Approach
     * 
     * TC: O(n) + O(n) + O(n) ~ O(n)
     * SC: O(1)
     */
    int maxOnes(int[] arr) {
        int n = arr.length;
        int count1s = 0;
        for (int i = 0; i < n; i++) { // TC: O(n)
            if (arr[i] == 1) {
                count1s++;
            }
        }
        /**
         * in 1 flip 1s changes to 0s and 0s changes to 1s,
         * so mark arr[i] = -1 if it is 1, else mark arr[i]
         * = +1
         */
        for (int i = 0; i < n; i++) { // TC: O(n)
            if (arr[i] == 1) {
                arr[i] = -1;
            } else {
                arr[i] = 1;
            }
        }
        /**
         * Now the problem can be transformed 
         * into computing maximum sub-array sum
         */
        int maxSum = 0;
        int currentSum = 0;
        for (int i = 0; i < n; i++) { // TC: O(n)
            currentSum += arr[i];
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum + count1s;
    }
};
