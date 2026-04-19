class Solution {
    /**
     * Approach : Using Math Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public boolean isPower(int x, int y) {
        if (y == 1) {
            return true;
        } else if (x == 1) {
            return false;
        }
        while (y != 1) {
            int rem = y % x;
            if (rem != 0) {
                return false;
            }
            y = y / x;
        }
        return true;
    }
}
