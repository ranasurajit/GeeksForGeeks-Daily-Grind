class Solution {
    private Map<Character, ArrayDeque<Integer>> indexMap;
    private Map<Character, ArrayList<Character>> rulesMap;
    private int[][] freq;
    
    /**
     * Approach III: Using Tabulation Approach
     * 
     * TC: O(26 x N)
     * SC: O(26 x N)
     */
    public int maxScore(String s, char[][] jumps) {
        int n = s.length();
        char[] arr = s.toCharArray();

        // 1. ASCII prefix sum
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        // 2. charPrefix[ch][i] = total ASCII sum of char 'a'+ch in [0..i-1]
        long[][] charPrefix = new long[26][n + 1];
        for (int i = 0; i < n; i++) {
            int c = arr[i] - 'a';

            // copy previous prefix sums
            for (int ch = 0; ch < 26; ch++) {
                charPrefix[ch][i + 1] = charPrefix[ch][i];
            }
            charPrefix[c][i + 1] += arr[i];
        }

        // 3. canJump[src][dest] (including same-char jumps)
        boolean[][] canJump = new boolean[26][26];
        for (int i = 0; i < 26; i++) {
            canJump[i][i] = true; // same character jump
        }
        for (char[] p : jumps) {
            int src = p[0] - 'a';
            int dest = p[1] - 'a';
            canJump[src][dest] = true;
        }

        // 4. best[src][dest] corresponds to best transition state
        long[][] best = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(best[i], Long.MIN_VALUE);
        }

        // 5. dp[i] = best score when last jump lands at index i
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MIN_VALUE);

        dp[0] = 0;
        int firstChar = arr[0] - 'a';

        // initialize best based on dp[0]
        for (int dest = 0; dest < 26; dest++) {
            best[firstChar][dest] = 0 - prefix[0] + charPrefix[dest][0]; 
            // = 0 (same as JS)
        }

        // 6. DP processing
        for (int i = 1; i < n; i++) {

            int curr = arr[i] - 'a';
            long maxVal = Long.MIN_VALUE;

            // find best source that can jump to current character
            for (int src = 0; src < 26; src++) {
                if (canJump[src][curr] && best[src][curr] != Long.MIN_VALUE) {
                    maxVal = Math.max(maxVal, best[src][curr]);
                }
            }

            if (maxVal != Long.MIN_VALUE) {
                // dp[i] = best transition + score of segment
                dp[i] = maxVal + (prefix[i] - charPrefix[curr][i]);
            }

            // update best matrix
            if (dp[i] != Long.MIN_VALUE) {
                for (int dest = 0; dest < 26; dest++) {
                    long val = dp[i] - prefix[i] + charPrefix[dest][i];
                    if (val > best[curr][dest]) {
                        best[curr][dest] = val;
                    }
                }
            }
        }

        long ans = 0;
        for (long v : dp) ans = Math.max(ans, v);
        return (int) ans;
    }

    /**
     * Approach II : Using Memoization (Forward Partition DP) Approach
     * 
     * TC: O(N x K)
     * SC: O(N) + O(N)
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int maxScoreMemoization(String s, char[][] jumps) {
        int n = s.length();
        this.indexMap = new HashMap<Character, ArrayDeque<Integer>>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            indexMap.computeIfAbsent(s.charAt(i), 
                k -> new ArrayDeque<Integer>()).addLast(i);
        }
        this.rulesMap = new HashMap<Character, ArrayList<Character>>();
        for (char[] jump : jumps) { // TC: O(M)
            char from = jump[0];
            char to = jump[1];
            rulesMap.computeIfAbsent(from, 
                k -> new ArrayList<Character>()).add(to);
        }
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + (int) s.charAt(i); 
        }
        // prefix freq: freq[c][i] = count of c in s[0..i-1]
        freq = new int[128][n + 1];
        for (int i = 0; i < n; i++) {
            for (int c = 0; c < 128; c++) {
                freq[c][i + 1] = freq[c][i];
            }
            freq[s.charAt(i)][i + 1]++;
        }
        long[] memo = new long[n];
        Arrays.fill(memo, -1L);
        return (int) solveMemoization(0, n, s, prefixSum, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC: Exponential
     * SC: O(1)
     */
    private long solveMemoization(int idx, int n, String s, long[] prefixSum, long[] memo) {
        // Base Case
        if (idx >= n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1L) {
            return memo[idx];
        }
        // Recursion Calls
        long maximumScore = 0;
        char c1 = s.charAt(idx);
        ArrayDeque<Integer> deque = indexMap.get(c1);
        if (deque != null) {
            for (Integer j : deque) {
                if (j <= idx) continue;
                if (j > idx) {
                    long currentScore = scoreRange(idx, j, prefixSum)
                        - count(freq, s.charAt(j), idx, j) * s.charAt(j);
                    maximumScore = Math.max(maximumScore, 
                        currentScore + solveMemoization(j, n, s, prefixSum, memo)
                    );
                }
            }
        }
        ArrayList<Character> rulesList = 
            rulesMap.getOrDefault(c1, new ArrayList<Character>());
        for (Character ch : rulesList) {
            ArrayDeque<Integer> rulesIndices = indexMap.get(ch);
            if (rulesIndices != null) {
                for (Integer j : rulesIndices) {
                    if (j <= idx) continue;
                    if (j > idx) {
                        long currentScore = scoreRange(idx, j, prefixSum)
                               - count(freq, s.charAt(j), idx, j) * s.charAt(j);
                        maximumScore = Math.max(maximumScore, 
                            currentScore + solveMemoization(j, n, s, prefixSum, memo)
                        );
                    }
                }
            }
        }
        return memo[idx] = maximumScore;
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    // Sum of ASCII values in s[i..j-1]
    private long scoreRange(int i, int j, long[] prefixSum) {
        return prefixSum[j] - prefixSum[i];
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    // Count occurrences of char c in s[l..r-1]
    private int count(int[][] freq, char c, int l, int r) {
        return freq[c][r] - freq[c][l];
    }

    /**
     * Approach I : Using Recursion (Forward Partition DP) Approach
     * 
     * TC: Exponential
     * SC: O(1)
     * 
     * Time Limit Exceeded (1010 / 1115 testcases passed)
     */
    public int maxScoreRecursion(String s, char[][] jumps) {
        int n = s.length();
        this.indexMap = new HashMap<Character, ArrayDeque<Integer>>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            indexMap.computeIfAbsent(s.charAt(i), 
                k -> new ArrayDeque<Integer>()).addLast(i);
        }
        this.rulesMap = new HashMap<Character, ArrayList<Character>>();
        for (char[] jump : jumps) { // TC: O(M)
            char from = jump[0];
            char to = jump[1];
            rulesMap.computeIfAbsent(from, 
                k -> new ArrayList<Character>()).add(to);
        }
        return solveRecursion(0, n, s);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC: Exponential
     * SC: O(1)
     */
    private int solveRecursion(int idx, int n, String s) {
        // Base Case
        if (idx >= n) {
            return 0;
        }
        // Recursion Calls
        long maximumScore = Long.MIN_VALUE;
        char c1 = s.charAt(idx);
        ArrayDeque<Integer> deque = indexMap.get(c1);
        if (deque != null) {
            for (Integer j : deque) {
                if (j > idx) {
                    long currentScore = calculateScore(idx, j, s, s.charAt(j));
                    maximumScore = Math.max(maximumScore, 
                        currentScore + solveRecursion(j, n, s)
                    );
                }
            }
        }
        ArrayList<Character> rulesList = 
            rulesMap.getOrDefault(c1, new ArrayList<Character>());
        for (Character ch : rulesList) {
            ArrayDeque<Integer> rulesIndices = indexMap.get(ch);
            if (rulesIndices != null) {
                for (Integer j : rulesIndices) {
                    if (j > idx) {
                        long currentScore = calculateScore(idx, j, s, ch);
                        maximumScore = Math.max(maximumScore, 
                            currentScore + solveRecursion(j, n, s)
                        );
                    }
                }
            }
        }
        return (int) maximumScore;
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private long calculateScore(int i, int j, String s, char target) {
        long score = 0;
        for (int start = i; start < j; start++) {
            if (s.charAt(start) != target) {
                score += (long) s.charAt(start);
            }
        }
        return score;
    }
}
