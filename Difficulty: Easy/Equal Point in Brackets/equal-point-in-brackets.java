class Solution {
    /**
     * Approach : Using Prefix-Suffix Array Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public int findIndex(String s) {
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
