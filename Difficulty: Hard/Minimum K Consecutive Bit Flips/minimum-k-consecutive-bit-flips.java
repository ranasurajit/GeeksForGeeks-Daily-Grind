class Solution {
    /**
     * Approach I : Using Extra Space Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public int kBitFlips(int[] arr, int k) {
        int n = arr.length;
        int flips = 0;
        boolean[] isFlipped = new boolean[n]; // SC: O(N)
        int flipCountFromPast = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i >= k && isFlipped[i - k]) {
                // decrement extra count
                flipCountFromPast--;
            }
            if (flipCountFromPast % 2 == arr[i]) {
                if (i + k > n) {
                    // not possible to make array 'arr' elements 1
                    return -1;
                }
                flipCountFromPast++;
                flips++;
                isFlipped[i] = true;
            }
        }
        return flips;
    }
}
