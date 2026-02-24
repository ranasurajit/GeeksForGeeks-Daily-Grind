class Solution {
    // Please change the array in-place
    /**
     * Approach : Using Insertion Sort Approach
     * 
     * TC: O(N²), Best Case: O(N)
     * SC: O(1)
     */
    public void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {     // TC: O(N)
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) { // TC: O(N)
                // swap arr[j] with arr[j - 1]
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }
}
