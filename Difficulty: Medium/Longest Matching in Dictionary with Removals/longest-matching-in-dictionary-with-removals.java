class Solution {

    /**
     * Approach : Using Next Occurrence Array
     *
     * A word is valid if it is a subsequence of s.
     *
     * We precompute next[i][c], which stores the first
     * position >= i where character c occurs in s.
     *
     * This allows us to check whether a dictionary word
     * is a subsequence in O(word.length()) time.
     *
     * If multiple words have the same maximum length,
     * we return the lexicographically smallest word.
     *
     * TC : O(26 * |s| + n * m)
     * SC : O(26 * |s|)
     */
    public String findLongestWord(String s, List<String> d) {

        int n = s.length();

        /*
         * next[i][c] = first position >= i
         * where character c occurs in s.
         *
         * n is used as a sentinel when the character
         * does not occur from position i onwards.
         */
        int[][] next = new int[n + 1][26];

        // No character exists after the end of s.
        for (int c = 0; c < 26; c++) {
            next[n][c] = n;
        }

        /*
         * Build the next occurrence array
         * from right to left.
         */
        for (int i = n - 1; i >= 0; i--) {

            // Copy the information from the next position.
            for (int c = 0; c < 26; c++) {
                next[i][c] = next[i + 1][c];
            }

            // Current character occurs at index i.
            next[i][s.charAt(i) - 'a'] = i;
        }

        String answer = "";

        /*
         * Check every dictionary word.
         */
        for (String word : d) {

            if (isSubsequence(word, next, n)) {

                /*
                 * Update answer if:
                 *
                 * 1. Current word is longer, OR
                 * 2. Same length but lexicographically smaller.
                 */
                if (word.length() > answer.length()
                        || (word.length() == answer.length()
                        && word.compareTo(answer) < 0)) {

                    answer = word;
                }
            }
        }

        return answer;
    }

    /**
     * Checks whether word is a subsequence of s
     * using the precomputed next occurrence array.
     */
    private boolean isSubsequence(String word, int[][] next, int n) {

        // Start searching from the beginning of s.
        int pos = 0;

        for (int i = 0; i < word.length(); i++) {

            int ch = word.charAt(i) - 'a';

            /*
             * Find the first occurrence of the required
             * character at or after pos.
             */
            int index = next[pos][ch];

            /*
             * Character is not available after pos.
             */
            if (index == n) {
                return false;
            }

            /*
             * Search for the next character after
             * the current matched character.
             */
            pos = index + 1;
        }

        return true;
    }
}
