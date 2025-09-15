class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(Max(N, M))
     * SC: O(1)
     */
    public boolean stringStack(String pat, String tar) {
        int n = pat.length();
        int m = tar.length();
        int p = n - 1; // pointer at the end of String 'pat'
        int q = m - 1; // pointer at the end of String 'tar'
        while (p >= 0 && q >= 0) { // TC: O(Max(N, M))
            if (pat.charAt(p) == tar.charAt(q)) {
                p--;
                q--;
            } else {
                // as we have inserted it and also need to delete it
                p -= 2;
            }
        }
        return q < 0;
    }
}
