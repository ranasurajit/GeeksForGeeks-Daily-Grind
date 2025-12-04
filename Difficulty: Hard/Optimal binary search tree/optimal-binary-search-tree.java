class Solution {
    public int minCost(int keys[], int freq[]) {
        int n = keys.length;
        int[][] dp = new int[n][n];
        
        // Prefix sum for fast range sums
        int[] prefix = new int[n];
        prefix[0] = freq[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + freq[i];
        }
        
        // Function to get sum of frequencies in range [l..r]
        java.util.function.BiFunction<Integer, Integer, Integer> sum = 
            (l, r) -> (l == 0 ? prefix[r] : prefix[r] - prefix[l - 1]);
        
        // Base case: single key -> cost = freq
        for (int i = 0; i < n; i++) {
            dp[i][i] = freq[i];
        }
        
        // Solve for lengths from 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                // Try each key as root
                for (int r = i; r <= j; r++) {
                    int left = (r == i) ? 0 : dp[i][r - 1];
                    int right = (r == j) ? 0 : dp[r + 1][j];
                    
                    int cost = left + right + sum.apply(i, j);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}
