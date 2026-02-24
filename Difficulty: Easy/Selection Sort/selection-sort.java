class Solution {
    /**
     * Approach : Using Selection Sort Approach
     * 
     * TC: O(N²)
     * SC: O(1)
     */
    void selectionSort(int[] arr) {
        int n = arr.length;
        /**
         * Idea: We select minimums
         */
        for (int i = 0; i < n - 1; i++) {     // TC: O(N)
            int minIdx = i;
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (i != minIdx) {
                // swap arr[minIdx] with arr[i]
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }
}
