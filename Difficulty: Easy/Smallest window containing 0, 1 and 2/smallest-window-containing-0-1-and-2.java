class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * TC : O(n)
     * SC : O(3) ~ O(1)
     */
    public int smallestSubstring(String s) {
        int n = s.length();
        int i = 0; // pointer at the start of sliding window
        int j = 0; // pointer at the end of sliding window
        int[] counts = new int[3]; // SC : O(3)
        int minLength = Integer.MAX_VALUE;
        while (j < n) { // TC : O(n)
            updateCounts(counts, s.charAt(j), true);
            while (i < n && counts[0] > 0 && counts[1] > 0 && counts[2] > 0) {
                minLength = Math.min(minLength, j - i + 1);
                // shrink window from left
                updateCounts(counts, s.charAt(i), false);
                i++;
            }
            j++;
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
    
    /**
     * Using Enumeration Approach
     * 
     * TC : O(1)
     * SC : O(1)
     */
    private void updateCounts(int[] counts, char ch, boolean inc) {
        if (ch == '0') {
            counts[0] = inc ? (counts[0] + 1) : (counts[0] - 1);
        } else if (ch == '1') {
            counts[1] = inc ? (counts[1] + 1) : (counts[1] - 1);
        } else if (ch == '2') {
            counts[2] = inc ? (counts[2] + 1) : (counts[2] - 1);
        }
    }
};
