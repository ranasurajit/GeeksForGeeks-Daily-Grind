
class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x N)
     * SC: O(N x N) + O(N)
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int maximumAmount(int arr[]) {
        int n = arr.length;
        int[][] memo = new int[n][n];
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, n - 1, arr, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int i, int j, int[] arr, int[][] memo) {
        // Base Case
        if (i == j) {
            return arr[i];
        }
        if (i > j) {
            // invalid length of arr
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int leftSelection = arr[i] + Math.min(
            solveMemoization(i + 2, j, arr, memo), // opponent picks leftt
            solveMemoization(i + 1, j - 1, arr, memo)); // opponent picks right
        int rightSelection = arr[j] + Math.min(
            solveMemoization(i + 1, j - 1, arr, memo), // opponent picks left
            solveMemoization(i, j - 2, arr, memo)); // opponent picks right
        return memo[i][j] = Math.max(leftSelection, rightSelection);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1115 testcases passed)
     */
    public int maximumAmountRecursion(int arr[]) {
        int n = arr.length;
        return solveRecursion(0, n - 1, arr);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int i, int j, int[] arr) {
        // Base Case
        if (i == j) {
            return arr[i];
        }
        if (i > j) {
            // invalid length of arr
            return 0;
        }
        // Recursion Calls
        int leftSelection = arr[i] + Math.min(
            solveRecursion(i + 2, j, arr), // opponent picks leftt
            solveRecursion(i + 1, j - 1, arr)); // opponent picks right
        int rightSelection = arr[j] + Math.min(
            solveRecursion(i + 1, j - 1, arr), // opponent picks left
            solveRecursion(i, j - 2, arr)); // opponent picks right
        return Math.max(leftSelection, rightSelection);
    }
}
