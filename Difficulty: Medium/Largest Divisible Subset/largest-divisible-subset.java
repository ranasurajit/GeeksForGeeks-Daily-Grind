class Solution {
    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x 2 ^ N) + O(N x log(N)) ~ O(N x 2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1110 / 1116 testcases passed)
     */
    public ArrayList<Integer> largestSubset(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr); // TC: O(N x log(N))
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[][] memo = new ArrayList[n + 1][n + 1]; // SC: O(N x N)
        return solveMemoization(0, -1, n, arr, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    private ArrayList<Integer> solveMemoization(int idx, int prev, int n, 
        int[] arr, ArrayList<Integer>[][] memo) {
        // Base Case
        if (idx == n) {
            return new ArrayList<Integer>();
        }
        // Memoization Check
        if (memo[idx][prev + 1] != null) {
            return memo[idx][prev + 1];
        }
        // Recursion Calls
        // pick or skip
        ArrayList<Integer> skip = solveMemoization(idx + 1, prev, n, arr, memo);
        ArrayList<Integer> pick = new ArrayList<Integer>();
        if (prev == -1 || arr[idx] % arr[prev] == 0) {
            pick.add(arr[idx]);
            List<Integer> next = solveMemoization(idx + 1, idx, n, arr, memo);
            pick.addAll(next);
        }
        return memo[idx][prev + 1] = pick.size() > skip.size() ? pick : skip;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N x 2 ^ N) + O(N x log(N)) ~ O(N x 2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1110 / 1116 testcases passed)
     */
    public ArrayList<Integer> largestSubsetRecursion(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr); // TC: O(N x log(N))
        return solveRecursion(0, -1, n, arr);
    }
    
    /**
     * Using Recursion Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    private ArrayList<Integer> solveRecursion(int idx, int prev, int n, int[] arr) {
        // Base Case
        if (idx == n) {
            return new ArrayList<Integer>();
        }
        // Recursion Calls
        // pick or skip
        ArrayList<Integer> skip = solveRecursion(idx + 1, prev, n, arr);
        ArrayList<Integer> pick = new ArrayList<Integer>();
        if (prev == -1 || arr[idx] % arr[prev] == 0) {
            pick.add(arr[idx]);
            List<Integer> next = solveRecursion(idx + 1, idx, n, arr);
            pick.addAll(next);
        }
        return pick.size() > skip.size() ? pick : skip;
    }
}
