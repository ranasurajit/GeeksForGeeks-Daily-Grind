class Solution {
    /**
     * Approach : Using Array Prefix Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    public int cntWays(int[] arr) {
        int n = arr.length;
        int count = 0;
        long totalEven = 0L;
        long totalOdd = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if ((i & 1) == 0) {
                // even indices
                totalEven += (long) arr[i];
            } else {
                // odd indices
                totalOdd += (long) arr[i];
            }
        }
        /**
         * for every deletion of i, there will be a shift of elements to the left
         * so the parity changes from even to odd and vice-versa after index i
         * 
         * so we need to count index i such that, 
         * sumLeftEven + sumRightOdd == sumRightEven + sumLeftOdd
         */
        long sumLeftEven = 0L;
        long sumLeftOdd = 0L;
        long sumRightEven = 0L;
        long sumRightOdd = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i > 0) {
                if (((i - 1) & 1) == 0) {
                    // even indices
                    sumLeftEven += (long) arr[i - 1];
                } else {
                    // odd indices
                    sumLeftOdd += (long) arr[i - 1];
                }
            }
            sumRightEven = totalEven - sumLeftEven - ((i & 1) == 0 ? arr[i] : 0);
            sumRightOdd  = totalOdd  - sumLeftOdd  - ((i & 1) != 0 ? arr[i] : 0);
            if (sumLeftEven + sumRightOdd == sumRightEven + sumLeftOdd) {
                count++;
            }
        }
        return count;
    }
}
