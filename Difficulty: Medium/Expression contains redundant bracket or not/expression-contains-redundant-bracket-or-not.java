class Solution {
    /**
     * Approach : Using Stack + String Simulation Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public static boolean checkRedundancy(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<Character>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (ch != ')') {
                st.push(ch);
            } else {
                boolean hasOperator = false;
                while (!st.isEmpty() && st.peek() != '(') {
                    char c = st.pop();
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
                        hasOperator = true;
                    }
                }
                if (!hasOperator) {
                    return true;
                }
                st.pop();
            }
        }
        return false;
    }
}
