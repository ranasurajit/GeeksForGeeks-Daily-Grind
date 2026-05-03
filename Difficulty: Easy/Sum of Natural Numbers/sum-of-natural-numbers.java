// User function Template for Java

class Solution {
    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    static int sumOfNaturals(int n) {
        return solve(n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    private static int solve(int n) {
        // Base Case
        if (n == 1) {
            return 1;
        }
        // Recursion Calls
        int prevSum = solve(n - 1);
        return n + prevSum;
    }
};
