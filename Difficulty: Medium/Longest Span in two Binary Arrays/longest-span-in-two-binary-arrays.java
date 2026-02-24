class Solution {
    /**
     * Approach II : Using Hashing + Prefix Sum (No Mutation) Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public int equalSumSpan(int[] a1, int[] a2) {
        int n = a1.length; // size(a1) = size(a2)
        /**
         * To make this equation condition easy,
         * Condition: a1[i] + a1[i+1] + .... + a1[j] =  a2[i] + a2[i+1] + ... + a2[j]
         * we can convert this to :
         * (a1[i] - a2[i]) + (a1[i + 1] - a2[i + 1]) + .... = 0
         * 
         * we can do this on the fly
         *
         * Now this question is converted into finding the maximum
         * length of sub-array with sum = 0, where we can use a 
         * HashMap to figure the same
         * 
         * so, we will store the { prefixSum, index } in HashMap
         */
        Map<Long, Integer> map = new HashMap<>(); // SC: O(N)
        map.put(0L, -1); // needed for indexing if sum = 0 is encountered at any index >= 0
        long prefixSum = 0L;
        int maxLength = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSum += a2[i] - a1[i];
            if (map.containsKey(prefixSum)) {
                maxLength = Math.max(maxLength, i - map.get(prefixSum));
            } else {
                // do not update the key if map already contains it
                map.put(prefixSum, i);
            }
        }
        return maxLength;
    }

    /**
     * Approach I : Using Hashing + Prefix Sum (Input Modified) Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int equalSumSpanWithMutation(int[] a1, int[] a2) {
        int n = a1.length; // size(a1) = size(a2)
        /**
         * To make this equation condition easy,
         * Condition: a1[i] + a1[i+1] + .... + a1[j] =  a2[i] + a2[i+1] + ... + a2[j]
         * we can convert this to :
         * (a1[i] - a2[i]) + (a1[i + 1] - a2[i + 1]) + .... = 0
         * 
         * so, let's store it in the array a1 (in-place)
         */
        for (int i = 0; i < n; i++) { // TC: O(N)
            a1[i] = a2[i] - a1[i];
        }
        /**
         * Now this question is converted into finding the maximum
         * length of sub-array with sum = 0, where we can use a 
         * HashMap to figure the same
         * 
         * so, we will store the { prefixSum, index } in HashMap
         */
        Map<Long, Integer> map = new HashMap<>(); // SC: O(N)
        map.put(0L, -1); // needed for indexing if sum = 0 is encountered at any index >= 0
        long prefixSum = 0L;
        int maxLength = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSum += a1[i];
            if (map.containsKey(prefixSum)) {
                maxLength = Math.max(maxLength, i - map.get(prefixSum));
            } else {
                // do not update the key if map already contains it
                map.put(prefixSum, i);
            }
        }
        return maxLength;
    }
}
