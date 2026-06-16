class Solution {
    /**
     * Approach : Using Simulation Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    public int maxProduct(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int result = 1;
        int cuts = 3;
        while (n > 4) { // TC : O(n)
            n -= cuts;
            result *= cuts;
        }
        return result * n;
    }
}
