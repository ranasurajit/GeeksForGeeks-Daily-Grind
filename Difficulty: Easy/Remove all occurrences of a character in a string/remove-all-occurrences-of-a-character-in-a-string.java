// User function Template for Java
class Solution {
    // Function to remove all occurrences of the character from the string
    /**
     * Approach II : Using Optimal Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n) + O(n)
     * 
     * - O(n) - forming the String result
     * - O(n) - recursion stack
     */
    public void removeCharacter(StringBuilder s, char c) {
        StringBuilder sb = new StringBuilder(); // SC : O(n)
        solveRecursion(0, s, c, sb);
        s.setLength(0);
        s.append(sb);
    }

    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private void solveRecursion(int idx, StringBuilder s, char c, StringBuilder sb) {
        // Base Case
        if (idx >= s.length()) {
            // nothing to process more
            return;
        }
        // Recursion Calls
        char ch = s.charAt(idx);
        if (ch != c) {
            sb.append(ch);
        }
        solveRecursion(idx + 1, s, c, sb);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n²)
     * SC : O(n)
     * 
     * - O(n) - recursion stack
     */
    public void removeCharacterRecursion(StringBuilder s, char c) {
        solve(0, s, c);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n²)
     * SC : O(n)
     */
    private void solve(int idx, StringBuilder s, char c) {
        // Base Case
        if (idx >= s.length()) {
            // nothing to process more
            return;
        }
        // Recursion Calls
        char ch = s.charAt(idx);
        if (ch == c) {
            // removing character from s
            s.deleteCharAt(idx); // TC : O(n)
            /**
             * as we deleted character at index 'idx
             * so, next character takes its position
             */
            solve(idx, s, c);
        } else {
            solve(idx + 1, s, c);
        }
    }
}
