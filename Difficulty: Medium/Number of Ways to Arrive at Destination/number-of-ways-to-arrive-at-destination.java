class Solution {
    /**
     * Approach : Using Dijkstra's Algorithm Approach
     * 
     * TC: O(E) + O((V + E) x log(V)) ~ O((V + E) x log(V))
     * SC: O(V + E) + O(V) + O(E) + O(V) ~ O(V + E)
     */
    public int countPaths(int V, int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        int[] minDist = new int[V]; // SC: O(V)
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;
        // we will store { weight, node } in Min-Heap
        PriorityQueue<int[]> pq =
            new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, 0 });
        // it stores number of ways to reach at any node following shortest path
        int[] ways = new int[V]; // SC: O(V)
        ways[0] = 1; // by default 1 way is there at node '0'
        while (!pq.isEmpty()) { // TC: O(V)
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            if (w > minDist[u]) {
                continue;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (w + edgeWeight < minDist[v]) {
                    minDist[v] = w + edgeWeight;
                    pq.offer(new int[] { w + edgeWeight, v }); // TC: O(log(V))
                    ways[v] = ways[u];
                } else if (w + edgeWeight == minDist[v]) {
                    ways[v] += ways[u];
                }
            }
        }
        return ways[V - 1];
    }
    
    /**
     * Using Hashing (DAG) Approach
     * 
     * TC: O(2 x E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = 
            new HashMap<Integer, ArrayList<int[]>>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.computeIfAbsent(u, k -> new ArrayList<int[]>()).add(new int[] { v, w });
            adj.computeIfAbsent(v, k -> new ArrayList<int[]>()).add(new int[] { u, w });
        }
        return adj;
    }
}
