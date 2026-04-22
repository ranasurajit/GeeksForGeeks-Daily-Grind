class Solution {
    /**
     * Approach : Using Prefix Sum Approach
     * 
     * TC : O(n) + O(q) ~ O(n + q)
     * SC : O(n)
     */
    public ArrayList<Integer> findMean(int[] arr, int[][] queries) {
        int n = arr.length;
        long[] prefixSum = new long[n]; // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            prefixSum[i] = (i == 0) ?
                (long) arr[i] :
                (prefixSum[i - 1] + (long) arr[i]);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] query : queries) { // TC :O(q)
            int l = query[0];
            int r = query[1];
            long sum = prefixSum[r] - (l > 0 ? prefixSum[l - 1] : 0);
            result.add((int) (sum / (r - l + 1)));
        }
        return result;
    }
}
