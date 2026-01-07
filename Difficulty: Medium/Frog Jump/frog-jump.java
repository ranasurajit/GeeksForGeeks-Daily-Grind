class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(N)
     * O(N) - memoization memory
     * O(N) - recursion stack space
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    int minCost(int[] height) {
        int n = height.length;
        int[] memo = new int[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(height, n - 1, memo);
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N)
     * SC: O(N) - recursion stack space
     */
    private int solveMemoization(int[] height, int idx, int[] memo) {
        // Base Case
        if (idx == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        /**
         * To reach ith stair, frog would have jumped from 
         * (i - 1)th stair or (i - 2)th stair. so minimum ways 
         * = Min(ways to reach ith stair from (i - 1)th stair,
         * ways to reach ith stair from (i - 2)th stair)
         */
        int oneStepWays = Integer.MAX_VALUE;
        int twoStepWays = Integer.MAX_VALUE;
        if (idx > 0) {
            oneStepWays = Math.abs(height[idx] - height[idx - 1]) + 
                solveMemoization(height, idx - 1, memo);
        }
        if (idx > 1) {
            twoStepWays = Math.abs(height[idx] - height[idx - 2]) +
                solveMemoization(height, idx - 2, memo);
        }
        return memo[idx] = Math.min(oneStepWays, twoStepWays);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * O(N) - recursion stack space
     * 
     * Time Limit Exceeded (1010 / 1115 testcases passed)
     */
    int minCostRecursion(int[] height) {
        int n = height.length;
        return solveRecursion(height, n - 1);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int[] height, int idx) {
        // Base Case
        if (idx == 0) {
            return 0;
        }
        // Recursion Calls
        /**
         * To reach ith stair, frog would have jumped from 
         * (i - 1)th stair or (i - 2)th stair. so minimum ways 
         * = Min(ways to reach ith stair from (i - 1)th stair,
         * ways to reach ith stair from (i - 2)th stair)
         */
        int oneStepWays = Integer.MAX_VALUE;
        int twoStepWays = Integer.MAX_VALUE;
        if (idx > 0) {
            oneStepWays = Math.abs(height[idx] - height[idx - 1]) + 
                solveRecursion(height, idx - 1);
        }
        if (idx > 1) {
            twoStepWays = Math.abs(height[idx] - height[idx - 2]) +
                solveRecursion(height, idx - 2);
        }
        return Math.min(oneStepWays, twoStepWays);
    }
}
