class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(M) + O(M x log(K x Min(ranks))) ~ O(M x log(N)) 
     *  as 1 ≤ rank[i] ≤ 100, Min(rank) can be maximum 100 
     * 
     * SC: O(1)
     * 
     * where K = (n * (n + 1)) / 2 ~ n ^ 2
     */
    public int minTime(int[] ranks, int n) {
        int m = ranks.length;
        int minRank = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) { // TC: O(M)
            minRank = Math.min(minRank, ranks[i]);
        }
        // minimum time needed to make n donuts
        int low = 0;
        // maximum time needed to make n donuts - thinking the fastest chef makes all donuts
        int high = ((n * (n + 1)) / 2) * minRank;
        int minTime = high;
        while (low <= high) { // TC: O(log(K x Min(ranks)))
            int mid = low + (high - low) / 2;
            boolean isPossible = canDonutsBeMadeInTime(ranks, m, n, mid); // TC: O(M)
            if (isPossible) {
                // we need to minimize the time
                minTime = Math.min(minTime, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return minTime;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(M)
     * SC: O(1)
     */
    private boolean canDonutsBeMadeInTime(int[] ranks, int m, int n, int time) {
        int countDonuts = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            int timeAllowed = time;
            int increment = 1;
            while (timeAllowed > 0) {
                timeAllowed -= ranks[i] * increment;
                if (timeAllowed >= 0) {
                    countDonuts += 1;
                } else {
                    // early return
                    break;
                }
                increment++;
            }
        }
        return countDonuts >= n;
    }
}
