class Solution {
    /**
     * Approach : Using Recursion + Backtracking Approach
     * 
     * TC: O(K x 2 ^ 9)
     * SC: O(K)
     */
    public ArrayList<ArrayList<Integer>> combinationSum(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> current =  new ArrayList<Integer>();
        solveRecursion(1, 0, n, k, current, result); // TC: O(K x 2 ^ 9), SC: O(K)
        return result;
    }
    
    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(K x 2 ^ 9)
     * SC: O(K)
     */
    private void solveRecursion(int start, int currentSum, int n, int k,
        ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result) {
        // Base Case
        if (current.size() == k && currentSum == n) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        if (current.size() > k || currentSum > n) {
            return;
        }
        // Recursion Calls
        for (int i = start; i <= 9; i++) { // TC: O(9)
            // modify
            currentSum += i;
            current.add(i);
            // explore
            solveRecursion(i + 1, currentSum, n, k, current, result);
            // backtrack
            currentSum -= i;
            current.remove(current.size() - 1);
        }
    }
}
