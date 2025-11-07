class Solution {
    /**
     * Approach III : Using Memoization (Optimized) Approach
     * 
     * TC: O(N x log(N)) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     * 
     * - O(N) - memoization array
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1110 / 1120 testcases passed)
     */
    public int maxProfit(int[][] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]); // TC: O(N x log(N))
        int[] last = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            // setting highest index of job that can be selected if index 'i' job is selected
            last[i] = getNonConflictedLastJobIndex(jobs, i); // TC: O(log(N))
        }
        int[] memo = new int[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoizationOptimized(n - 1, jobs, last, memo); // TC: O(N), SC: O(N)
    }
    
    /**
     * Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int getNonConflictedLastJobIndex(int[][] jobs, int i) {
        int low = 0;
        int high = i - 1;
        int index = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (jobs[mid][1] <= jobs[i][0]) {
                index = mid;
                // we need to maximize index
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return index;
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int solveMemoizationOptimized(int idx, int[][] jobs, 
        int[] last, int[] memo) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        // pick or skip
        int pick = 0;
        int skip = 0;
        // pick or skip
        pick = jobs[idx][2] + solveMemoizationOptimized(last[idx], jobs, last, memo);
        skip = solveMemoizationOptimized(idx - 1, jobs, last, memo);
        return memo[idx] = Math.max(pick, skip);
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x log(N)) + O(N x N) + O(N x N) ~ O(N x N)
     * SC: O(N x N) + O(N)
     * 
     * - O(N x N) - memoization array
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1110 / 1120 testcases passed)
     */
    public int maxProfitMemoization(int[][] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]); // TC: O(N x log(N))
        int[][] memo = new int[n][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(N)
        }
        return solveMemoization(0, -1, n, jobs, memo); // TC: O(N x N), SC: O(N)
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int prevIdx, int n, int[][] jobs, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        // Memoization Check
        if (memo[idx][prevIdx + 1] != -1) {
            return memo[idx][prevIdx + 1];
        }
        // pick or skip
        int pick = 0;
        int skip = 0;
        if (prevIdx == -1 || jobs[idx][0] >= jobs[prevIdx][1]) {
            // pick or skip
            pick = jobs[idx][2] + solveMemoization(idx + 1, idx, n, jobs, memo);
            skip = solveMemoization(idx + 1, prevIdx, n, jobs, memo);
        } else {
            // need to skip
            skip = solveMemoization(idx + 1, prevIdx, n, jobs, memo);
        }
        return memo[idx][prevIdx + 1] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(N x log(N)) + O(2 ^ N) ~ O(2 ^ N)
     * SC: O(N)
     * 
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1120 testcases passed)
     */
    public int maxProfitRecursion(int[][] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]); // TC: O(N x log(N))
        return solveRecursion(0, -1, n, jobs); // TC: O(2 ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int prevIdx, int n, int[][] jobs) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        // pick or skip
        int pick = 0;
        int skip = 0;
        if (prevIdx == -1 || jobs[idx][0] >= jobs[prevIdx][1]) {
            // pick or skip
            pick = jobs[idx][2] + solveRecursion(idx + 1, idx, n, jobs);
            skip = solveRecursion(idx + 1, prevIdx, n, jobs);
        } else {
            // need to skip
            skip = solveRecursion(idx + 1, prevIdx, n, jobs);
        }
        return Math.max(pick, skip);
    }
}
