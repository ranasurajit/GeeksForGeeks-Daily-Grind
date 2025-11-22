class Solution {
    /**
     * Approach : Using Optimized Tabulation Approach
     *
     * TC: O(N x N) + O(K) + O(K) ~ O(N x N) as K < N
     * SC: O(N) + O(N) + O(K) ~ O(N)
     * 
     * - O(N) - dp, track array memory
     * - O(K) - lis array memory
     *
     * Accepted (56 / 56 testcases passed)
     */
    public ArrayList<Integer> getLIS(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];    // SC: O(N)
        int[] track = new int[n]; // SC: O(N)
        int maxLength = 1;
        int maxIndex = 0;
        for (int idx = 0; idx < n; idx++) { // TC: O(N)
            dp[idx] = 1;
            track[idx] = idx;
            for (int prev = 0; prev < idx; prev++) { // TC: O(N)
                if (arr[prev] < arr[idx] && dp[idx] < dp[prev] + 1) {
                    dp[idx] = dp[prev] + 1;
                    track[idx] = prev;
                }
            }
            if (dp[idx] > maxLength) {
                maxLength = dp[idx];
                maxIndex = idx;
            }
        }
        int[] lis = new int[maxLength]; // SC: O(K)
        int index = maxLength - 1;
        while (track[maxIndex] != maxIndex) { // TC: O(K)
            lis[index] = arr[maxIndex];
            maxIndex = track[maxIndex];
            index--;
        }
        lis[index] = arr[maxIndex];
        ArrayList<Integer> lisList = new ArrayList<Integer>();
        for (int i = 0; i < maxLength; i++) { // TC: O(K)
            lisList.add(lis[i]);
        }
        return lisList;
    }
}
