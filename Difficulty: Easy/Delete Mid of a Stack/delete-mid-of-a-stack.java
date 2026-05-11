class Solution {
    // Function to delete middle element of a stack.
    /**
     * Approach : Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public void deleteMid(Stack<Integer> s) {
        int n = s.size();
        solve(0, n, s);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private void solve(int i, int n, Stack<Integer> s) {
        // Base Case
        if (i == n) {
            return;
        }
        // Recursion Calls
        if (!s.isEmpty()) {
            int current = s.pop();
            solve(i + 1, n, s);
            if (i != (n / 2)) {
                s.push(current);
            }
        }
    }
}
