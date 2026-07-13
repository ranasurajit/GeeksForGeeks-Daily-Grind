class Solution {
    /**
     * Approach : Using Graph DFS + Math Approach
     * 
     * TC : O(n x log(n))
     * SC : O(n)
     */
    int minOperations(int[] b) {
        int MOD = (int) 1e9 + 7;
        /**
         * This is essentially connecting the
         * original array index (1-based) to 
         * b[i] and forming a graph. The indices
         * movement back to original position 
         * indicates the cycle each graph 
         * component has
         */
        int n = b.length;
        boolean[] visited = new boolean[n]; // SC : O(n)
        long lcm = 1L;
        for (int i = 0; i < n; i++) {       // TC : O(n)
            if (!visited[i]) {
                int cycleLength = dfsGraph(i, b, visited); // TC : O(n)
                lcm = getLCM(lcm, cycleLength); // TC : O(log(n))
            }
        }
        return (int) (lcm % MOD);
    }

    /**
     * Using Graph DFS Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    private int dfsGraph(int u, int[] b, boolean[] visited) {
        int current = u;
        int cycleLength = 0;
        while (!visited[current]) {
            visited[current] = true;
            cycleLength++;
            current = b[current] - 1;
        }
        return cycleLength;
    }
    
    /**
     * Using Math Approach
     * 
     * TC : O(log(a) base b)
     * SC : O(log(a) base b)
     */
    private long getLCM(long a, long b) {
        return (a * b) / gcd(a, b);
    }
    
    /**
     * Using Math Approach
     * 
     * TC : O(log(a) base b)
     * SC : O(log(a) base b)
     */
    private long gcd(long a, long b) {
        if (b == 0L) {
            return a;
        }
        return gcd(b, a % b);
    }
};
