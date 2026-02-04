class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int maxOnes(int arr[], int k) {
        int n = arr.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int countZeros = 0;
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            if (arr[j] == 0) {
                countZeros++;
            }
            while (i < n && countZeros > k) {
                // shrink the window size by removing computation from index 'i'
                if (arr[i] == 0) {
                    countZeros--;
                }
                i++;
            }
            // here countZeros <= k
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }
}
