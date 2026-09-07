class Solution {

    private int[] arr;
    private int n;
    private int[][][] dp;

    public int minCount(int[] arr) {
        this.arr = arr;
        this.n = arr.length;

        // 101 because arr[i] <= 100.
        // 100 is used as a special "no element yet" state for
        // decreasing sequence, so we actually use 101 as INF.
        int INF = 101;

        dp = new int[n][101][102];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 101; j++) {
                java.util.Arrays.fill(dp[i][j], -1);
            }
        }

        int maxSelected = solve(0, 0, INF);

        return n - maxSelected;
    }

    private int solve(int index, int lastInc, int lastDec) {

        if (index == n) {
            return 0;
        }

        if (dp[index][lastInc][lastDec] != -1) {
            return dp[index][lastInc][lastDec];
        }

        int x = arr[index];

        // Option 1: Don't use this element
        int ans = solve(index + 1, lastInc, lastDec);

        // Option 2: Put it in increasing subsequence
        if (x > lastInc) {
            ans = Math.max(
                ans,
                1 + solve(index + 1, x, lastDec)
            );
        }

        // Option 3: Put it in decreasing subsequence
        if (x < lastDec) {
            ans = Math.max(
                ans,
                1 + solve(index + 1, lastInc, x)
            );
        }

        return dp[index][lastInc][lastDec] = ans;
    }
}
