class Solution {
    /**
     * Approach : Using LCS (Prefix and Suffix DP) Approach
     * 
     * TC : O(n1 x n2)
     * SC : O(n1 x n2)
     */
    public int waysToIncreaseLCSBy1(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        /**
         * we will pre-compute prefix LCS and suffix LCS so that at every
         * index position of String 's1' we can try inserting a character
         * which is present in String 's2' such that the LCS increases
         * by 1
         */
        int[][] prefixLCS = new int[n1 + 1][n2 + 1]; // SC : O(n1 x n2)
        for (int i = 1; i < n1 + 1; i++) {     // TC : O(n1)
            for (int j = 1; j < n2 + 1; j++) { // TC : O(n2)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    prefixLCS[i][j] = 1 + prefixLCS[i - 1][j - 1];
                } else {
                    prefixLCS[i][j] = Math.max(prefixLCS[i - 1][j], prefixLCS[i][j - 1]);
                }
            }
        }
        int[][] suffixLCS = new int[n1 + 1][n2 + 1]; // SC : O(n1 x n2)
        for (int i = n1 - 1; i >= 0; i--) {     // TC : O(n1)
            for (int j = n2 - 1; j >= 0; j--) { // TC : O(n2)
                if (s1.charAt(i) == s2.charAt(j)) {
                    suffixLCS[i][j] = 1 + suffixLCS[i + 1][j + 1];
                } else {
                    suffixLCS[i][j] = Math.max(suffixLCS[i + 1][j], suffixLCS[i][j + 1]);
                }
            }
        }
        int countWays = 0;
        int lcs = prefixLCS[n1][n2];
        /**
         * let's try to insert at every position of String 's1' and
         * check if LCS(left) + 1 + LCS(right) = LCS + 1
         */
        for (int pos = 0; pos <= n1; pos++) { // TC : O(n1)
            Set<Character> trialSet = new HashSet<>(); // SC : O(n2)
            for (int j = 0; j < n2; j++) { // TC : O(n2)
                char ch = s2.charAt(j);
                if (trialSet.contains(ch)) {
                    // already tried
                    continue;
                }
                if (prefixLCS[pos][j] + 1 + suffixLCS[pos][j + 1] == lcs + 1) {
                    countWays++;
                    trialSet.add(ch);
                }
            }
        }
        return countWays;
    }
}
