class Solution {
    /**
     * Approach II : Using Double Recursion Approach
     * 
     * TC : O(n²)
     * SC : O(n) 
     */
    public void sortStack(Stack<Integer> st) {
        solve(st);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n²)
     * SC : O(n) 
     */
    private void solve(Stack<Integer> st) {
        // Base Case
        if (st.size() == 1) {
            return;
        }
        // Recursion Calls
        int currentItem = st.pop();
        /**
         * we will believe that recursion will give me
         * the remaining stack in ascending sort
         */
        solve(st);
        /**
         * now we need to place currentItem in its 
         * correct position in the smaller stack
         */
        placeInStackRecursion(st, currentItem); // TC : O(n), SC : O(n)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n) 
     */
    private void placeInStackRecursion(Stack<Integer> st, int num) {
        // Base Case
        if (st.isEmpty() || num >= st.peek()) {
            // no more insertion needed, add it here
            st.push(num);
            return;
        }
        // Recursion Calls
        int current = st.pop();
        // we believe that Stack is 
        placeInStackRecursion(st, num);
        st.push(current);
    }

    /**
     * Approach I : Using Recursion + Iteration Approach
     * 
     * TC : O(n²)
     * SC : O(n) 
     */
    public void sortStackRecursionIterativeApproach(Stack<Integer> st) {
        solveRecursion(st);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n²)
     * SC : O(n) 
     */
    private void solveRecursion(Stack<Integer> st) {
        // Base Case
        if (st.size() == 1) {
            return;
        }
        // Recursion Calls
        int currentItem = st.pop();
        /**
         * we will believe that recursion will give me
         * the remaining stack in ascending sort
         */
        solveRecursion(st);
        /**
         * now we need to place currentItem in its 
         * correct position in the smaller stack
         */
        placeCurrentInStack(st, currentItem); // TC : O(n)
    }
    
    /**
     * Using Stack Approach
     * 
     * TC : O(n)
     * SC : O(1) 
     */
    private void placeCurrentInStack(Stack<Integer> st, int num) {
        Stack<Integer> temp = new Stack<>();
        while (!st.isEmpty() && st.peek() > num) {
            temp.push(st.pop());
        }
        st.push(num);
        while (!temp.isEmpty()) {
            st.push(temp.pop());
        }
    }
}
