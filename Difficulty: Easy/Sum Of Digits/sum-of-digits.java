class Solution {
    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(log(n) base 10)
     * SC : O(log(n) base 10)
     */
    static int sumOfDigits(int n) {
        return solve(n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(log(n) base 10)
     * SC : O(log(n) base 10)
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
