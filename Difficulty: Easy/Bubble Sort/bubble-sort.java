class Solution {
    /**
     * Approach : Using Bubble Sort Approach
     * 
     * TC: O(N²)
     * SC: O(1)
     */
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        /**
         * Idea: We compare adjacent elements and 
         * move the maximum element at the last
         */
        for (int i = 0; i < n - 1; i++) {         // TC: O(N)
            for (int j = 0; j < n - 1 - i; j++) { // TC: O(N)
                if (arr[j] > arr[j + 1]) {
                    // swap elements arr[j] and arr[j + 1]
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
