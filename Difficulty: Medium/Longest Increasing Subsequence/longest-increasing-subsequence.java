class Solution {
    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     */
    static int lis(int arr[]) {
        int n = arr.length;
        List<Integer> sorted = new ArrayList<Integer>(); // SC: O(N)
        sorted.add(arr[0]);
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (arr[i] > sorted.get(sorted.size() - 1)) {
                sorted.add(arr[i]);
            } else {
                /**
                 * here we need to find index where arr[i] can be 
                 * set using Lower Bound (Binary Search)
                 */
                int idx = lowerBound(sorted, arr[i]); // TC: O(log(N))
                sorted.set(idx, arr[i]);
            }
        }
        return sorted.size();
    }
    
    /**
     * Using Binary Search (sorted.get(i) >= num) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private static int lowerBound(List<Integer> sorted, int num) {
        int low = 0;
        int high = sorted.size() - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (sorted.get(mid) >= num) {
                // we need to go to left (shrink) to get minimum index
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
