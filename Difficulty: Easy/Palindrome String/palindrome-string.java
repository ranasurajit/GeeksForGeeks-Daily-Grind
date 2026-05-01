class Solution {
    /**
     * Approach II : Using Two Pointers Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    boolean isPalindrome(String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;
        while (i < j) { // TC : O(n)
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     * 
     * O(n) - recursion call stack
     */
    boolean isPalindromeRecursion(String s) {
        int n = s.length();
        return solveRecursion(s, 0, n - 1);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n / 2) ~ O(n)
     * SC : O(n / 2) ~ O(n)
     */
    private boolean solveRecursion(String s, int i, int j) {
        // Base Case
        if (i >= j) {
            // overlapped or pointing to same character
            return true;
        }
        boolean isCurrentMatched = s.charAt(i) == s.charAt(j);
        if (!isCurrentMatched) {
            return false;
        }
        // Recursion Calls
        // Recursion Leap of Faith
        boolean validateMid = solveRecursion(s, i + 1, j - 1);
        return validateMid;
    }
}
