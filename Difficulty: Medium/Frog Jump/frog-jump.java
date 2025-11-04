class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - memoization memory
     * - O(N) - recursion stack
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    int minCost(int[] height) {
        int n = height.length;
        int[] memo = new int[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(n - 1, height, memo); // TC: O(N), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int[] height, int[] memo) {
        // Base Case
        if (idx <= 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        int oneStepCost = Integer.MAX_VALUE;
        if (idx > 0) {
            oneStepCost = Math.abs(height[idx] - height[idx - 1]) + 
                solveMemoization(idx - 1, height, memo);
        }
        int twoStepCost = Integer.MAX_VALUE;
        if (idx > 1) {
            twoStepCost = Math.abs(height[idx] - height[idx - 2]) + 
                solveMemoization(idx - 2, height, memo);
        }
        return memo[idx] = Math.min(oneStepCost, twoStepCost);
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
    int minCostRecursion(int[] height) {
        int n = height.length;
        return solveRecursion(n - 1, height); // TC: O(2 ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int[] height) {
        // Base Case
        if (idx <= 0) {
            return 0;
        }
        // Recursion Calls
        int oneStepCost = Integer.MAX_VALUE;
        if (idx > 0) {
            oneStepCost = Math.abs(height[idx] - height[idx - 1]) + 
                solveRecursion(idx - 1, height);
        }
        int twoStepCost = Integer.MAX_VALUE;
        if (idx > 1) {
            twoStepCost = Math.abs(height[idx] - height[idx - 2]) + 
                solveRecursion(idx - 2, height);
        }
        return Math.min(oneStepCost, twoStepCost);
    }
}
