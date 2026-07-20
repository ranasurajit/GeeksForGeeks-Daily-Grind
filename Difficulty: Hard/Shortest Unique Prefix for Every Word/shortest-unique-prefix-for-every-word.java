class Solution {
    /**
     * Approach : Using Trie Approach
     * 
     * TC : O(n x l) + O(n x l) ~ O(n x l)
     * SC : O(n x l)
     */
    public ArrayList<String> findPrefixes(String[] arr) {
        int n = arr.length;
        TrieNode root = new TrieNode(); // SC : O(n x l)
        // we need to insert the word elements from String[] arr in Trie
        for (int i = 0; i < n; i++) { // TC : O(n)
            insert(root, arr[i]); // TC : O(l)
        }
        /**
         * we need to get the index of each character of word elements
         * from String[] arr in Trie where the count at the character
         * is 1 i.e. it becomes a unique prefix out of all elements
         */
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) { // TC : O(n)
            int idx = getLastIndex(root, arr[i]); // TC : O(l)
            result.add(arr[i].substring(0, idx));
        }
        return result;
    }
    
    /**
     * Using Trie Insert Word Approach
     * 
     * TC : O(l)
     * SC : O(1)
     */
    private void insert(TrieNode root, String word) {
        TrieNode current = root;
        int l = word.length();
        for (int i = 0; i < l; i++) { // TC : O(l)
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                current.children[idx] = new TrieNode();
            }
            current = current.children[idx];
            current.count++;
        }
    }
    
    /**
     * Using Trie Search Word Approach
     * 
     * TC : O(l)
     * SC : O(1)
     */
    private int getLastIndex(TrieNode root, String word) {
        TrieNode current = root;
        int lastIndex = 0;
        int l = word.length();
        for (int i = 0; i < l; i++) { // TC : O(l)
            int idx = word.charAt(i) - 'a';
            if (current.children[idx].count == 1) {
                return (i + 1);
            }
            current = current.children[idx];
            lastIndex++;
        }
        return l;
    }
}

class TrieNode {
    TrieNode[] children;
    int count;

    public TrieNode() {
        this.count = 0;
        this.children = new TrieNode[26];
    }
}
