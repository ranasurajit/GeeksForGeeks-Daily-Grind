class Solution {
    private int insert;
    private int delete;
    private int copy;

    /**
     * Approach II : Using Recursion + State Reverse Transition Approach
     * 
     * TC : O(log(n))
     * SC : O(log(n))
     */
    public int minCost(int n, int i, int d, int c) {
        this.insert = i;
        this.delete = d;
        this.copy = c;
        return solve(n);
    }

    /**
     * Using Recursion + State Reverse Transition Approach
     * 
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private int solve(int n) {
        // Base Case
        if (n <= 1) {
            return insert;
        }
        // al characters built by insertion
        int result = n * insert;
        if ((n & 1) == 0) {
            // n is even then we can perform last operation copy-paste
            result = Math.min(result, solve(n / 2) + copy);
        } else {
            // we can reach (n - 1) characters + inser operation
            result = Math.min(result, solve(n - 1) + insert);
            // we can reach (n + 1) characters + delete last character
            result = Math.min(result, solve(n + 1) + delete);
        }
        return result;
    }

    /**
     * Approach I : Using Dijkstra's Algorithm Approach
     * 
     * TC : O(n x log(n))
     * SC : O(2 x n) + O(n) ~ O(n)
     * 
     * Time Limit Exceeded (1103 / testcases passed)
     */
    public int minCostDijkstrasAlgorithm(int n, int i, int d, int c) {
        /**
         * Each operation alters the length of the String
         * and costs for all oiperations are positive
         * so, all length behave like a node and we can
         * use Dijkstra's Algorithm to find the minimum
         * cost to obtain n characters on the screen
         */
        int max = 2 * n;
        int[] minDist = new int[max + 1]; // SC : O(2 x n)
        Arrays.fill(minDist, (int) 1e8);
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((p, q) -> p[0] - q[0]); // SC : O(n)
        pq.offer(new int[] { 0, 0 });
        minDist[0] = 0;
        while (!pq.isEmpty()) { // TC : O(n)
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            if (minDist[u] != w) {
                continue;
            }
            if (u == n) {
                return w;
            }
            // insert operation - 1 character insertion
            if (u + 1 <= max && w + i < minDist[u + 1]) {
                minDist[u + 1] = w + i;
                pq.offer(new int[] { w + i, u + 1 }); // TC : O(log(n))
            }
            // delete operation - 1 character deletion
            if (u > 0 && w + d < minDist[u - 1]) {
                minDist[u - 1] = w + d;
                pq.offer(new int[] { w + d, u - 1 }); // TC : O(log(n))
            }
            // copy-paste operation - u is doubled
            if (u > 0 && 2 * u <= max && w + c < minDist[2 * u]) {
                minDist[2 * u] = w + c;
                pq.offer(new int[] { w + c, 2 * u }); // TC : O(log(n))
            }
        }
        return -1;
    }
}
