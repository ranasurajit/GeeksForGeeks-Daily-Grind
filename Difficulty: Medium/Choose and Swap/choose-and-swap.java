class Solution {
    /**
     * Approach : Using Hashing + Simulation Approach
     * 
     * TC : O(n) + O(26 x n) ~ O(n)
     * SC : O(n) + O(26) ~ O(n)
     */
    public String chooseSwap(String s) {
        int n = s.length();
        /**
         * we will store the first occurence of any character
         * index in the hashing array so that we can do and
         * rectify the swap operation as early as possible
         */
        int[] first = new int[26]; // SC : O(26)
        Arrays.fill(first, -1);
        char[] chars = s.toCharArray(); // SC : O(n)
        for (int i = 0; i < n; i++) {   // TC : O(n)
            if (first[chars[i] - 'a'] == -1) {
                first[chars[i] - 'a'] = i;
            }
        }
        // we will try to pick the characters from beginning and swap
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int ch = 0; ch < (chars[i] - 'a'); ch++) { // TC : O(26)
                if (first[ch] > i) {
                    // we need to swap characters below
                    char ch1 = chars[i];
                    char ch2 = (char) ('a' + ch);
                    // we should swap globally
                    for (int j = 0; j < n; j++) { // TC : O(n) runs once
                        if (chars[j] == ch1) {
                            chars[j] = ch2;
                        } else if (chars[j] == ch2) {
                            chars[j] = ch1;
                        }
                    }
                    return String.valueOf(chars);
                }
            }
        }
        return s;
    }
}
