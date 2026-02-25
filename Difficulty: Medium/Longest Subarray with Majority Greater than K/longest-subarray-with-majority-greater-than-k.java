class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int longestSubarray(int[] arr, int k) {
        int n = arr.length;
        /**
         * we will modify the input array 'arr' and
         * replace if arr[i] > k with +1 and if arr[i]
         * <= k with -1
         */
        for (int i = 0; i < n; i++) { // TC: O(N)
            arr[i] = arr[i] > k ? 1 : -1; 
        }
        int prefixSum = 0;
        int maxLength = 0;
        Map<Integer, Integer> prefixMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSum += arr[i];
            if (prefixSum > 0) {
                maxLength = i + 1;
            } else {
                if (!prefixMap.containsKey(prefixSum)) {
                    prefixMap.put(prefixSum, i);
                }
                int diff = prefixSum - 1;
                if (prefixMap.containsKey(diff)) {
                    maxLength = Math.max(maxLength, i - prefixMap.get(diff));
                }
            }
        }
        return maxLength;
    }
}
