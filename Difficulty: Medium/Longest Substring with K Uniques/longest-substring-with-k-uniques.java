class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N)
     * SC: O(K)
     */
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        int i = 0;      // start pointer of sliding window
        int j = 0;      // end pointer of sliding window
        Map<Integer, Integer> freqMap = new HashMap<>(); // SC: O(K)
        int maxLength = -1;
        while (j < n) { // TC: O(N)
            int chIdx = s.charAt(j) - 'a';
            freqMap.put(chIdx, freqMap.getOrDefault(chIdx, 0) + 1);
            while (freqMap.size() > k) {
                int removeIdx = s.charAt(i) - 'a';
                int freq = freqMap.get(removeIdx);
                if (freq == 1) {
                    freqMap.remove(removeIdx);
                } else {
                    freqMap.put(removeIdx, freq - 1);
                }
                i++;
            }
            // here the distinct characters <= k
            if (freqMap.size() == k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++;
        }
        return maxLength;
    }
}
