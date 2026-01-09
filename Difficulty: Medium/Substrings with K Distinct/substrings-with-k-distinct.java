class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(K) + O(K) (reused) ~ O(K)
     */
    public int countSubstr(String s, int k) {
        int n = s.length();
        /**
         * Using Sliding Window, we can find count of substrings
         * with atmost k distinct characters so, count of substrings
         * with exactly k distinct characters can be computed as 
         * f(k) - f(k - 1), f(n) = countSubstrAtMostKDistinct(n)
         */
        return countSubstrAtMostKDistinct(s, n, k) -
            countSubstrAtMostKDistinct(s, n, k - 1);
    }

    /**
     * Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N)
     * SC: O(K)
     */
    private int countSubstrAtMostKDistinct(String s, int n, int k) {
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Character, Integer> map = new HashMap<Character, Integer>(); // SC: O(K)
        int count = 0;
        while (j < n) { // TC: O(N)
            char ch = s.charAt(j);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                // remove computation from index 'i'
                char c = s.charAt(i);
                int freq = map.get(c);
                if (freq == 1) {
                    map.remove(c);
                } else {
                    map.put(c, freq - 1);
                }
                // shrink the window
                i++;
            }
            /**
             * if sub-array [i..j] has at-most k distinct characters
             * then [i+1...j], [i + 2...j] to [j..j] sub-arrays will
             * also have at-most k distinct characters
             */
            count += (j - i + 1);
            j++;
        }
        return count;
    }
}
