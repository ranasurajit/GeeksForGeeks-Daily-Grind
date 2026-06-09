class Solution {
    /**
     * Approach : Using Binary Search Approach
     * 
     * TC : O(n x log(n))
     * SC : O(1)
     */
    public int binarySearchable(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int target = arr[i];
            int idx = findTarget(arr, n, target); // TC : O(log(n)), SC : O(1)
            if (idx >= 0 && idx < n) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Using Binary Search Approach
     * 
     * TC : O(log(n))
     * SC : O(1)
     */
    private int findTarget(int[] arr, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
};
