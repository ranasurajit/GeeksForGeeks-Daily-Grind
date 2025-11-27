class Solution {
    /**
     * Approach : Using Bit-Manipulation + Math Approach
     * 
     * For each element, we either include it or exclude it.
     * For a fixed bit position, when looking across all subsets, 
     * that bit will be included (via XOR) exactly in half of the subsets.
     * 
     * This happens due to symmetry of subset construction.
     * Thus: Total XOR Sum = (bitwise OR of all elements) × 2 ^ (N - 1)
     * 
     * TC: O(N)
     * SC: O(1)
     */
    int subsetXORSum(int arr[]) {
        int n = arr.length;
        int bits = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            bits |= arr[i];
        }
        return bits * (int) Math.pow(2, n - 1);
    }
}
