class Solution {
    /**
     * Approach : Using Kadane's Algorithm Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    int maxSubstring(String s) {
        int n = s.length();
        int count0s = 0;
        int count1s = 0;
        int[] nums = new int[n]; // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s.charAt(i);
            if (ch == '0') {
                count0s++;
                nums[i] = 1; // replace with '+1'
            } else {
                count1s++;
                nums[i] = -1; // replace with '-1'
            }
        }
        if (count1s == n) {
            return -1;
        }
        /**
         * we need to maximize the difference between
         * count of 0s - count of 1s so, we can replace
         * 0s with +1 and 1s with -1 so we need to find
         * maximum sub-array sum so, can then apply
         * Kadane's algorithm to solve this problem
         */
        int maxSum = 0;
        int currentSum = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            currentSum += nums[i];
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }
}
