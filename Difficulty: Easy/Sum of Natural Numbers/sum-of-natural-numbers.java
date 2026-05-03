// User function Template for Java

class Solution {
    /**
     * Approach III : Using Math Approach
     * 
     * TC : O(1)
     * SC : O(1)
     */
    static int sumOfNaturals(int n) {
        return (n * (n + 1)) / 2;
    }

    /**
     * Approach II : Using Iterative Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    static int sumOfNaturalsIteration(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) { // TC : O(n)
            sum += i;
        }
        return sum;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     * 
     * - O(n) - recursion stack
     */
    static int sumOfNaturalsRecursion(int n) {
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
