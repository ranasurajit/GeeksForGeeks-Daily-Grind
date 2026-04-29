class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public int minSwaps(int[] arr) {
        int n = arr.length;
        int total1s = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (arr[i] == 1) {
                total1s++;
            }
        }
        if (total1s == 0) {
            // not possible to do any swaps
            return -1;
        }
        /**
         * Intuition is to place all 1s together in the sub-array
         * so we should try to form a window of size = total1s and
         * try to check maximum 1s in that which could help us
         * to minimize swaps from 0s to 1s
         */
        int k = total1s; // window size
        int i = 0; // left pointer of sliding window
        int j = 0; // right pointer of sliding window
        int count1s = 0;
        int max1s = 0;
        while (j < n) { // TC : O(n)
            if (arr[j] == 1) {
                count1s++;
            }
            if (j - i + 1 == k) {
                // we got the sliding window
                max1s = Math.max(max1s, count1s);
                // now remove computation of index 'i'
                if (arr[i] == 1) {
                    count1s--;
                }
                // slide to next window
                i++;
            }
            j++;
        }
        // now minimum swaps needed = minimum zeroes inside sliding window
        return total1s - max1s;
    }
}
