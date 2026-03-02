class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N)
     * SC: O(2) ~ O(1)
     */
    public int totalElements(int[] arr) {
        int n = arr.length;
        /**
         * since we need to find the longest sub-array
         * with atmost two distinct sub-arrays so we need
         * a HashMap to compute { key, freq } and we can
         * utilize sliding window technique to find the same
         */
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Integer, Integer> freqMap = new HashMap<>(); // SC: O(2)
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            freqMap.put(arr[j], freqMap.getOrDefault(arr[j], 0) + 1);
            while (freqMap.size() > 2) {
                // remove computation from index 'i'
                int freq = freqMap.get(arr[i]);
                if (freq == 1) {
                    freqMap.remove(arr[i]);
                } else {
                    freqMap.put(arr[i], freq - 1);
                }
                i++;
            }
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }
}
