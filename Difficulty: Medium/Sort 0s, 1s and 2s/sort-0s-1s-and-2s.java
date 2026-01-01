class Solution {
    /**
     * Approach III : Using Optimal (Dutch National Flag Algorithm) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public void sort012(int[] arr) {
        int n = arr.length;
        int low = 0;      // pointer at the start of array 'arr'
        int mid = 0;      // pointer at the start of array 'arr'
        int high = n - 1; // pointer at the end of array 'arr'
        while (mid <= high) { // TC: O(N)
            if (arr[mid] == 0) {
                /**
                 * mid pointer is pointing to zero then 
                 * swap arr[mid] with arr[low] and 
                 * increment both low and mid pointers
                 */
                swap(arr, low, mid); // TC: O(1)
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                /**
                 * mid pointer is pointing to one 
                 * then increment only mid pointer
                 */
                mid++;
            } else {
                /**
                 * mid pointer is pointing to two then 
                 * swap arr[mid] with arr[high] and 
                 * decrement high pointer only
                 */
                swap(arr, mid, high); // TC: O(1)
                high--;
            }
        }
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

    /**
     * Approach II : Using Better (Hashing) Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(3) ~ O(1)
     */
    public void sort012Better(int[] arr) {
        int n = arr.length;
        int[] freq = new int[3]; // SC: O(3) - distinct elements are 0, 1 and 2
        for (int i = 0; i < n; i++) { // TC: O(N)
            freq[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < 3; i++) { // TC: O(3)
            int j = 0;
            while (j < freq[i]) { // TC: O(N / 3)
                arr[index] = i;
                j++;
                index++;
            }
        }
    }

    /**
     * Approach I : Using Brute-Force (Array Sorting) Approach
     * 
     * TC: O(N x log(N))
     * SC: O(1)
     */
    public void sort012BruteForce(int[] arr) {
        Arrays.sort(arr); // TC: O(N x log(N))
    }
}
