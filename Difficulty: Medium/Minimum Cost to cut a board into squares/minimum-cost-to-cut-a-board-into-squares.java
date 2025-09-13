class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     * 
     * TC: O(M x log(M) + N x log(N) + (M + N)) ~ O(M x log(M) + N x log(N)
     * SC: O(1)
     */
    public static int minCost(int n, int m, int[] x, int[] y) {
        /**
         * Intuition is to pick the largest costs first and 
         * cut it initially so that overall total cost is 
         * minimal, also the order of cutting costs does not
         * matter so we need to sort both arrays 'x' and 'y'
         */
        Arrays.sort(x); // TC: O(M x log(M))
        Arrays.sort(y); // TC: O(N x log(N))
        int hcuts = 1;
        int vcuts = 1;
        int i = m - 2; // pointer at the end of array 'x'
        int j = n - 2; // pointer at the end of array 'x'
        int totalCost = 0;
        while (i >= 0 && j >= 0) { // TC: O(N + M)
            if (x[i] >= y[j]) {
                // cut it vertically and this impacts number of horizontal pieces
                totalCost += hcuts * x[i];
                vcuts++;
                i--;
            } else {
                // cut it horizontally and this impacts number of vertical pieces
                totalCost += vcuts * y[j];
                hcuts++;
                j--;
            }
        }
        while (i >= 0) {
            // cut it vertically and this impacts number of horizontal pieces
            totalCost += hcuts * x[i];
            vcuts++;
            i--;
        }
        while (j >= 0) {
            // cut it horizontally and this impacts number of vertical pieces
            totalCost += vcuts * y[j];
            hcuts++;
            j--;
        }
        return totalCost;
    }
}
