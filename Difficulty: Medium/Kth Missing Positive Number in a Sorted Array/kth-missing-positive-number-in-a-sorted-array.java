class Solution {
    /**
     * Approach II : Using Binary Search on Answers Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    public int kthMissing(int[] arr, int k) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        int index = -1;
        /**
         * Number missing till index i = arr[i] - (i + 1)
         */
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            int missingNumbersTillMid = arr[mid] - (mid + 1);
            if (missingNumbersTillMid >= k) {
                high = mid - 1;
            } else {
                index = mid;
                low = mid + 1;
            }
        }
        if (index == -1) {
            // index -1 means kth missing is before arr[0]
            return k;
        }
        // missing elements till index
        int missingElements = arr[index] - (index + 1);
        return arr[index] + k - missingElements;
    }

    /**
     * Approach I : Using Binary Search on Answers Approach
     * 
     * TC: O(log(Max(arr)) x log(N))
     * SC: O(1)
     */
    public int kthMissingApproachI(int[] arr, int k) {
        int n = arr.length;
        int low = 1; // minimum positive number
        int high = arr[n - 1] + k; // maximum positive number
        while (low <= high) { // TC: O(log(Max(arr)))
            int mid = low + (high - low) / 2;
            int numbersPresentInArrTillMid = upperBound(arr, n, mid); // TC: O(log(N))
            int missingNumbersTillMid = mid - numbersPresentInArrTillMid;
            if (missingNumbersTillMid >= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Using Binary Search (For Upper Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(int[] arr, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O()
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
