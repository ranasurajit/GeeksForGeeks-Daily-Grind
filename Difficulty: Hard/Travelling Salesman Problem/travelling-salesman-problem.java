class Solution {
    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O((N ^ 2) x (2 ^ N))
     * SC: O(N x N) + O(N)
     * 
     * Time Limit Exceeded (10 / 1115 testcases passed)
     */
    public int tsp(int[][] cost) {
        int n = cost.length;
        int maxMask = (1 << n); // this is to be used to mark visited cities
        int[][] memo = new int[n][maxMask]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        int startCity = 0;
        int mask = 1 << startCity;
        return solveMemoization(startCity, mask, n, cost, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: O((N ^ 2) x (2 ^ N))
     * SC: O(N)
     */
    private int solveMemoization(int current, int mask, int n, int[][] cost, int[][] memo)  {
        // Base Case
        if (mask == (1 << n) - 1) {
            return cost[current][0];
        }
        // Memoization Check
        if (memo[current][mask] != -1) {
            return memo[current][mask];
        }
        // Recursion Calls
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if ((mask & (1 << i)) == 0) { // if not visited
                int newMask = (mask | (1 << i)); 
                int currentCost = cost[current][i] + 
                    solveMemoization(i, newMask, n, cost, memo); // explore
                result = Math.min(result, currentCost);
            }
        }
        return memo[current][mask] = result;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(N ^ N)
     * SC: O(N) + O(N)
     * 
     * Time Limit Exceeded (10 / 1115 testcases passed)
     */
    public int tspRecursion(int[][] cost) {
        int n = cost.length;
        boolean[] visited = new boolean[n]; // SC: O(N)
        visited[0] = true; // as we are starting from city 0
        return solveRecursion(0, visited, 1, n, cost);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(N ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int pos, boolean[] visited, 
        int countVisited, int n, int[][] cost)  {
        // Base Case
        if (countVisited == n) {
            return cost[pos][0];
        }
        // Recursion Calls
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (!visited[i]) {
                visited[i] = true; // modify
                int currentCost = cost[pos][i] + 
                    solveRecursion(i, visited, countVisited + 1, n, cost); // explore
                result = Math.min(result, currentCost);
                visited[i] = false; // backtrack
            }
        }
        return result;
    }
}
