class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     * 
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int getMinDiff(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr); // TC: O(N x log(N))
        int minH = arr[0];
        int maxH = arr[n - 1];
        int minDiff = maxH - minH;
        for (int i = 1; i < n; i++) { // TC: O(N)
            /**
             * we try to balance height with 0th index so added 
             * k to arr[0] and deducted k from others
             */
            minH = Math.min(arr[0] + k, arr[i] - k);
            if (minH < 0) {
                // minheight cannot go negative
                continue;
            }
            /**
             * we try to balance height with (n - 1)th index so 
             * deducted k from arr[n - 1] and add k to others
             */
            maxH = Math.max(arr[n - 1] - k, arr[i - 1] + k);
            minDiff = Math.min(minDiff, maxH - minH);
        }
        return minDiff;
    }
}
