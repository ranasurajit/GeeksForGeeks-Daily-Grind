class Solution {
    /**
     * Approach : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     * 
     * - O(n) - recursion stack space
     */
    public int towerOfHanoi(int n, int from, int to, int aux) {
        int[] count = { 0 };
        solve(n, from, to, aux, count);
        return count[0];
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private void solve(int n, int src, int dest, int helper, int[] count) {
        count[0]++;
        // Base Case
        if (n == 1) {
            return;
        }
        // Recursion Calls
        // Leap of Faith that recursion will move (n - 1) disks from src to helper
        solve(n - 1, src, helper, dest, count);
        solve(n - 1, helper, dest, src, count);
    }
}
