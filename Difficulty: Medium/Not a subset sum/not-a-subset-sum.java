class Solution {
    /**
     * Approach : Using Greedy Aprroach
     * 
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public int findSmallest(int[] arr) {
        int n = arr.length;
        /**
         * the order does not matter so we can check 
         * greedily by sorting array 'arr'
         */
        Arrays.sort(arr); // TC : O(n x log(n))
        int result = 1; // this is the number we cannot form so far
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (result < arr[i]) {
                // we cannot form result
                return result;
            } else {
                result += arr[i];
            }
        }
        return result;
    }
}
