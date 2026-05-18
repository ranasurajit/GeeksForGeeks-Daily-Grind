class Solution {
    public int maxSum(int n) {
        return solve(n);
    }
    
    private int solve(int n) {
        // Base Case
        if (n <= 2) {
            return n;
        }
        // Recursion Calls
        int currentSum = solve(n / 2) + solve(n / 3) + solve(n / 4);
        return Math.max(n, currentSum);
    }
}
