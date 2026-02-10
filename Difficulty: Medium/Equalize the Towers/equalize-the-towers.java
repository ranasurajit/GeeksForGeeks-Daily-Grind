class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N) + O(2 x N x log(K)) ~ O(N x log(K))
     * SC: O(1)
     * 
     * where K = Max(heights) - Min(heights)
     */
    public int minCost(int[] heights, int[] cost) {
        int n = heights.length;
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            low = Math.min(low, heights[i]);
            high = Math.max(high, heights[i]);
        }
        while (low <= high) { // TC: O(log(K))
            int mid = low + (high - low) / 2;
            long costMid = costToModifyHeight(heights, cost, n, mid);      // TC: O(N)
            long costNext = costToModifyHeight(heights, cost, n, mid + 1); // TC: O(N)
            if (costMid <= costNext) {
                // on increasing target cost is increasing, so decrease mid
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // so we should modify all heights by 'low'
        return (int) costToModifyHeight(heights, cost, n, low);
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private long costToModifyHeight(int[] heights, int[] cost, int n, int target) {
        long totalCost = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            totalCost += cost[i] * Math.abs(target - heights[i]);
        }
        return totalCost;
    }
}
