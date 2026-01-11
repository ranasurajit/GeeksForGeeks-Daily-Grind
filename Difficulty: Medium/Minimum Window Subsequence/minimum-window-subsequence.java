class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(M x N)
     * SC: O(1)
     */
    public String minWindow(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int i = 0; // pointer at the start of String 's1'
        int startIndex = -1;
        int minLength = Integer.MAX_VALUE;
        while (i < m) { // TC: O(M)
            // forward scan till we find a matching subsequence
            int j = 0; // pointer at the start of String 's2'
            while (i < m) { // TC: O(M)
                if (s1.charAt(i) == s2.charAt(j)) {
                    j++;
                    if (j == n) {
                        // subsequence match is found
                        break;
                    }
                }
                i++;
            }
            if (j < n) {
                // no possibility to find any matching subsequnce further
                break;
            }
            /**
             * here we already found a match so now shrink the window to 
             * minimize window,
             * here we would try to re-match s1 and s2 again in reverse 
             * direction so that we can find the left-most valid 
             * startIndex for this endIndex
             */
            int endIndex = i;
            j = n - 1;
            while (i >= 0) { // TC: O(N)
                if (s1.charAt(i) == s2.charAt(j)) {
                    j--;
                    if (j < 0) {
                        break;
                    }
                }
                i--;
            }
            // valid window size is endIndex - i + 1
            int currentWindowSize = endIndex - i + 1;
            if (currentWindowSize < minLength) {
                minLength = currentWindowSize;
                startIndex = i;
            }
            i++;
        }
        return startIndex == -1 ? "" : s1.substring(startIndex, startIndex + minLength);
    }
}
