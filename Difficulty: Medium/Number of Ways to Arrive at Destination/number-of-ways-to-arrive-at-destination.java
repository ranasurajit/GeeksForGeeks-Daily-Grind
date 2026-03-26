class Solution {
    /**
     * Approach : Using Dijkstra's Algorithm Approach
     * 
     * TC: O(E) + O((V + E) x log(V)) ~ O((V + E) x log(V))
     * SC: O(V + E) + O(E) + O(V) + O(V) ~ O(V + E)
     */
    public int countPaths(int V, int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj =
            createGraph(edges); // TC: O(E), SC: O(V + E)
        // Using Min-Heap to store nodes based on edge weights
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((p, q) -> p[1] - q[1]); // SC: O(E)
        int[] minDist = new int[V]; // SC: O(V)
        Arrays.fill(minDist, Integer.MAX_VALUE);
        int src = 0;
        minDist[src] = 0;
        pq.offer(new int[] { src, 0 });
        int[] ways = new int[V]; // SC: O(V)
        ways[src] = 1;
        while (!pq.isEmpty()) {  // TC: O(V)
            int[] current = pq.poll();
            int u = current[0];
            int w = current[1];
            if (w > minDist[u]) {
                continue;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (w + edgeWeight < minDist[v]) {
                    minDist[v] = w + edgeWeight;
                    pq.offer(new int[] { v, w + edgeWeight }); // TC: O(log(V))
                    ways[v] = ways[u];
                } else if (w + edgeWeight == minDist[v]) {
                    ways[v] += ways[u];
                }
            }
        }
        return ways[V - 1];
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(2 x E) ~ O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<>(); // SC: O(V + E)
        for (int[] edge : edges) { // TC: O(2 x E)
            adj.computeIfAbsent(edge[0],
                k -> new ArrayList<>()).add(new int[] { edge[1], edge[2] });
            adj.computeIfAbsent(edge[1],
                k -> new ArrayList<>()).add(new int[] { edge[0], edge[2] });
        }
        return adj;
    }
}
