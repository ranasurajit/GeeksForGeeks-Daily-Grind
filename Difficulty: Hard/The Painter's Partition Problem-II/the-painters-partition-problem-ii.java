class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     */
    public int minTime(int[] arr, int k) {
        int n = arr.length;
        long low = 0L;
        long high = 0L;
        for (int x : arr) { // TC: O(N)
            /**
             * low: atleast a painter should be assigned one item 
             * in array 'arr' so we should start low with maximum 
             * value in in it
             * 
             * high: assuming if k = 1, i.e. 1 painter, then painter
             * should be painting all elements in array 'arr' so 
             * high = sum(arr)
             */
            low = Math.max(low, (long) x);
            high += (long) x;
        }
        // using Binary Search
        while (low <= high) { // TC: O(log(N))
            long mid = low + (high - low) / 2;
            long paintersNeeded = getPaintersCountInTime(arr, n, mid); // TC: O(N)
            if (paintersNeeded <= k) {
                // mimimize the time
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) low;
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private long getPaintersCountInTime(int[] arr, int n, long time) {
        long count = 1L; // assign 1st painter
        long sum = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (sum + arr[i] <= time) {
                sum += arr[i];
            } else {
                sum = arr[i]; // intialize sum for next painter
                count++;
            }
        }
        return count;
    }
}
