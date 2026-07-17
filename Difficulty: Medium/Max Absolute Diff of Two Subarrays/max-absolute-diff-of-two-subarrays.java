class Solution {
    /**
     * Approach : Using Kadane's Algorithm Approach
     * 
     * TC : O(5 x n) ~ O(n)
     * SC : O(4 x n) ~ O(n)
     */
    public int maxDiffSubArrays(int[] arr) {
        int n = arr.length;
        long[] leftMax = new long[n]; // SC : O(n)
        long bestMax = arr[0];
        long currentSum = arr[0];
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            bestMax = Math.max(bestMax, currentSum);
            leftMax[i] = bestMax;
        }
        long[] rightMax = new long[n]; // SC : O(n)
        bestMax = arr[n - 1];
        currentSum = arr[n - 1];
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            bestMax = Math.max(bestMax, currentSum);
            rightMax[i] = bestMax;
        }
        long[] leftMin = new long[n]; // SC : O(n)
        long bestMin = arr[0];
        currentSum = arr[0];
        leftMin[0] = arr[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            currentSum = Math.min(arr[i], currentSum + arr[i]);
            bestMin = Math.min(bestMin, currentSum);
            leftMin[i] = bestMin;
        }
        long[] rightMin = new long[n]; // SC : O(n)
        bestMin = arr[n - 1];
        currentSum = arr[n - 1];
        rightMin[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            currentSum = Math.min(arr[i], currentSum + arr[i]);
            bestMin = Math.min(bestMin, currentSum);
            rightMin[i] = bestMin;
        }
        long result = Long.MIN_VALUE;
        /**
         * we will now compare subarrays ending at i as
         * Max(leftMax[i] - rightMin[i + 1], rightMax[i + 1] - leftMin[i])
         */
        for (int i = 0; i < n - 1; i++) { // TC : O(n)
            result = Math.max(result, leftMax[i] - rightMin[i + 1]);
            result = Math.max(result, rightMax[i + 1] - leftMin[i]);
        }
        return (int) result;
    }
}
