class Solution {
    /**
     * Approach : Using Dijkstra's Algorithm Approach
     * 
     * TC : O(e) + O((v + e) x log(v)) ~ O((v + e) x log(v))
     * SC : O(v + e) + O(2 x v) ~ O(v + e)
     */
    public int shortestPath(int V, int src, int dest, int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj =
            createGraph(edges); // TC : O(e), SC : O(v + e)
        PriorityQueue<int[]> pq = new PriorityQueue<>((p, q) -> {
           return p[0] - q[0]; 
        }); // SC : O(v)
        int[] minDist = new int[V]; // SC : O(v)
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src] = 0;
        pq.offer(new int[] { 0, src });
        while (!pq.isEmpty()) { // TC : O(v)
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            if (minDist[u] < w) {
                continue;
            }
            ArrayList<int[]> connections = adj.get(u);
            if (connections == null) {
                continue;
            }
            for (int[] ngbr : connections) { // TC : O(e)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (w + edgeWeight < minDist[v]) {
                    minDist[v] = w + edgeWeight;
                    pq.offer(new int[] { w + edgeWeight, v }); // TC : O(log(v))
                }
            }
        }
        if (minDist[dest] == Integer.MAX_VALUE) {
            return -1;
        }
        return minDist[dest];
    }

    /**
     * Using Hashing Approach
     * 
     * TC : O(2e) ~ O(e)
     * SC : O(v + e)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<>();
        for (int[] edge : edges) { // TC : O(e)
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.computeIfAbsent(u, 
                k -> new ArrayList<>()).add(new int[] { v, w });
            adj.computeIfAbsent(v, 
                k -> new ArrayList<>()).add(new int[] { u, w });
        }
        return adj;
    }
}
