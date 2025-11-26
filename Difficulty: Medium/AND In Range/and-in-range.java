class Solution {
    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(log(r - l))
     * SC: O(1)
     * 
     * Intuition: Bitwise AND of numbers keeps a 1 only if 
     * all numbers in the range have 1 at that bit position
     */
    public int andInRange(int l, int r) {
        int shifts = 0;
        while (l != r) { // TC: O(log(r - l))
            l = (l >> 1);
            r = (r >> 1);
            shifts++;
        }
        return (l << shifts);
    }
}
