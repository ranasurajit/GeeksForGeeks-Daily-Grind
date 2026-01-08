class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N)
     * SC: O(K)
     */
    public int countAtMostK(int arr[], int k) {
        int n = arr.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(K)
        int count = 0;
        while (j < n) { // TC: O(N)
            freqMap.put(arr[j], freqMap.getOrDefault(arr[j], 0) + 1);
            while (freqMap.size() > k) {
                // remove computation from index 'i'
                int countFreq = freqMap.get(arr[i]);
                if (countFreq == 1) {
                    freqMap.remove(arr[i]);
                } else {
                    freqMap.put(arr[i], countFreq - 1);
                }
                i++;
            }
            count += (j - i + 1);
            j++;
        }
        return count;
    }
}
