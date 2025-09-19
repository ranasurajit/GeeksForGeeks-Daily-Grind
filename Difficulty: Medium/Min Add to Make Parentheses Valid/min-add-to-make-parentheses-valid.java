class Solution {
    /**
     * Approach : Using Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public int minParentheses(String s) {
        int n = s.length();
        /**
         * Intuition is to remove all the parentheses that gets 
         * balanced and then count of number of un-balanced 
         * parentheses will be the one required to balance them
         */
        Stack<Character> st = new Stack<Character>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (!st.isEmpty() && ch == ')' && st.peek() == '(') {
                st.pop();
            } else {
                st.push(ch);
            }
        }
        return st.size();
    }
}
