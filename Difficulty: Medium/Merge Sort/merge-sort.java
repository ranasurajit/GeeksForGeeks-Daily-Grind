class Solution {
    /**
     * Approach : Using Merge Sort (Divide and Conquer) Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N) + O(log(N))
     * - O(N) - temp array memory
     * - O(log(N)) - recursion stack
     */
    void mergeSort(int arr[], int l, int r) {
        if (l >= r) {
            /**
             * out of bounds or single element is 
             * left which is by default sorted
             */
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        mergeSortedArrays(arr, l, mid, r); // TC: O(N), SC: O(N)
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private void mergeSortedArrays(int[] arr, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        List<Integer> temp = new ArrayList<>();
        while (i <= mid && j <= high) { // TC: O(N)
            if (arr[i] <= arr[j]) {
                temp.add(arr[i]);
                i++;
            } else {
                temp.add(arr[j]);
                j++;
            }
        }
        while (i <= mid) {
            temp.add(arr[i]);
            i++;
        }
        while (j <= high) {
            temp.add(arr[j]);
            j++;
        }
        for (int p = low; p <= high; p++) {
            arr[p] = temp.get(p - low);
        }
    }
}
