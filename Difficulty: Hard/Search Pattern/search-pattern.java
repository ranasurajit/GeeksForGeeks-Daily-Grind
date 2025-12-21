class Solution {
    /**
     * Approach I : Using KMP Algorithm Approach
     * 
     * TC: O(N + M)
     * SC: O(N)
     */
    ArrayList<Integer> search(String pat, String txt) {
        int n = pat.length();
        int m = txt.length();
        int[] lps = new int[n]; // SC: O(N)
        buildPatternLPS(pat, n, lps); // TC: O(N), SC: O(1)
        int i = 0; // pointer at the start of String 'pat'
        int j = 0; // pointer at the start of String 'txt'
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (j < m) { // TC: O(M)
            if (pat.charAt(i) == txt.charAt(j)) {
                i++;
                j++;
                if (i == n) {
                    result.add(j - i);
                    // reset i for again search
                    i = lps[i - 1];
                }
            } else {
                if (i > 0) {
                    i = lps[i - 1];
                } else {
                    j++;
                }
            }
        }
        return result;
    }
    
    /**
     * Using KMP Algorithm (To Find LPS Array) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private void buildPatternLPS(String pat, int n, int[] lps) {
        int len = 0;
        int i = 1;
        while (i < n) { // TC: O(N)
            if (pat.charAt(len) == pat.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}
