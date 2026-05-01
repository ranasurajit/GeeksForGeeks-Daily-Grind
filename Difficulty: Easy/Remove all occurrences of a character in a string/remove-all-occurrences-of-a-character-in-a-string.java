// User function Template for Java
class Solution {
    // Function to remove all occurrences of the character from the string
    /**
     * Approach : Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public void removeCharacter(StringBuilder s, char c) {
        solve(0, s, c);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
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
            s.deleteCharAt(idx);
            solve(idx, s, c);
        } else {
            solve(idx + 1, s, c);
        }
    }
}
