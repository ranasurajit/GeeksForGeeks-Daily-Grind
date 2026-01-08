class Solution {
    /**
     * Approach : Using Sliding Window (Variable Length) Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     */
    public int countSubarrays(int[] arr, int k) {
        int n = arr.length;
        /**
         * count of subarrays with exactly k odds = 
         * count of subarrays with at most k odds -
         * count of subarrays with at most (k - 1) odds
         */
        return countAtmostKOdds(arr, n, k) - countAtmostKOdds(arr, n, k - 1);
    }
    
    /**
     * Using Sliding Window (Variable Length) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private int countAtmostKOdds(int[] arr, int n, int k) {
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int oddCount = 0;
        int countPairs = 0;
        while (j < n) { // TC: O(N)
            if ((arr[j] & 1) != 0) {
                oddCount++;
            }
            while (oddCount > k) {
                // shrink the sliding window - remove computation from index 'i'
                if ((arr[i] & 1) != 0) {
                    // arr[i] is odd
                    oddCount--;
                }
                i++;
            }
            // we have (j - i + 1) counts of valid windows with atmost k odds
            countPairs += (j - i + 1);
            j++;
        }
        return countPairs;
    }
}
