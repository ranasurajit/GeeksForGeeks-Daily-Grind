class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     * 
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public int sumDiffPairs(int[] arr, int k) {
        int n = arr.length;
        /**
         * To efficiently get the pairs with difference
         * < k, we need to sort the array 'arr'
         */
        Arrays.sort(arr); // TC : O(n x log(n))
        int p = n - 2;
        int q = n - 1;
        int sum = 0;
        while (p >= 0) {  // TC : O(n)
            if (p >= 0 && arr[q] - arr[p] < k) {
                sum += (arr[p] + arr[q]);
                p--;
                q--;
            }
            p--;
            q--;
        }
        return sum;
    }
}
