class Solution {
    /**
     * Approach : Using Array Simulation Approach
     * 
     * TC : O(n x log(Max(arr)))
     * SC : O(1)
     */
    public int countMinOperations(int arr[]) {
        int n = arr.length;
        /**
         * instead of building the zero-array to 
         * make it exactly 'arr', we can try to 
         * compute the minimum operatios needed
         * that can make array 'arr' to zero-array
         */
        int operations = 0;
        while (true) { // TC : O(log(Max(arr)))
            // check for all odd numbers in array 'arr'
            boolean allZeroes = true;
            for (int i = 0; i < n; i++) { // TC : O(n)
                if (arr[i] != 0) {
                    allZeroes = false;
                }
                if ((arr[i] & 1) == 1) {
                    // contains odd values
                    arr[i] -= 1;
                    operations++;
                }
            }
            if (allZeroes) {
                break;
            }
            // once all the elements become even we can divide all elements by 2
            boolean hasDivided = false;
            for (int i = 0; i < n; i++) { // TC : O(n)
                if (arr[i] != 0) {
                    arr[i] /= 2;
                    hasDivided = true;
                }
            }
            if (hasDivided) {
                operations++;
            }
        }
        return operations;
    }
}
