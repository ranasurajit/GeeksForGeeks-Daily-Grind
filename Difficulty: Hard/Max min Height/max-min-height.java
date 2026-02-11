class Solution {
    /**
     * Approach : Using Binary Search on Answers + Difference Array Approach
     * 
     * TC: O(N) + O(N x log(R)) ~ O(N x log(R))
     * SC: O(N)
     * 
     * where R = Max(arr) - Min(arr)
     */
    public int maxMinHeight(int[] arr, int k, int w) {
        int n = arr.length;
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            low = Math.min(low, arr[i]);
            high = Math.max(high, arr[i]);
        }
        high += k; // for (k increments)
        while (low <= high) { // TC: O(log(R))
            int mid = low + (high - low) / 2;
            int daysNeeded = daysNeededToAchieveHeight(arr, n, w, mid); // TC: O(N), SC: O(N)
            if (daysNeeded > k) {
                // we need to shrink mid
                high = mid - 1;
            } else {
                // we need to maximize mid
                low = mid + 1;
            }
        }
        return high;
    }
    
    /**
     * Using Difference Array Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int daysNeededToAchieveHeight(int[] arr, int n, int w, int target) {
        int added = 0;
        int daysNeeded = 0;
        int[] diff = new int[n + 1];  // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            added += diff[i]; // running sum
            int current = arr[i] + added;
            if (current < target) {
                int needed = target - current;
                daysNeeded += needed;
                added += needed;
                diff[i] += needed;
                if (i + w < n) {
                    diff[i + w] -= needed;
                }
            }
        }
        return daysNeeded;
    }
}
