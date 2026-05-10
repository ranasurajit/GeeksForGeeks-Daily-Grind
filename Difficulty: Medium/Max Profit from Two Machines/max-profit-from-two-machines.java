class Solution {
    /**
     * Approach II : Using Greedy + Sorting Approach
     * 
     * TC : O(n) + O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(n)
     */
    public int maxProfit(int x, int y, int[] a, int[] b) {
        int n = a.length;
        /**
         * We will try to greedily assign the tasks based
         * upon the maximum profit based upon the difference
         * between a[i] - b[i]
         */
        Integer[] indices = new Integer[n]; // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            indices[i] = i;
        }
        Arrays.sort(indices, (i, j) -> {
            return Math.abs(a[j] - b[j]) - Math.abs(a[i] - b[i]);
        });                           // TC : O(n x log(n))
        int maxProfit = 0;
        for (int idx : indices) {     // TC : O(n)
            if (a[idx] > b[idx]) {
                if (x > 0) {
                    maxProfit += a[idx];
                    x--;
                } else if (y > 0) {
                    maxProfit += b[idx];
                    y--;
                }
            } else {
                if (y > 0) {
                    maxProfit += b[idx];
                    y--;
                } else if (x > 0) {
                    maxProfit += a[idx];
                    x--;
                }
            }
        }
        return maxProfit;
    }

    /**
     * Approach I : Using Greedy + Sorting Approach
     * 
     * TC : O(n) + O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(3 x n) ~ O(n)
     */
    public int maxProfitGreedy(int x, int y, int[] a, int[] b) {
        int n = a.length;
        /**
         * We will try to greedily assign the tasks based
         * upon the maximum profit based upon the difference
         * between a[i] - b[i]
         */
        int[][] diff = new int[n][3]; // SC : O(3 x n) ~ O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            int d = a[i] - b[i];
            int sign = d > 0 ? 1 : -1;
            diff[i] = new int[] { Math.abs(d), sign, i };
        }
        Arrays.sort(diff, (p, q) -> q[0] - p[0]); // TC : O(n x log(n))
        int maxProfit = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int[] item = diff[i];
            int sign = item[1];
            int idx = item[2];
            if (sign > 0) {
                // take the profit from array 'a'
                if (x > 0) {
                    maxProfit += a[idx];
                    x--;
                } else if (y > 0) {
                    maxProfit += b[idx];
                    y--;
                }
            } else if (sign < 0 ) {
                // take the profit from array 'b'
                if (y > 0) {
                    maxProfit += b[idx];
                    y--;
                } else if (x > 0) {
                    maxProfit += a[idx];
                    x--;
                }
            } else {
                // take the profit from either array 'a' or 'b'
                if (x > 0) {
                    maxProfit += a[idx];
                    x--;
                } else if (y > 0) {
                    maxProfit += b[idx];
                    y--;
                }
            }
        }
        return maxProfit;
    }
}
