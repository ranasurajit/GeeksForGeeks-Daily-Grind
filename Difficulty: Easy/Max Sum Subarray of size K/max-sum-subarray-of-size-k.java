class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int maxSubarraySum(int[] arr, int k) {
        int n = arr.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int maximumSum = 0;
        int currentSum = 0;
        while (j < n) { // TC: O(N)
            currentSum += arr[j];
            if (j - i + 1 == k) {
                // slidng window size is k
                maximumSum = Math.max(maximumSum, currentSum);
                // remove computation from index 'i'
                currentSum -= arr[i];
                // slide to next window
                i++;
            }
            j++;
        }
        return maximumSum;
    }
}
