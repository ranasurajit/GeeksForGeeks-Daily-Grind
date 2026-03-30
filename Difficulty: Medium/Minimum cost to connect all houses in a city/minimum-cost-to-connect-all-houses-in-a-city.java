class Solution {
    /**
     * Approach I : Using Prim's Algorithm Approach
     * 
     * TC: O(N²) + O(N² x log(N)) ~ O(N² x log(N))
     * SC: O(N²) + O(N) + O(N²) ~ O(N²)
     */
    public int minCost(int[][] houses) {
        int n = houses.length;
        Map<Integer, ArrayList<int[]>> adj =
            createGraph(houses, n); // TC: O(N²), SC: O(N²)
        boolean[] visited = new boolean[n]; // SC: O(N)
        /**
         * we will store { edge weight, node } in Min-Heap to
         * process the edges with lesser edge weights
         */
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((p, q) -> p[0] - q[0]); // SC: O(N²)
        pq.offer(new int[] { 0, 0 });
        int minCost = 0;
        while (!pq.isEmpty()) { // TC: O(N²)
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            if (visited[u]) {
                continue;
            }
            minCost += w;
            visited[u] = true;
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<>())) {
                int v = ngbr[0];
                int edgeCost = ngbr[1];
                pq.offer(new int[] { edgeCost, v }); // TC: O(log(N))
            }
        }
        return minCost;
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(N²)
     * SC: O(N²)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] houses, int n) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<>(); // SC: O(N²)
        for (int i = 0; i < n - 1; i++) {     // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                int x1 = houses[i][0];
                int y1 = houses[i][1];
                int x2 = houses[j][0];
                int y2 = houses[j][1];
                int w = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                adj.computeIfAbsent(i,
                    k -> new ArrayList<>()).add(new int[] { j, w });
                adj.computeIfAbsent(j,
                    k -> new ArrayList<>()).add(new int[] { i, w });
            }
        }
        return adj;
    }
}
