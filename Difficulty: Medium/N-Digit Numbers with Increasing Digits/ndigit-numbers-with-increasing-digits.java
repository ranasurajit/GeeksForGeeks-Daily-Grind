class Solution {
    /**
     * Approach : Using Recursion + Backtracking Approach
     * 
     * TC : O(9ⁿ)
     * SC : O(n)
     */
    public static ArrayList<Integer> increasingNumbers(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        if (n == 1) {
            for (int i = 0; i < 10; i++) {
                result.add(i);
            }
            return result;
        }
        int[] num = { 0 };
        solveRecursion(0, n, num, result);
        return result;
    }
    
    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC : O(9ⁿ)
     * SC : O(n)
     */
    private static void solveRecursion(int last, int n, 
        int[] num, ArrayList<Integer> result) {
        // Base Case
        if (n == 0) {
            result.add(num[0]);
            return;
        }
        // Recursion Calls
        for (int i = last + 1; i < 10; i++) {
            // modify
            num[0] = num[0] * 10 + i;
            // explore
            solveRecursion(i, n - 1, num, result);
            // backtrack
            num[0] = num[0] / 10;
        }
    }
}
