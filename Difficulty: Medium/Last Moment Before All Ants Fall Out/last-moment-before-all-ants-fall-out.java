class Solution {
    /**
     * Apprach : Using Greedy Approach
     * 
     * TC: O(M + N)
     * SC: O(1)
     */
    public int getLastMoment(int n, int left[], int right[]) {
        /**
         * we should not consider any ant which is at left at 
         * index 0 and right at index = n
         * 
         * the last ant will fell down at time which is max time 
         * any ant will take to fall at any end
         * 
         * 
         * Collision has no effect on timings so it is as good
         * as ants continuing the journey to reach either side
         * of the wooden plank, so we need to find the maximum
         * units travelled by any ant (at speed of 1 unit / sec)
         * so, the maximum unit = time of the last ant to fall 
         * out the plank
         */
        int maxLeft = 0;
        for (int x : left) { // TC: O(M)
            if (x != 0) {
                maxLeft = Math.max(maxLeft, x);
            }
        }
        int maxRight = 0;
        for (int x : right) { // TC: O(N)
            if (x != n) {
                maxRight = Math.max(maxRight, n - x);
            }
        }
        return Math.max(maxLeft, maxRight);
    }
}
