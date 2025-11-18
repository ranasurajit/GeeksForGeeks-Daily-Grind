class Solution {
    /**
     * Approach : Using Dijkstra's Algorithm Approach
     * 
     * TC: O(2 x E) + O((V + E) x log(E)) ~ O((V + E) x log(E))
     * SC: O(V + E) + O(V) + O(V) + O(E) ~ O(V + E)
     */
    public int countPaths(int V, int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(edges); // TC: O(2 x E), SC: O(V + E)
        int[] minDist = new int[V]; // SC: O(V)
        Arrays.fill(minDist, (int) 1e9 + 7);
        int[] ways = new int[V]; // SC: O(V)
        ways[0] = 1;
        // we will be storing { time, node } in Min-Heap
        PriorityQueue<int[]> pq = 
            new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, 0 });
        minDist[0] = 0; // as sourceNode is 0
        while (!pq.isEmpty()) { // TC: O(V)
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            if (w > minDist[u]) {
                continue;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int time = ngbr[1];
                if (w + time < minDist[v]) {
                    minDist[v] = w + time;
                    ways[v] = ways[u];
                    pq.offer(new int[] { w + time, v }); // TC: O(log(E))
                } else if (w + time == minDist[v]) {
                    ways[v] += ways[u];
                }
            }
        }
        return ways[V - 1];
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(2 x E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = 
            new HashMap<Integer, ArrayList<int[]>>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<int[]>())
                .add(new int[] { edge[1], edge[2] });
            adj.computeIfAbsent(edge[1], k -> new ArrayList<int[]>())
                .add(new int[] { edge[0], edge[2] });
        }
        return adj;
    }
}
