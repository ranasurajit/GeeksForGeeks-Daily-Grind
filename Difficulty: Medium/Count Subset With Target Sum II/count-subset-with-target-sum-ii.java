class Solution {
    /**
     * Approach II : Using Recursion (0-1 Knapsack Pattern) Approach
     * 
     * Intuition: Meet in the Middle (MITM) – Intuition to satisfy Constraints
     * 
     * TC: O(2 ^ M)
     * SC: O(3 x (N / 2)) + O(2 x M) ~ O(N) + O(N)
     *     - O(N) - left + right array memory
     *     - O(N) - recursion stack
     *     - where M = N / 2
     * 
     * Accepted (1113 / 1113 testcases passed)
     */
    public int countSubset(int[] arr, int k) {
        int n = arr.length;
        int mid = n / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);  // SC: O(N / 2)
        int[] right = Arrays.copyOfRange(arr, mid, n); // SC: O(N / 2)
        /**
         * we will store all sums achieved from Left part i.e. 
         * 'left' array as { sum, freq of sum}
         */
        Map<Long, Integer> leftMap = new HashMap<Long, Integer>(); // SC: O(N / 2)
        generateLeftSums(left, 0, 0L, leftMap); // TC: O(2 ^ M), SC: O(M)
        return generateRightSumsAndCount(right, 0, 0L, k, leftMap); // TC: O(2 ^ M), SC: O(M)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ M)
     * SC: O(M)
     * 
     * where M = N / 2 and M is maximum 20 which will be in limits of 2 ^ 20
     */
    private int generateRightSumsAndCount(int[] right, int idx, long sum, 
        int k, Map<Long, Integer> leftMap) {
        // Base Case
        if (idx == right.length) {
            return leftMap.getOrDefault(k - sum, 0);
        }
        // Recursion Calls
        int count = 0;
        count += generateRightSumsAndCount(right, idx + 1,
            sum, k, leftMap); // skip
        count += generateRightSumsAndCount(right, idx + 1,
            sum + right[idx], k, leftMap); // pick
        return count;
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ M)
     * SC: O(M)
     * 
     * where M = N / 2 and M is maximum 20 which will be in limits of 2 ^ 20
     */
    private void generateLeftSums(int[] left, int idx, long sum, 
        Map<Long, Integer> leftMap) {
        // Base Case
        if (idx == left.length) {
            leftMap.put(sum, leftMap.getOrDefault(sum, 0) + 1);
            return;
        }
        // Recursion Calls
        generateLeftSums(left, idx + 1, sum, leftMap); // skip
        generateLeftSums(left, idx + 1, sum + left[idx], leftMap); // pick
    }

    /**
     * Approach I : Using Recursion (0-1 Knapsack Pattern) Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     *     - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1110 / 1113 testcases passed)
     */
    public int countSubsetRecursion(int[] arr, int k) {
        int n = arr.length;
        return solveRecursion(n - 1, k, arr); // TC: O(2 ^ N), SC: O(N)
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack
     */
    private int solveRecursion(int idx, int k, int[] arr) {
        // Base Case
        if (idx < 0) {
            if (k == 0) {
                return 1;
            }
            return 0;
        }
        // Recursion Calls
        // we can pick or skip
        int skip = solveRecursion(idx - 1, k, arr);
        int pick = 0;
        if (k >= arr[idx]) {
            pick = solveRecursion(idx - 1, k - arr[idx], arr);
        }
        // total subset count
        return skip + pick;
    }
}
