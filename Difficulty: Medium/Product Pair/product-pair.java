class Solution {
    /**
     * Approach : Using Sorting + Two Pointers Approach
     * 
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public boolean isProduct(int[] arr, long target) {
        int n = arr.length;
        /**
         * as the order does not matter so we can
         * sort the array so that we can find the
         * pair in a linear time complexity
         */
        Arrays.sort(arr); // TC : O(n x log(n))
        int i = 0; // pointer at the start index of array 'arr'
        int j = n - 1; // pointer at the end index of array 'arr'
        while (i < j) {   // TC : O(n)
            long product = (long) arr[i] * (long) arr[j];
            if (product == target) {
                return true;
            } else if (product < target) {
                // we need to increase arr[i]
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
};
