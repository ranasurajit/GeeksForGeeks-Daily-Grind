class Solution {
    /**
     * Approach : Using Hashing + Two Pointers Approach
     * 
     * TC : O(n) + O(m) + O(Min(n, m)) ~ O(Max(n, m)) ~ O(n)
     * SC : O(1)
     */
    int transform(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        Map<Character, Integer> freq = new HashMap<>(); // 
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s1.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < m; i++) { // TC : O(m)
            char ch = s2.charAt(i);
            int count = freq.getOrDefault(ch, 0);
            if (count == 1) {
                freq.remove(ch);
            } else {
                freq.put(ch, count - 1);
            }
        }
        if (freq.size() != 0) {
            /**
             * String 's1' and 's2' are not anagrams
             * so, transformation is not possible
             */
            return -1;
        }
        /**
         * we need to compute from end pointer at both String
         * 's1' and 's2' to check how many characters is not 
         * in order
         */
        int p = n - 1;
        int q = m - 1;
        int count = 0;
        while (p >= 0 && q >= 0) { // TC : O(n + m)
            if (s1.charAt(p) == s2.charAt(q)) {
                p--;
                q--;
            } else {
                count++;
                p--;
            }
        }
        return count;
    }
}
