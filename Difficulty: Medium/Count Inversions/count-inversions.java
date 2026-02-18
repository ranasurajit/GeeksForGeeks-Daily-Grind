class Solution {
    /**
     * Approach II : Using Merge-Sort Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    static int inversionCount(int arr[]) {
        int n = arr.length;
        int[] count = { 0 };
        mergeSort(arr, 0, n - 1, count); // TC: O(N x log(N)), SC: O(N)
        return count[0];
    }
    
    /**
     * Using Merge-Sort Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     */
    private static void mergeSort(int[] arr, int low, int high, int[] count) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(arr, low, mid, count);      // TC: O(log(N))
        mergeSort(arr, mid + 1, high, count); // TC: O(log(N))
        mergeSortedArrays(arr, low, mid, high, count); // TC: O(N x log(N)), SC: O(N)
    }
    
    /**
     * Using Merge-Sort Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     */
    private static void mergeSortedArrays(int[] arr, int low, int mid, int high, int[] count) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
                count[0] += (mid - left + 1);
            }
        }
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }
        for (int p = low; p <= high; p++) {
            arr[p] = temp.get(p - low);
        }
    }

    /**
     * Approach I : Using Brute-Force (Simulation) Approach
     * 
     * TC: O(N²)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1110 / 1115 testcases passed)
     */
    static int inversionCountBruteForce(int arr[]) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {     // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
