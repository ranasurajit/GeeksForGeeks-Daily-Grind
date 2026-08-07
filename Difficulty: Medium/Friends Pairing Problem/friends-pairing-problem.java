class Solution {
    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    public int countFriendsPairings(int n) {
        /**
         * this problem follows a recursion formula
         */
        return solveRecursion(n);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int solveRecursion(int n) {
        // Base Case
        if (n <= 2) {
            return n;
        }
        // Recursion Calls
        return solveRecursion(n - 1) + (n - 1) * solveRecursion(n - 2);
    }
}
