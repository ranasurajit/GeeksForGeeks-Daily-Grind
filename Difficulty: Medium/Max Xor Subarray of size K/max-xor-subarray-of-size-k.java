class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int maxSubarrayXOR(int[] arr, int k) {
        int n = arr.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int maxXOR = 0;
        int currentXOR = 0;
        while (j < n) { // TC: O(N)
            currentXOR ^= arr[j];
            if (j - i + 1 == k) {
                // sliding window size of k is met here
                maxXOR = Math.max(maxXOR, currentXOR);
                // remove computation from index 'i'
                currentXOR ^= arr[i]; // by property of XOR (a ^ a ^ b = b) so arr[i] is negated
                // slide to next window
                i++;
            }
            j++;
        }
        return maxXOR;
    }
}
