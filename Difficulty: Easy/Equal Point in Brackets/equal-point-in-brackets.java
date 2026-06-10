class Solution {
    /**
     * Approach II : Using Optimal (Prefix-Suffix Array Without Extra Space) Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public int findIndex(String s) {
        int n = s.length();
        int totalClose = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (s.charAt(i) == ')') {
               totalClose++;
            }
        }
        int open = 0;
        int close = totalClose;
        for (int i = 0; i <= n; i++) { // TC : O(n)
            // index 'i' is the split position
            if (open == close) {
                return i;
            }
            if (i < n) {
                if (s.charAt(i) == '(') {
                    open++;
                } else {
                    close--;
                }
            }
        }
        return -1;
    }

    /**
     * Approach I : Using Brute-Force (Prefix-Suffix Array With Extra Space) Approach
     * 
     * TC : O(n) + O(n) + O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public int findIndexBruteForce(String s) {
        int n = s.length();
        int[] opened = new int[n]; // SC : O(n)
        opened[0] = s.charAt(0) == '(' ? 1 : 0;
        for (int i = 1; i < n; i++) { // TC : O(n)
            opened[i] += opened[i - 1];
            if (s.charAt(i) == '(') {
                opened[i] += 1;
            }
        }
        int[] closed = new int[n]; // SC : O(n)
        closed[n - 1] = s.charAt(n - 1) == ')' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            closed[i] += closed[i + 1];
            if (s.charAt(i) == ')') {
                closed[i] += 1;
            }
        }
        int close = 0;
        for (int i = n; i > 0; i--) { // TC : O(n)
            int left = i - 1;
            if (opened[left] == close) {
                return i;
            }
            if (s.charAt(i - 1) == ')') {
                close++;
            }
        }
        int open = 0;
        for (int i = -1; i <= n; i++) { // TC : O(n)
            int right = i + 1;
            if (closed[right] == open) {
                return i + 1;
            }
            if (s.charAt(i + 1) == '(') {
                open++;
            }
        }
        return -1;
    }
}
