class Solution {
    /**
     * Approach II : Using Iterative Approach
     * 
     * TC : O(log₁₀(n))
     * SC : O(1)
     */
    static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) { // TC : O(log₁₀(n))
            sum += n % 10; // remainder
            n = n / 10;
        }
        return sum;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(log₁₀(n))
     * SC : O(log₁₀(n))
     * 
     * - O(log₁₀(n)) - recursion stack
     */
    static int sumOfDigitsRecursion(int n) {
        return solve(n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(log₁₀(n))
     * SC : O(log₁₀(n))
     */
    private static int solve(int n) {
        // Base Case
        if (n == 0) {
            return 0;
        }
        // Recursion Calls
        int rem = n % 10;
        int nextSum = solve(n / 10);
        return rem + nextSum;
    }
}
