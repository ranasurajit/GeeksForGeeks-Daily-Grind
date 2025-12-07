class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach II : Using Tabulation Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(26) ~ O(N)
     * - O(N) - dp array memory
     * - O(26) - characters map array memory
     * 
     * Accepted (1120 / 1120 testcases passed)
     */
    int distinctSubseq(String str) {
        int n = str.length();
        int[] dp = new int[n + 1]; // SC: O(N)
        dp[0] = 1; // if str = "", then subsequences can be : "" and count = 1
        // dp[1] = 2; // if str = "a", then subsequences can be : "", "a" and count = 2
        int[] map = new int[26]; // SC: O(26)
        Arrays.fill(map, -1);
        for (int i = 1; i <= n; i++) { // TC: O(N)
            dp[i] = (2 * (dp[i - 1] % MOD)) % MOD;
            int lastIdx = map[str.charAt(i - 1) - 'a'];
            if (lastIdx != -1) {
                dp[i] = (dp[i] - dp[lastIdx] + MOD) % MOD;
            }
            map[str.charAt(i - 1) - 'a'] = i - 1;
        }
        return dp[n] % MOD;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N) + O(N)
     * - O(2 ^ N) - hashing memory
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1120 testcases passed)
     */
    int distinctSubseqRecursion(String str) {
        int n = str.length();
        Set<String> set = new HashSet<String>(); // SC: O(2 ^ N)
        solveRecursion(0, n, new StringBuilder(), str, set); // TC: O(2 ^ N), SC: O(N)
        return set.size();
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int idx, int n, StringBuilder sb, 
        String str, Set<String> set) {
        // Base Case
        if (idx == n) {
            set.add(String.valueOf(sb.toString()));
            return;
        }
        // Recursion Calls
        // pick or skip
        solveRecursion(idx + 1, n, sb, str, set); // skip
        int size = sb.length();
        sb.append(str.charAt(idx)); // modify
        solveRecursion(idx + 1, n, sb, str, set); // pick - explore
        sb.setLength(size); // backtrack
    }
}
