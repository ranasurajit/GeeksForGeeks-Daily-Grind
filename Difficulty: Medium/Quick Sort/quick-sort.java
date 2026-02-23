class Solution {
    /**
     * Approach : Using Quick Sorting (Divide and Conquer) Approach
     * 
     * TC: O(N x log(N))
     * SC: O(log(N))
     * 
     * - O(log(N)) - recursion stack
     */
    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            /**
			 * invalid pointers or if low equals high, pointers 
			 * pointing to same element which is sorted by itself
			 */
			return;
        }
        // this method will return the pivot index after placing it in correct position
        // Complexity -> T(n) = O(N) + 2 x T(n / 2)
        int pivotIdx = partition(arr, low, high); // TC: O(N)
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
            // here the arr[i] > arr[pivotIdx]
            while (i <= j && arr[j] >= arr[pivotIdx]) {
                j--;
            }
            // here the arr[j] < arr[pivotIdx]
            if (i < j) {
                // swap arr[i] with arr[j]
                swap(arr, i, j); // TC: O(1), SC: O(1)
            }
        }
        // here i > j, so we need to place arr[pivotIdx] to its actual position j
        swap(arr, pivotIdx, j); // TC: O(1), SC: O(1)
        return j;
    }
    
    /**
     * Using Enumeration Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private void swap(int[] arr, int p, int q) {
        int temp = arr[q];
        arr[q] = arr[p];
        arr[p] = temp;
    } 
}
