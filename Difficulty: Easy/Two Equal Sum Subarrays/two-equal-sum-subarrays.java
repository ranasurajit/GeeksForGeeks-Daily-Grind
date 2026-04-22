class Solution {
    /**
     * Approach : Using Array Simulation Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public boolean canSplit(int arr[]) {
        long total = 0L;
        for (int x : arr) { // TC : O(n)
            total += (long) x;
        }
        /**
         * if total is odd then it is not possible
         * to form two equal sum sub-arrays
         */
        if ((total & 1) != 0) {
            return false;
        }
        total = total / 2; // this is the needed sub-array sum
        long sum = 0L;
        for (int x : arr) { // TC : O(n)
            sum += (long) x;
            if (sum == total) {
                return true;
            }
        }
        return false;
    }
}
