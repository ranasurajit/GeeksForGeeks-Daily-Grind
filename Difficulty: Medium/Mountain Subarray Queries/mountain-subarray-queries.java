class Solution {
    /**
     * Approach : Using Prefix-Array Approach
     * 
     * TC : O(n) + O(n) + O(q) ~ O(n + q)
     * SC : O(n) + O(n) ~ O(n)
     */
    public ArrayList<Boolean> processQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        /**
         * we will pre-compute from every index 'i' till
         * what index we can go further towards right in 
         * ascending order
         */
        int[] incEnd = new int[n];         // SC : O(n)
        incEnd[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            incEnd[i] = (arr[i] <= arr[i + 1]) ? incEnd[i + 1] : i;
        }
        /**
         * we will pre-compute from every index 'i' till
         * what index we can go further towards left in 
         * descending order
         */
        int[] decStart = new int[n];  // SC : O(n)
        decStart[0] = 0;
        for (int i = 1; i < n; i++) { // TC : O(n)
            decStart[i] = (arr[i - 1] >= arr[i]) ? decStart[i - 1] : i;
        }
        /**
         * if there is a peak between a query [l, r] then there must be
         * an overlap of incEnd[l] and decStart[r] i.e. 
         * incEnd[l] >= decStart[r]
         */
        ArrayList<Boolean> result = new ArrayList<>();
        for (int[] query : queries) { // TC : O(q)
            int l = query[0];
            int r = query[1];
            result.add(incEnd[l] >= decStart[r]);
        }
        return result;
    }
}
