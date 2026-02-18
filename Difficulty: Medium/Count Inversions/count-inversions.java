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
        int low = 0;
        int high = n - 1;
        return (int) mergeSort(arr, low, high); // TC: O(N x log(N))
    }

    /**
     * Using Merge-Sort Approach
     * 
     * TC: O(N x log(N)) + O(2 x log(N)) ~ O(N x log(N))
     * SC: O(N)
     */
    private static long mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return 0L;
        }
        int mid = low + (high - low) / 2;
        long count = 0L;
        count += mergeSort(arr, low, mid);                 // TC: O(log(N))
        count += mergeSort(arr, mid + 1, high);            // TC: O(log(N))
        count += 
            mergeSortedArrays(arr, low, mid, high); // TC: O(N x log(N)), SC: O(N)
        return count;
    }
    
    /**
     * Using Merge-Sort Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     */
    private static long mergeSortedArrays(int[] arr, int low, int mid, int high) {
        List<Integer> temp = new ArrayList<Integer>();
        int left = low;
        int right = mid + 1;
        long count = 0L;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                count += (mid - left + 1);
                right++;
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
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        return count;
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
