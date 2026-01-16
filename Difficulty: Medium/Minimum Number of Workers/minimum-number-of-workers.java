class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public int minMen(int arr[]) {
        int n = arr.length;
        /**
         * Greedy Approach : we need to create a 2D array to
         * store intervals { start, end } if arr[i] != -1 and
         * greedily check for the worker who can immediately
         * available or start before or on which current worker
         * ends working
         */
        List<int[]> intervals = new ArrayList<int[]>(); // SC: O(N x 2)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] != -1) {
                int start = Math.max(0, i - arr[i]);
                int end = Math.min(n - 1, i + arr[i]);
                intervals.add(new int[] { start, end });
            }
        }
        // Sorting intervals based upon start time
        intervals.sort((a, b) -> a[0] - b[0]); // TC: O(N x log(N))
        int count = 0;
        int currentEnd = -1;
        int index = 0;
        while (currentEnd < n - 1) { // TC: O(N)
            int farthestEnd = currentEnd;
            while (index < intervals.size() && intervals.get(index)[0] <= currentEnd + 1) {
                /**
                 * keep increasing the span till current worker's 
                 * start time <= last end time i.e. worker that 
                 * is immediately available on or before previous
                 * worker ends working
                 */
                farthestEnd = Math.max(farthestEnd, intervals.get(index)[1]);
                index++;
            }
            if (farthestEnd == currentEnd) {
                // that means there is a gap which cannot be covered
                return -1;
            }
            currentEnd = farthestEnd;
            count++;
        }
        return count;
    }
}
