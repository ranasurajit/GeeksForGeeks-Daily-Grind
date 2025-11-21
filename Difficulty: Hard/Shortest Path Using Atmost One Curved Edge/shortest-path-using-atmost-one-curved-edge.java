class Solution {
    public int shortestPath(int V, int a, int b, int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>();
        Map<Integer, ArrayList<int[]>> revAdj = new HashMap<Integer, ArrayList<int[]>>();
        List<int[]> curved = new ArrayList<>();
        createGraph(adj, revAdj, edges, curved);
        int[] minDistA = dijkstrasAlgo(adj, a, V);
        int[] minDistB = dijkstrasAlgo(revAdj, b, V);
        int minDist = minDistA[b];
        for (int[] curr : curved) {
            int u = curr[0];
            int v = curr[1];
            int c = curr[2];
            if (minDistA[u] != Integer.MAX_VALUE && minDistB[v] != Integer.MAX_VALUE) {
                minDist = Math.min(minDist, minDistA[u] + c + minDistB[v]);
            }
        }
        return minDist;
    }
    
    private int[] dijkstrasAlgo(Map<Integer, ArrayList<int[]>> adj, int src, int V) {
        int[] minDist = new int[V];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src] = 0;
        // we will be storing { w, node } in Min-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]);
        pq.offer(new int[] { 0, src });
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            if (w > minDist[u]) {
                continue;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) {
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (w + edgeWeight < minDist[v]) {
                    minDist[v] = w + edgeWeight;
                    pq.offer(new int[] { w + edgeWeight, v });
                }
            }
        }
        return minDist;
    }
    
    private void createGraph(Map<Integer, ArrayList<int[]>> adj,
        Map<Integer, ArrayList<int[]>> revAdj, int[][] edges, List<int[]> curved) {
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int c = edge[3];
            adj.computeIfAbsent(u, k -> new ArrayList<int[]>()).add(new int[] { v, w });
            adj.computeIfAbsent(v, k -> new ArrayList<int[]>()).add(new int[] { u, w });
            revAdj.computeIfAbsent(v, k -> new ArrayList<int[]>()).add(new int[] { u, w });
            revAdj.computeIfAbsent(u, k -> new ArrayList<int[]>()).add(new int[] { v, w });
            curved.add(new int[] { u, v, c});
            curved.add(new int[] { v, u, c});
        }        
    }
}
