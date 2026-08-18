class Solution {
    /**
     * Approach : Using KMP Algorithm + String Simulation Approach
     * 
     * TC : O(n) + O(n) + O(m) ~ O(n)
     * SC : O(n) + O(m) + O(m) ~ O(n)
     */
    public String compress(String s) {
        int n = s.length();
        int[] lps = new int[n]; // SC : O(n)
        computeLPS(s, lps, n);  // TC : O(n)
        /**
         * now we need to loop lps array from
         * right to left.
         * 
         * we can compress String if 
         * i is odd and lps[i] >= (i + 1) / 2
         * and (i + 1) is divisible by
         * 2 * (i + 1 - lps[i])
         */
        int i = n - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0) {
            if ((i & 1) != 0 &&
                (lps[i] >= (i + 1) / 2) &&
                (i + 1) % (2 * (i + 1 - lps[i])) == 0) {
                sb.append('*');  
                /**
                 * We compressed the second half of the
                 * current prefix, so jump to the middle.
                 */
                i = (i / 2) + 1;
            } else {
                sb.append(s.charAt(i));
            }
            i--;
        }
        return sb.reverse().toString();
    }
    
    /**
     * Using KMP Algorithm Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    private void computeLPS(String s, int[] lps, int n) {
        lps[0] = 0;
        int len = 0;
        int i = 1;
        while (i < n) { // TC : O(n)
            if (s.charAt(len) == s.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // we need to fallback len to lps[len - 1]
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    i++;
                }
            }
        }
    }
}
