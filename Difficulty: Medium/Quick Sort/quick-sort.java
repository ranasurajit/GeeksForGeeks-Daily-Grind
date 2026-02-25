class Solution {
    /**
     * Approach : Using Quick Sort Approach
     * 
     * TC: O(N x log(N))
     * SC: O(log(N))
     * - O(log(N)) - recursion stack
     */
    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            /**
             * out of bounds or single element is 
             * left which is by default sorted
             */
            return;
        }
        int pivotIdx = partition(arr, low, high); // TC: O(N), SC: O(1)
        quickSort(arr, low, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, high);
    }

    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private int partition(int[] arr, int low, int high) {
        int pivotIdx = low;
        int i = low;
        int j = high;
        while (i <= j) { // TC: O(N)
            while (i <= j && arr[i] <= arr[pivotIdx]) {
                i++;
            }
            while (i <= j && arr[j] >= arr[pivotIdx]) {
                j--;
            }
            if (i <= j) {
                // swap arr[i] with arr[j]
                swap(arr, i, j); // TC: O(1)
            }
        }
        // at this place i > j, so swap arr[pivotIdx] with arr[j]
        swap(arr, pivotIdx, j);  // TC: O(1)
        // return position of j which is the correct position of arr[pivotIdx]
        return j;
    }

    /**
     * Using Enumeration Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private void swap(int[] arr, int p, int q) {
        int temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }
}
