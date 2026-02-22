class Solution {
    /**
     * Approach : Using Bubble Sort Approach
     * 
     * TC: O(N²)
     * SC: O(1)
     */
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {             // TC: O(N)
            // in this sorting approach always the last element is sorted
            boolean hasSwapped = false;
            for (int j = 0; j < n - i - 1; j++) { // TC: O(N)
                if (arr[j] > arr[j + 1]) {
                    hasSwapped = true;
                    // swapping adjacent elements 
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!hasSwapped) {
                return;
            }
        }
    }
}
