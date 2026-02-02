class Solution {
    /**
     * Approach : Using Greedy + Array Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }
}
