class Solution {
    // Function to calculate factorial of a number.
    /**
     * Approach : Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(n)
     * 
     * - O(n) - recursion stack
     */
    int factorial(int n) {
        // Base Case
        if (n <= 1) {
            return 1;
        }
        // Recursion Calls
        int prevFact = factorial(n - 1);
        return n * prevFact;
    }
}
