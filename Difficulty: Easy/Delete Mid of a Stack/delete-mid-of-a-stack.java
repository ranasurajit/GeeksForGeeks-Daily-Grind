class Solution {
    // Function to delete middle element of a stack.
    /**
     * Approach II : Using Optimal Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public void deleteMid(Stack<Integer> s) {
        int n = s.size();
        int mid = (n + 1) / 2; // 1-based
        deleteStackMid(n, s, mid);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private void deleteStackMid(int n, Stack<Integer> s, int mid) {
        // Base Case
        if (s.size() == mid) {
            s.pop();
            return;
        }
        // Recursion Calls
        int current = s.pop();
        deleteStackMid(n - 1, s, mid);
        s.push(current);
    }

    /**
     * Approach I : Using Brute-Force (Recursion) Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public void deleteMidBruteForce(Stack<Integer> s) {
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
