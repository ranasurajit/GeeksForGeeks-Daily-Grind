class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x N)
     * SC: O(N x N) + O(N)
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        int[][] memo = new int[n][n]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(1, n - 1, arr, memo); // TC: O(N x N), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x N)
     * SC: O(N)
     */
    private static int solveMemoization(int i, int j, int[] arr, int[][] memo) {
        // Base Case
        if (i >= j) {
            // it will be invalid matrix size
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int minOperations = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int currentOperation = arr[i - 1] * arr[k] * arr[j];
            minOperations = Math.min(minOperations,
                solveMemoization(i, k, arr, memo) + 
                currentOperation + 
                solveMemoization(k + 1, j, arr, memo)
            );
        }
        return memo[i][j] = minOperations;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: Exponential
     * SC: O(N)
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1110 / 1111 testcases passed)
     */
    static int matrixMultiplicationRecursion(int arr[]) {
        int n = arr.length;
        return solveRecursion(1, n - 1, arr);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: Exponential
     * SC: O(N)
     */
    private static int solveRecursion(int i, int j, int[] arr) {
        // Base Case
        if (i >= j) {
            // it will be invalid matrix size
            return 0;
        }
        // Recursion Calls
        int minOperations = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int currentOperation = arr[i - 1] * arr[k] * arr[j];
            minOperations = Math.min(minOperations,
                solveRecursion(i, k, arr) + 
                currentOperation + 
                solveRecursion(k + 1, j, arr)
            );
        }
        return minOperations;
    }
}
