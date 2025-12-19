class match {
    // Function to fill lps[] for given patttern pat[0..M-1].
    /**
     * Approach : Using KMP Algorithm to compute LPS
     * 
     * TC: O(M)
     * SC: O(1)
     */
    void computeLPSArray(String pat, int M, int lps[]) {
        int i = 1;
        int len = 0;
        while (i < M) {
            if (pat.charAt(len) == pat.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // Function to check if the pattern exists in the string or not.
    /**
     * Approach : Using KMP Algorithm to compute LPS
     * 
     * TC: O(M + N)
     * SC: O(M)
     */
    boolean KMPSearch(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();
        int[] lps = new int[m];       // SC: O(M)
        computeLPSArray(pat, m, lps); // TC: O(M)
        int i = 0; // pointer at start of String 'txt'
        int j = 0; // pointer at start of String 'pat'
        while (i < n) { // TC: O(N)
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
                if (j == m) {
                    return true;
                }
            } else {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }
}
