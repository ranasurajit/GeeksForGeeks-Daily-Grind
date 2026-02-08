class Solution {
    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    public int findKRotation(int arr[]) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            if (arr[low] <= arr[high]) {
                // all elements are in sorted position already
                return low;
            }
            int mid = low + (high - low) / 2;
            int next = (mid + 1) % n;
            int prev = ((mid - 1) + n) % n;
            if (arr[mid] <= arr[prev] && arr[mid] <= arr[next]) {
                return mid;
            } else if (arr[mid] <= arr[high]) {
                // right side of mid index of array 'arr' is sorted
                // answer is on the left
                high = mid - 1;
            } else if (arr[mid] >= arr[low]) {
                // left side of mid index of array 'arr' is sorted
                // answer is on the right
                low = mid + 1;
            }
        }
        return -1;
    }
}
