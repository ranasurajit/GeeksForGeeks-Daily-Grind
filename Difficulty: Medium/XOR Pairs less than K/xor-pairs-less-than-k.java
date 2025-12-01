class Solution {
    /**
     * Approach II : Using Trie Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     *  Accepted (1120 / 1120 testcases passed)
     */
    public int cntPairs(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        Trie trie = new Trie(); // SC: O(N)
        for (int i = 0; i < n; i++) {     // TC: O(N)
            count += trie.countQuery(arr[i], k); // TC: O(32), SC: O(1)
            trie.insert(arr[i]); // TC: O(32), SC: O(1)
        }
        return count;
    }
    
    private class Trie {
        TrieNode root = new TrieNode();
        
        public TrieNode getRoot() {
            return this.root;
        }
        
        /**
         * Using Trie Insert Operation Approach
         * 
         * TC: O(32)
         * SC: O(1)
         */
        public void insert(int num) {
            TrieNode crawler = root;
            for (int i = 31; i >= 0; i--) {
                int bit = ((num >> i) & 1) == 1 ? 1 : 0;
                if (crawler.children[bit] == null) {
                    crawler.children[bit] = new TrieNode();
                }
                crawler = crawler.children[bit];
                crawler.count++;
            }
        }
        
        /**
         * Using Trie Query Operation Approach
         * 
         * TC: O(32)
         * SC: O(1)
         */
        public int countQuery(int num, int k) {
            TrieNode crawler = root;
            int pairs = 0;
            for (int i = 31; i >= 0; i--) { // TC: O(32)
                if (crawler == null) {
                    break;
                }
                int numBit = ((num >> i) & 1) == 1 ? 1 : 0;
                int kBit = ((k >> i) & 1) == 1 ? 1 : 0;
                if (kBit == 1) {
                    /**
                     * here to have ybit ^ numBit so that < kBit, 
                     * we can have yBit = 1 (cancels) else we need
                     * to check lower bit positions
                     */
                    int sameBit = numBit;
                    if (crawler.children[sameBit] != null) {
                        pairs += crawler.children[sameBit].count;
                    }
                    crawler = crawler.children[1 - numBit];
                } else {
                    /**
                     * here to have ybit ^ numBit so that < kBit
                     * we can have yBit only 0
                     */
                    crawler = crawler.children[numBit];
                }
            }
            return pairs;
        }
    }

    private class TrieNode {
        TrieNode[] children;
        int count;
        
        public TrieNode() {
            this.children = new TrieNode[2];
            this.count = 0;
        }
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N x N)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1010 / 1120 testcases passed)
     */
    public int cntPairsBruteForce(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {     // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if ((arr[i] ^ arr[j]) < k) {
                    count++;
                }
            }
        }
        return count;
    }
}
