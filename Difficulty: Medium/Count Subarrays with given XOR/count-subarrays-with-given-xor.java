class Solution {
    /**
     * Approach : Using HashMap Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     * 
     * Note: First thinking might go around using Sliding Window
     * but XOR operations is not monotonous and cummulative XOR
     * operations might increase or decrease results
     */
    public long subarrayXor(int arr[], int k) {
        int n = arr.length;
        int[] prefix = new int[n];    // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefix[i] = (i == 0) ? arr[i] : (prefix[i - 1] ^ arr[i]);
        }
        /**
         * Intuition: For valid conditions, prefix[r] ^ prefix[l - 1] = k,
         * so by XOR property, prefix[l - 1] = k ^ prefix[r]
         * 
         * so we need to check the count of times we have seen for 
         * every prefix[i] ^ k, if we have seen and prefix[l - 1] before
         * so, we will store { prefixXOR, count(previousPrefixXOR) } 
         * in HashMap
         */
        Map<Integer, Integer> countXORMap = new HashMap<Integer, Integer>(); // SC: O(N)
        countXORMap.put(0, 1); // initial XOR before iteration
        long count = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int key = prefix[i] ^ k;
            if (countXORMap.containsKey(key)) {
                count += countXORMap.get(key);
            }
            countXORMap.put(prefix[i], countXORMap.getOrDefault(prefix[i], 0) + 1);
        }
        return count;
    }
}
