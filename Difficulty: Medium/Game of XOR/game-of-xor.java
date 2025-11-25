class Solution {
    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int subarrayXor(int[] arr) {
        int n = arr.length;
        if ((n & 1) == 0) {
            // all elements will appear even times and will cancel each other with XOR operation
            return 0;
        }
        /**
         * Contribution or Frequency of each element at index i
         * freq = (i + 1) x (n - i) times so we need to check 
         * if freq is even then results into zero else one 1 
         * occurence of element is left 
         */
        int result = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            long freq = ((long) (i + 1)) * (n - (long) i);
            if ((freq & 1) != 0) {
                // if frequency is even then it contributes zero
                result ^= arr[i];
            }
        }
        return result;
    }
}
