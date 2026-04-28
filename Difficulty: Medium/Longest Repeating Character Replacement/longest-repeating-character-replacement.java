class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * TC : O(n)
     * SC : O(26) ~ O(1)
     */
    public int longestSubstr(String s, int k) {
        int n = s.length();
        /**
         * Intuition : we should try to make operations in way to
         * such that any character with in a window should be changed
         * to the most frequent character within the same window
         * 
         * say valid window is such that, 
         * window size - count(max(frequency) <= k 
         * 
         * so sliding window is the way to go about this
         */
        int i = 0; // start pointer of the sliding window
        int j = 0; // end pointer of the sliding window
        
        // to compute maximum frequency, we need to store upper case letters
        int[] freq = new int[26]; // SC : O(26)
        int maxFreq = 0;
        int maxWindow = 0;
        while (j < n) { // TC : O(n)
            int jdx = s.charAt(j) - 'A';
            freq[jdx]++;
            maxFreq = Math.max(maxFreq, freq[jdx]);
            while (i < n && (j - i + 1) - maxFreq > k) {
                // remove computation from index 'i'
                int idx = s.charAt(i) - 'A';
                freq[idx]--;
                i++;
            }
            // here we have a valid window with atmost k operations
            maxWindow = Math.max(maxWindow, j - i + 1);
            j++;
        }
        return maxWindow;
    }
}
