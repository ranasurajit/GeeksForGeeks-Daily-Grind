class Solution {
    /**
     * Approach : Using Optimized Tabulation Approach
     *
     * TC: O(N x N) + O(N) + O(K) ~ O(N x N) as K < N
     * SC: O(N) + O(N) + O(K) ~ O(N)
     * 
     * - O(N) - dp, track array memory
     * - O(K) - result array memory
     *
     * Accepted (56 / 56 testcases passed)
     */
    public ArrayList<Integer> getLIS(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n]; // SC: O(N)
        // every element is itself an LIS of size 1
        Arrays.fill(dp, 1);
        int[] track = new int[n]; // SC: O(N)
        int maxLength = 1;
        int maxIndex = 0;
        for (int idx = 1; idx < n; idx++) { // TC: O(N)
            track[idx] = idx; // updating the backtracking array with self-index (parent)
            // prevIdx < idx
            for (int prevIdx = 0; prevIdx < idx; prevIdx++) { // TC: O(N)
                if (arr[prevIdx] < arr[idx] && (1 + dp[prevIdx]) > dp[idx]) {
                    dp[idx] = 1 + dp[prevIdx];
                    track[idx] = prevIdx;
                }
            }
            if (maxLength < dp[idx]) {
                maxLength = dp[idx];
                maxIndex = idx;
            }
        }
        ArrayList<Integer> lis = new ArrayList<Integer>();
        int[] result = new int[maxLength]; // SC: O(K)
        int index = 0;
        while (track[maxIndex] != maxIndex) { // TC: O(N)
            result[index] = arr[maxIndex];
            maxIndex = track[maxIndex];
            index++;
        }
        result[index] = arr[maxIndex];
        for (int i = maxLength - 1; i >= 0; i--) { // TC: O(K)
            lis.add(result[i]);
        }
        return lis;
    }
}
