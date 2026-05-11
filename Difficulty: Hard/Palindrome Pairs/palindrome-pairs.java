class Solution {
    /**
     * Approach II : Using Optimal (Trie + Two Pointers) Approach
     * 
     * TC : O(n x k^2)
     * SC : O(n x k)
     * 
     * where k = maximum length of String in String[] arr
     * 
     * Accepted (1101 / 1101 testcases passed)
     */
    public boolean palindromePair(String[] arr) {
        int n = arr.length;
        TrieNode root = new TrieNode(); // SC : O(n x k)
        for (int i = 0; i < n; i++) { // TC : O(n)
            insert(root, arr[i], i); // TC: O(k^2)
        }
        for (int i = 0; i < n; i++) { // TC : O(k)
            if (search(root, arr[i], i)) { // TC: O(k^2)
                return true;
            }
        }
        return false;
    }
    
    private void insert(TrieNode root, String word, int idx) {
        int n = word.length();
        TrieNode current = root;
        for (int i = n - 1; i >= 0; i--) { // TC : O(k)
            if (isPalindromeCheck(word, 0, i)) { // TC : O(k)
                current.indices.add(idx);
            }
            int currIdx = word.charAt(i) - 'a';
            if (current.children[currIdx] == null) {
                current.children[currIdx] = new TrieNode();
            }
            current = current.children[currIdx];
        }
        current.idx = idx;
        current.indices.add(idx);
    }
    
    private boolean search(TrieNode root, String word, int idx) {
        int n = word.length();
        TrieNode current = root;
        for (int i = 0; i < n && current != null; i++) { // TC : O(k)
            int index = word.charAt(i) - 'a';
            if (current.idx >= 0 && current.idx != idx && isPalindromeCheck(word, i, n - 1)) { // TC : O(k)
                return true;
            }
            current = current.children[index];
        }
        if (current != null) {
            for (int index : current.indices) { // TC : O(k)
                if (index != idx) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC : O(k / 2) ~ O(k)
     * SC : O(1)
     */
    private boolean isPalindromeCheck(String s, int i, int j) {
        while (i < j) { // TC : O(k)
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation + Two Pointers) Approach
     * 
     * TC : O(n^2 x k)
     * SC : O(k) + O(k) ~ O(k)
     * 
     * where k = maximum length of String formed by two largest pairs in String[] arr
     * 
     * Time Limit Exceeded (1100 / 1101 testcases passed)
     */
    public boolean palindromePairBruteForce(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int j = i + 1; j < n; j++) { // TC : O(n)
                String combinedStr = arr[i] + arr[j]; // SC : O(k)
                if (isPalindrome(combinedStr)) { // TC : O(k)
                    return true;
                }
                String revCombinedStr = arr[j] + arr[i]; // SC : O(k)
                if (isPalindrome(revCombinedStr)) { // TC : O(k)
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC : O(k / 2) ~ O(k)
     * SC : O(1)
     */
    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) { // TC : O(k)
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

class TrieNode {
    TrieNode[] children;
    List<Integer> indices; // to store indices of palindromic words
    int idx; // to store index within String 'arr'
    
    public TrieNode() {
        this.idx = -1;
        this.children = new TrieNode[26];
        this.indices = new ArrayList<>();
    }
}
