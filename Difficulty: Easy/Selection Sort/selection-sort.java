class Solution {
    /**
     * Approach : Using Selection Sort Approach
     * 
     * TC: O(N²)
     * SC: O(1)
     */
    void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            int minValue = arr[i];
            int minIndex = i;
            for (int j = i; j < n; j++) { // TC: O(N)
                if (minValue > arr[j]) {
                    minValue = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                // swap elements at index i with index minIndex
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
