class Solution {
    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(N)) + O(log(N)) ~ O(log(N))
     * SC: O(1)
     */
    public int countLessEqual(int[] arr, int x) {
        int n = arr.length;
        /**
         * Since the array is rotated and sorted, so
         * there must be a pivot point around which
         * arr[0] to arr[pivot - 1] is sorted and
         * arr[pivot] to arr[n - 1] is sorted so we
         * can use Binary Search to find the pivot
         * index first - here pivot is nothing but 
         * the index with minimum value
         */
        int pivotIndex = binaryPivotSearch(arr, n); // TC: O(log(N))
        int count = 0;
        if (pivotIndex == 0) {
            // array 'arr' is not rotated
            count = upperBound(arr, 0, n - 1, x); // TC: O(log(N))
        } else {
            // left sorted part count
            int leftCount = 
                upperBound(arr, 0, pivotIndex - 1, x); // TC: O(log(N))
            // right sorted part count
            int rightCount =
                upperBound(arr, pivotIndex, n - 1, x) - pivotIndex; // TC: O(log(N))
            count = leftCount + rightCount;
        }
        return count;
    }
    
    /**
     * Using Binary Search (To Find Minimum Index In Rotated Sorted Array) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int binaryPivotSearch(int[] arr, int n) {
        int low = 0;
        int high = n - 1;
        if (arr[low] <= arr[high]) {
            // array is not rotated
            return 0;
        }
        int minIndex = n;
        int minValue = Integer.MAX_VALUE;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr[low] <= arr[mid]) {
                // left side is sorted so,  minimum lies on the right half
                // eliminate left half but take minimum value
                if (minValue > arr[low]) {
                    minValue = arr[low];
                    minIndex = low;
                }
                low = mid + 1;
            } else {
                // right side is sorted so,  minimum lies on the left half
                // eliminate right half but take minimum value
                if (minValue > arr[mid]) {
                    minValue = arr[mid];
                    minIndex = mid;
                }
                high = mid - 1;
            }
        }
        return minIndex;
    }

    /**
     * Using Binary Search (To Find Upper Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(int[] arr, int low, int high, int x) {
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
