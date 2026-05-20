class Solution {
    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC : O(log10(n))
     * SC : O(1)
     */
    public boolean isBitSet(int n) {
        if (n == 0) {
            return false;
        }
        while (n > 0) { // TC : O(log10(n))
            if ((n & 1) == 0) {
                return false;
            }
            n = n >> 1;
        }
        return true;
    }
};
