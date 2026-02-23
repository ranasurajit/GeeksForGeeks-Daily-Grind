class Solution {
    /**
     * Approach : Using Merge Sorting Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N) + O(log(N))
     *  - O(N) - temp array memory
     *  - O(log(N)) - recursion stack
     */
    void mergeSort(int arr[], int l, int r) {
        if (l >= r) {
            // out of bound or single element (which is sorted by default)
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r); // Complexity : T(N) = 2 x T(N / 2) + O(N)
        mergeSortedArrays(arr, l, mid, r); // TC: O(N), SC: O(N)
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private void mergeSortedArrays(int[] arr, int l, int mid, int r) {
        List<Integer> temp = new ArrayList<>();
        int i = l;
        int j = mid + 1;
        while (i <= mid && j <= r) { // TC: O(R - L)
            if (arr[i] > arr[j]) {
                temp.add(arr[j]);
                j++;
            } else {
                temp.add(arr[i]);
                i++;
            }
        }
        while (i <= mid) {
            temp.add(arr[i]);
            i++;
        }
        while (j <= r) {
            temp.add(arr[j]);
            j++;
        }
        for (int p = l; p <= r; p++) { // TC: O(R - L)
            arr[p] = temp.get(p - l);
        }
    }
}
