class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public boolean areRotations(String s1, String s2) {
        int n = s1.length();
        s1 += s1;
        int p = 0; // pointer at the start index of String s1
        int q = 0; // pointer at the start index of String s2
        while (p < 2 * n && q < n) { // TC: O(N)
            if (s1.charAt(p) != s2.charAt(q)) {
                p++;
            } else {
                p++;
                q++;
            }
        }
        if (q == n) {
            return true;
        }
        return false;
    }
}
