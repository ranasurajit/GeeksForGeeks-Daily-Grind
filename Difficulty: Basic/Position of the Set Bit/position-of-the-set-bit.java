class Solution {
    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC : O(32) ~ O(1)
     * SC : O(1)
     */
    public int findPosition(int n) {
        if ((n & (n - 1)) != 0) {
            // number with power of 2 only has 1 set bit
            return -1;
        }
        for (int i = 0; i < 32; i++) { // TC : O(32)
            if (((n >> i) & 1) == 1) {
                return (i + 1);
            }
        }
        return -1;
    }
}
