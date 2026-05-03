class Solution {
    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     * 
     * - O(n) - recursion stack
     */
    public void reverseArray(int arr[]) {
        int n = arr.length;
        solve(arr, 0, n - 1);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n / 2) ~ O(n)
     * SC : O(n / 2) ~ O(n)
     */
    private void solve(int[] arr, int i, int j) {
        // Base Case
        if (i >= j) {
            /**
             * If the pointers coincide i.e. at 
             * the same index (so reverse of 
             * single element is same) or pointers
             * cross each other then we do nothing
             */
            return;
        }
        // Recursion Calls
        /**
         * In the current step we just swap elements
         * at position index 'i' and 'j' and then
         * allow recursion to continue the same after
         * shrinking the pointers
         */
        // swap elements at index 'i' and 'j'
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
        // Recursion Leap of Faith
        solve(arr, i + 1, j - 1);
    }
}
