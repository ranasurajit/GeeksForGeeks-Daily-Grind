class Solution {
    /**
     * Approach II : Using Optimal (String Simulation + Trie Insertion) Approach
     * 
     * TC: O(N²)
     * SC: O(N²) - in worst case if all characters are unique in String s
     * 
     * Accepted (1121 / 1121 testcases passed)
     */
    public static int countSubs(String s) {
        int n = s.length();
        Trie trie = new Trie(); // SC: O(N)
        int[] count = { 0 };
        for (int i = 0; i < n; i++) { // TC: O(N)
            TrieNode crawler = trie.getRoot();
            for (int j = i; j < n; j++) { // TC: O(N)
                crawler = trie.insert(crawler, s.charAt(j), count); // TC: O(1), SC: O(1)
            }
        }
        return count[0];
    }
    
    /**
     * Using Trie Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private static class Trie {
        TrieNode root = new TrieNode();
        
        public TrieNode getRoot() {
            return this.root;
        }
        
        /**
         * Using Trie Approach
         * 
         * TC: O(1)
         * SC: O(1)
         */
        public TrieNode insert(TrieNode crawler, char ch, int[] count) {
            int idx = ch - 'a';
            if (crawler.children[ch - 'a'] == null) {
                crawler.children[ch - 'a'] = new TrieNode();
                count[0]++;
            }
            return crawler.children[ch - 'a'];
        }
        
    }
    
    /**
     * Using Trie Approach
     * 
     * TC: O(1)
     * SC: O(26)
     */
    private static class TrieNode {
        TrieNode[] children;
        
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     * 
     * TC: O(N³)
     * SC: O(N²)
     * 
     * Time Limit Exceeded (1110 / 1121 testcases passed)
     */
    public static int countSubsBruteForce(String s) {
        int n = s.length();
        HashSet<String> set = new HashSet<String>(); // SC: O(N²)
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = i; j <= n; j++) { // TC: O(N)
                String substr = s.substring(i, j); // TC: O(N)
                if (!substr.equals("")) {
                    set.add(substr);
                }
            }
        }
        return set.size();
    }
}
