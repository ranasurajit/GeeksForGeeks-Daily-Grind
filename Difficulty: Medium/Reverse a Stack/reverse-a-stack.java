class Solution {
    /**
     * Approach : Using Recursion Approach
     * 
     * TC : O(n²)
     * SC : O(n)
     * 
     * O(n) - recursion stack
     */
    public static void reverseStack(Stack<Integer> st) {
        solveRecursion(st);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n²)
     * SC : O(n)
     */
    private static void solveRecursion(Stack<Integer> st) {
        // Base Case
        if (st.size() == 1) {
            return;
        }
        // Recursion Calls
        int last = st.pop();
        /**
         * With Recursion Leap of Faith we believe that
         * Stack st (excluding last) is already reversed
         */
        solveRecursion(st); // TC : O(n), SC : O(n)
        insertAtEnd(st, last);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private static void insertAtEnd(Stack<Integer> st, int item) {
        // Base Case
        if (st.isEmpty()) {
            st.push(item);
            return;
        }
        // Recursion Calls
        int last = st.pop();
        /**
         * With Recursion Leap of Faith we believe that
         * Stack st is reduced befor pushing last
         */
        insertAtEnd(st, item);
        st.push(last);
    }
}
