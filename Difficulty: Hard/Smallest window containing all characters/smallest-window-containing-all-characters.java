class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) + Hashing Approach
     * 
     * TC: O(M) + O(26 x N) ~ O(M + N)
     * SC: O(26) + O(26) ~ O(1)
     */
    public static String minWindow(String s, String p) {
        int n = s.length();
        int m = p.length();
        int[] pfreq = new int[26]; // SC: O(26)
        int[] sfreq = new int[26]; // SC: O(26)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        for (i = 0; i < m; i++) { // TC: O(M)
            pfreq[p.charAt(i) - 'a']++;
        }
        i = 0; // reset i for sliding window
        int maxStart = n;
        int minEnd = -1;
        int minLength = n;
        while (j < n) { // TC: O(N)
            sfreq[s.charAt(j) - 'a']++;
            while (isAllCharactersPresent(sfreq, pfreq)) { // TC: O(26)
                if (minLength > j - i + 1) {
                    minLength = j - i + 1;
                    maxStart = i;
                    minEnd = j;
                }
                // remove computation from index 'i'
                sfreq[s.charAt(i) - 'a']--;
                i++;
            }
            j++;
        }
        return minEnd == -1 ? "" : s.substring(maxStart, minEnd + 1);
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(26)
     * SC: O(1)
     */
    private static boolean isAllCharactersPresent(int[] sfreq, int[] pfreq) {
        for (int i = 0; i < 26; i++) { // TC: O(26)
            if (pfreq[i] != 0 && sfreq[i] < pfreq[i]) {
                return false;
            }
        }
        return true;
    }
}
