class Solution {
    /**
     * Approach I : Using Linear Search Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int searchInsertK(int arr[], int k) {
        int n = arr.length;
        if (k < arr[0]) {
            return 0;
        }
        if (k > arr[n - 1]) {
            return n;
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] >= k) {
                return i;
            }
        }
        return n;
    }
};
