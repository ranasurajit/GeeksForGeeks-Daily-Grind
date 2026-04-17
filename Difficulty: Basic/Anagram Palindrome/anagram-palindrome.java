class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(n) + O(26) ~ O(n)
     * SC: O(26) ~ O(1)
     */
    boolean canFormPalindrome(String s) {
        int n = s.length();
        int[] freq = new int[26]; // SC: O(26)
        for (int i = 0; i < n; i++) { // TC: O(n)
            freq[s.charAt(i) - 'a']++;
        }
        int countOdds = 0;
        for (int i = 0; i < 26; i++) { // TC: O(26)
            if ((freq[i] & 1) != 0) {
                countOdds++;
            }
        }
        if (countOdds > 1) {
            // odd frequencies can be atmost 1 found so cannot be anagram
            return false;
        }
        return true;
    }
}
