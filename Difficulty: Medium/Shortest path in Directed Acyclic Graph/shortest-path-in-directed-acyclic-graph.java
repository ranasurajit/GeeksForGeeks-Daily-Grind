// User function Template for Java
class Solution {
    /**
     * Approach : Using Dijkstra's Approach
     * 
     * TC: O(E) + O((V + E) x log(V)) + O(V) ~ O((V + E) x log(V))
     * SC: O(V + E) + O(V) + O(E) ~ O(V + E)
     */
    public int[] shortestPath(int V, int E, int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj =
            createGraph(edges);     // TC: O(E), SC: O(V + E)
        int[] minDist = new int[V]; // SC: O(V)
        Arrays.fill(minDist, Integer.MAX_VALUE);
        int src = 0;
        minDist[src] = 0;
        // we will add { weight, node } to Min-Heap 
        PriorityQueue<int[]> pq =
            new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, src });
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
                }
            }
        }
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (minDist[i] == Integer.MAX_VALUE) {
                minDist[i] = -1;
            }
        }
        return minDist;
    }

    /**
     * Using Hashing (DAG) Approach
     * 
     * TC: O(E)
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
        }
        return adj;
    }
}
