
class Solution {
    /**
     * Approach IV : Using Space Optimization Approach
     * 
     * TC: O(N x N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     * - O(N) - current, next1 and next2 array memory
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int maximumAmount(int arr[]) {
        int n = arr.length;
        // Initialization
        int[] next2 = new int[n]; // SC: O(N)
        int[] next1 = new int[n]; // SC: O(N)
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) {
            int[] current = new int[n]; // SC: O(N)
            for (int j = i; j < n; j++) {
                if (i == j) {
                    current[j] = arr[i];
                    continue;
                }
                int leftSelection = arr[i] + Math.min(
                    (i + 2 <= j) ? next2[j] : 0, // opponent picks leftt
                    (i + 1 <= j - 1) ? next1[j - 1] : 0); // opponent picks right
                int rightSelection = arr[j] + Math.min(
                    (i + 1 <= j - 1) ? next1[j - 1] : 0, // opponent picks left
                    (i <= j - 2) ? current[j - 2] : 0); // opponent picks right
                current[j] = Math.max(leftSelection, rightSelection);
            }
            next2 = next1;
            next1 = current;
        }
        return next1[n - 1];
    }

    /**
     * Approach III : Using Tabulation Approach
     * 
     * TC: O(N x N)
     * SC: O(N x N)
     * - O(N x N) - dp array memory
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int maximumAmountTabulation(int arr[]) {
        int n = arr.length;
        // Initialization
        int[][] dp = new int[n][n]; // SC: O(N x N)
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = arr[i];
                    continue;
                }
                int leftSelection = arr[i] + Math.min(
                    (i + 2 <= j) ? dp[i + 2][j] : 0, // opponent picks leftt
                    (i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0); // opponent picks right
                int rightSelection = arr[j] + Math.min(
                    (i + 1 <= j - 1) ? dp[i + 1][j - 1] : 0, // opponent picks left
                    (i <= j - 2) ? dp[i][j - 2] : 0); // opponent picks right
                dp[i][j] = Math.max(leftSelection, rightSelection);
            }
        }
        return dp[0][n - 1];
    }

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
    public int maximumAmountMemoization(int arr[]) {
        int n = arr.length;
        int[][] memo = new int[n][n]; // SC: O(N x N)
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
