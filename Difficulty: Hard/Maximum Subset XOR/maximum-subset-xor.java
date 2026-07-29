class Solution {
    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC : O(32 x n)
     * SC : O(1)
     */
    public int maxSubsetXOR(int[] arr) {
        int n = arr.length;
        int index = 0;
        // Build XOR Basis
        for (int bit = 31; bit >= 0; bit--) {
            int maxIndex = -1;
            // Find pivot having current bit set
            for (int i = index; i < n; i++) {
                if (((arr[i] >> bit) & 1) == 1) {
                    maxIndex = i;
                    break;
                }
            }
            if (maxIndex == -1) {
                continue;
            }
            // Move pivot to current position
            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
            // Eliminate current bit from all other numbers
            for (int i = 0; i < n; i++) {
                if (i != index && ((arr[i] >> bit) & 1) == 1) {
                    arr[i] ^= arr[index];
                }
            }
            index++;
        }
        // Construct maximum XOR
        int ans = 0;
        for (int x : arr) {
            ans = Math.max(ans, ans ^ x);
        }
        return ans;
    }
}
