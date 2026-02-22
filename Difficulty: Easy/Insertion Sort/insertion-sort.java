class Solution {
    // Please change the array in-place
    /**
     * Approach : Using Optimal (Insertion Sort) Approach
     * 
     * TC: O(N²), Best Case is O(N)
     * SC: O(1)
     */
    public void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; i++) { // TC: O(N)
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) { // TC: O(N)
                // swap arr[j] with arr[j - 1] to place arr[j] at its sorted position
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }
}
